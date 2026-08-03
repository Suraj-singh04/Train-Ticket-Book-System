package com.trainticket.booking_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainticket.booking_system.dto.request.CreateBookingRequest;
import com.trainticket.booking_system.dto.response.BookingResponse;
import com.trainticket.booking_system.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBooking(request));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {

        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @PathVariable String bookingId) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(bookingId));
    }
}
