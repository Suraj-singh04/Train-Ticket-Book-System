package com.trainticket.booking_system.service;

import java.util.List;

import com.trainticket.booking_system.dto.request.CreateBookingRequest;
import com.trainticket.booking_system.dto.response.BookingResponse;

public interface BookingService {

    BookingResponse createBooking(CreateBookingRequest request);

    List<BookingResponse> getAllBookings();

    BookingResponse getBookingById(String bookingId);

    BookingResponse cancelBooking(String bookingId);
}