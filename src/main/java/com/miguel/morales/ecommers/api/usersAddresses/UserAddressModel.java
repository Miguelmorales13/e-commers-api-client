package com.miguel.morales.ecommers.api.usersAddresses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.morales.ecommers.api.users.UserModel;
import com.miguel.morales.ecommers.api.usersAddresses.dto.CreateUserAddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients_addresses")
@SQLDelete(sql = "UPDATE clients_addresses SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class UserAddressModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private int id;

    @NotEmpty(message = "The street and number is not empty")
    @Column(name = "street_and_number", nullable = false)
    private String streetAndNumber;

    @NotEmpty(message = "The city is not empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "The zip code is not empty")
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @NotEmpty(message = "The state is not empty")
    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "lat", nullable = true)
    private String lat;

    @Column(name = "lng", nullable = true)
    private String lng;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false, columnDefinition = "bigint")
    private UserModel user;


    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public UserAddressModel(CreateUserAddressDto dto, UserModel user) {
        this.city = dto.getCity();
        this.state = dto.getState();
        this.streetAndNumber = dto.getStreetAndNumber();
        this.zipCode = dto.getZipCode();
        this.user = user;
    }

    public UserAddressModel(CreateUserAddressDto dto) {
        this.city = dto.getCity();
        this.state = dto.getState();
        this.streetAndNumber = dto.getStreetAndNumber();
        this.zipCode = dto.getZipCode();
    }

}
