package com.beingatushar.ubercommons.service.vehicle;

import com.beingatushar.ubercommons.dto.VehicleDTO;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.CreateOrFindBaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface VehicleService extends BaseService<VehicleDTO, Long>,
        GetByRefBaseService<Vehicle, Long>
        , CreateOrFindBaseService<Vehicle> {
}