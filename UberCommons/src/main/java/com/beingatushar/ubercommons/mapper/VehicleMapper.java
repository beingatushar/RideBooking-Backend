package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.VehicleBrandDTO;
import com.beingatushar.ubercommons.dto.VehicleColorDTO;
import com.beingatushar.ubercommons.dto.VehicleDTO;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
        vehicleDTO.setCapacity(vehicle.getCapacity());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        if (vehicle.getBrand() != null) {
            vehicleDTO.setBrand(toDTO(vehicle.getBrand()));
        }
        if (vehicle.getColor() != null) {
            vehicleDTO.setColor(toDTO(vehicle.getColor()));
        }
        return vehicleDTO;
    }

    public static VehicleBrandDTO toDTO(VehicleBrand brand) {
        if (brand == null) {
            return null;
        }
        return new VehicleBrandDTO(brand.getId(), brand.getName());
    }

    public static VehicleColorDTO toDTO(VehicleColor color) {
        if (color == null) {
            return null;
        }
        return new VehicleColorDTO(color.getId(), color.getName());
    }

    public static Vehicle toEntity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setCapacity(vehicleDTO.getCapacity());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        if (vehicleDTO.getBrand() != null) {
            vehicle.setBrand(toEntity(vehicleDTO.getBrand()));
        }
        if (vehicleDTO.getColor() != null) {
            vehicle.setColor(toEntity(vehicleDTO.getColor()));
        }
        return vehicle;
    }

    public static VehicleBrand toEntity(VehicleBrandDTO brandDTO) {
        if (brandDTO == null) {
            return null;
        }
        VehicleBrand brand = new VehicleBrand();
        brand.setId(brandDTO.getId());
        brand.setName(brandDTO.getName());
        return brand;
    }

    public static VehicleColor toEntity(VehicleColorDTO colorDTO) {
        if (colorDTO == null) {
            return null;
        }
        VehicleColor color = new VehicleColor();
        color.setId(colorDTO.getId());
        color.setName(colorDTO.getName());
        return color;
    }
}