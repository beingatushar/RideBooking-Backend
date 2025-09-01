package com.beingatushar.ubercommons.entity.user;

import com.beingatushar.ubercommons.dto.UserDTO;
import com.beingatushar.ubercommons.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

//    @OneToMany(mappedBy = "user")
//    private List<Booking> bookings;
//
//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews;

}
