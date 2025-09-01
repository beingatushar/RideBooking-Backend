package com.beingatushar.ubercommons.service.vehicle;

import com.beingatushar.ubercommons.dto.VehicleColorDTO;
import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.base.CreateOrFindBaseService;
import com.beingatushar.ubercommons.service.base.GetByRefBaseService;

public interface VehicleColorService extends BaseService<VehicleColorDTO, Long>,
        GetByRefBaseService<VehicleColor, Long>,
        CreateOrFindBaseService<VehicleColor> {
}