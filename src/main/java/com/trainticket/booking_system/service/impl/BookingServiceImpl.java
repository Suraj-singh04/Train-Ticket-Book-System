package com.trainticket.booking_system.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trainticket.booking_system.dto.request.CreateBookingRequest;
import com.trainticket.booking_system.dto.response.BookingResponse;
import com.trainticket.booking_system.entity.Booking;
import com.trainticket.booking_system.entity.BookingStatus;
import com.trainticket.booking_system.entity.Coach;
import com.trainticket.booking_system.entity.Passenger;
import com.trainticket.booking_system.entity.Schedule;
import com.trainticket.booking_system.entity.Seat;
import com.trainticket.booking_system.entity.Train;
import com.trainticket.booking_system.exception.ResourceNotFoundException;
import com.trainticket.booking_system.repository.BookingRepository;
import com.trainticket.booking_system.repository.CoachRepository;
import com.trainticket.booking_system.repository.PassengerRepository;
import com.trainticket.booking_system.repository.ScheduleRepository;
import com.trainticket.booking_system.repository.SeatRepository;
import com.trainticket.booking_system.service.BookingService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final ScheduleRepository scheduleRepository;
    private final CoachRepository coachRepository;
    private final SeatRepository seatRepository;

    public BookingServiceImpl(
        BookingRepository bookingRepository,
        PassengerRepository passengerRepository,
        ScheduleRepository scheduleRepository,
        CoachRepository coachRepository,
        SeatRepository seatRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.scheduleRepository = scheduleRepository;
        this.coachRepository = coachRepository;
        this.seatRepository = seatRepository;
    }

    private BookingResponse mapToBookingResponse(Booking booking) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getBookingId());
        response.setBookingReference(booking.getBookingReference());

        response.setPassengerName(
                booking.getPassenger().getFullName());

        response.setTrainNumber(
                booking.getSchedule()
                        .getTrain()
                        .getTrainNumber());

        if (booking.getSeat() != null) {

            response.setCoachNumber(
                    booking.getSeat()
                            .getCoach()
                            .getCoachNumber());

            response.setSeatNumber(
                    booking.getSeat()
                            .getSeatNumber());
        }

        response.setTravelDate(booking.getTravelDate());
        response.setFare(booking.getFare());
        response.setBookingStatus(booking.getBookingStatus());
        response.setBookingTime(booking.getBookingTime());

        return response;
    }

    private String generateBookingReference() {
        
        return "PNR" + UUID.randomUUID()
                                .toString()
                                .replace("-","")
                                .substring(0, 8)
                                .toUpperCase();
    }

    private double calculateFare(Schedule schedule) {
        
        int distance = schedule.getRoute()
                .getOrderedStations()
                .get(schedule.getRoute().getOrderedStations().size()-1)
                .getDistanceFromOrigin();

        return distance*1.5;
    }

    private Seat findAvailableSeat(Schedule schedule, LocalDate travelDate) {

        Train train = schedule.getTrain();

        List<Coach> coaches = coachRepository.findByTrainOrderByCoachNumberAsc(train);

        List<Seat> bookedSeats = bookingRepository.findBookedSeats(schedule, travelDate);

        Set<String> bookedSeatIds = bookedSeats.stream()
                .map(Seat::getSeatId)
                .collect(Collectors.toSet());
        
        for(Coach coach : coaches) {
            List<Seat> seats = seatRepository.findByCoachOrderBySeatNumberAsc(coach);
            for(Seat seat : seats) {
                if(!bookedSeatIds.contains(seat.getSeatId())) {
                    return seat;
                }
            }
        }

        return null;
    }

    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {
        
        Passenger passenger = passengerRepository.findById(request.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id: " + request.getPassengerId()));
        
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + request.getScheduleId()));
        

        Seat seat = findAvailableSeat(
            schedule,
            request.getTravelDate()
        );

        Booking booking = new Booking();

        booking.setBookingReference(generateBookingReference());
        booking.setPassenger(passenger);
        booking.setSchedule(schedule);
        booking.setTravelDate(request.getTravelDate());
        booking.setBookingTime(LocalDateTime.now());
        booking.setFare(calculateFare(schedule));

        if(seat != null) {
            booking.setSeat(seat);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
        } else {
            booking.setBookingStatus(BookingStatus.WAITLISTED);
        }

        Booking savedBooking = bookingRepository.save(booking);

        return mapToBookingResponse(savedBooking);
    }

    @Override
    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(this::mapToBookingResponse)
                .toList();
    }

    @Override
    public BookingResponse getBookingById(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: " + bookingId));

        return mapToBookingResponse(booking);
    }

    @Override
    public BookingResponse cancelBooking(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found with id: " + bookingId));

        booking.setBookingStatus(BookingStatus.CANCELLED);

        Booking cancelledBooking = bookingRepository.save(booking);

        return mapToBookingResponse(cancelledBooking);
    }
}