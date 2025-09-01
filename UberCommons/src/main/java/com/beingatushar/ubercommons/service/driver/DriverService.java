package com.beingatushar.ubercommons.service.driver;

import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface DriverService extends BaseService<Driver, Long>,
        GetByRefBaseService<Driver, Long> {
}
