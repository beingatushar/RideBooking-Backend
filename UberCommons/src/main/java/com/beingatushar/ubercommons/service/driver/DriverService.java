package com.beingatushar.ubercommons.service.driver;

import com.beingatushar.ubercommons.dto.DriverDTO;
import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface DriverService extends BaseService<DriverDTO, Long>,
        GetByRefBaseService<Driver, Long> {

}
