package com.beingatushar.ubercommons.service.booking.impl;

import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.BookingRepository;
import com.beingatushar.ubercommons.service.booking.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Long id, Booking booking) {
        Booking bookingToUpdate = getById(id);
        BeanUtils.copyProperties(booking, bookingToUpdate);
        return bookingRepository.save(bookingToUpdate);
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking with id " + id + " not found"));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }
}
