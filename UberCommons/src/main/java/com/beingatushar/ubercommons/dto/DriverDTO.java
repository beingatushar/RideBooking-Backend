package com.beingatushar.ubercommons.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DriverDTO extends UserDTO {

    private VehicleDTO vehicle;
}