package com.beingatushar.ubercommons.entity.user;

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

//    @OneToMany(mappedBy = "user")
//    private List<Booking> bookings;
//
//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews;

}
