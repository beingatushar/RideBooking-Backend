package com.beingatushar.ubercommons.service.vehicle;

import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.CreateOrFindBaseService;

public interface VehicleService extends BaseService<Vehicle, Long>,
        CreateOrFindBaseService<Vehicle> {
}