package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.LocationDTO;
import com.beingatushar.ubercommons.entity.location.Location;

public class LocationMapper {

    public static LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        return locationDTO;
    }

    public static Location toEntity(LocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }
        Location location = new Location();
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        return location;
    }
}