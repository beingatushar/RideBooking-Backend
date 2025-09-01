package com.beingatushar.ubercommons.entity.location;

import com.beingatushar.ubercommons.dto.LocationDTO;
import com.beingatushar.ubercommons.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "locations")
@Inheritance(strategy = InheritanceType.JOINED)
public class Location extends BaseEntity {
    private Double latitude;
    private Double longitude;

    public static LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        return locationDTO;
    }
}
