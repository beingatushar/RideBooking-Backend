package com.beingatushar.ubercommons.service.booking;

import com.beingatushar.ubercommons.dto.BookingDTO;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface BookingService extends BaseService<BookingDTO, Long>,
        GetByRefBaseService<Booking, Long> {
}
