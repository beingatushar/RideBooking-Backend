package com.beingatushar.ubercommons.controller.booking;

import com.beingatushar.ubercommons.dto.BookingDTO;
import com.beingatushar.ubercommons.service.booking.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid BookingDTO booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.create(booking));
    }

    @GetMapping("/{id}")
    ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody @Valid BookingDTO booking) {
        return ResponseEntity.ok(bookingService.update(id, booking));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.deleteById(id));
    }

    @GetMapping
    ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAll());
    }
}
