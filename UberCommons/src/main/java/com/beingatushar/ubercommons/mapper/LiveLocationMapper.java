package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.LiveLocationDTO;
import com.beingatushar.ubercommons.entity.location.LiveLocation;

public class LiveLocationMapper {

    public static LiveLocationDTO toDTO(LiveLocation liveLocation) {
        if (liveLocation == null) {
            return null;
        }
        LiveLocationDTO liveLocationDTO = new LiveLocationDTO();
        liveLocationDTO.setId(liveLocation.getId());
        liveLocationDTO.setLatitude(liveLocation.getLatitude());
        liveLocationDTO.setLongitude(liveLocation.getLongitude());
        liveLocationDTO.setBookingId(liveLocation.getBooking().getId());
        liveLocationDTO.setUpdatedDate(liveLocation.getUpdatedDate());
        return liveLocationDTO;
    }

    public static LiveLocation toEntity(LiveLocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }
        LiveLocation location = new LiveLocation();
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());
        location.setId(locationDTO.getId());
        return location;
    }
}