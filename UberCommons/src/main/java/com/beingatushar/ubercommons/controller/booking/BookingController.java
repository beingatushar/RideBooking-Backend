package com.beingatushar.ubercommons.controller.booking;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.service.BaseService;
import com.beingatushar.ubercommons.service.booking.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController extends BaseRestController<Booking, Long> {
    private final BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    protected BaseService<Booking, Long> getService() {
        return this.bookingService;
    }

    @PostMapping
    ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(booking));
    }

    @GetMapping("/{id}")
    ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return ResponseEntity.ok(update(id, booking));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteBooking(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }

    @GetMapping
    ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(getAll());
    }
}
