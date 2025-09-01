package com.beingatushar.ubercommons.service.vehicle;

import com.beingatushar.ubercommons.dto.VehicleBrandDTO;
import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.CreateOrFindBaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface VehicleBrandService extends BaseService<VehicleBrandDTO, Long>,
        GetByRefBaseService<VehicleBrand, Long>,
        CreateOrFindBaseService<VehicleBrand> {
}