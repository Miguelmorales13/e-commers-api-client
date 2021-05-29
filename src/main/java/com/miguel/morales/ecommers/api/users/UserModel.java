package com.miguel.morales.ecommers.api.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miguel.morales.ecommers.api.users.dto.CreateUserDto;
import com.miguel.morales.ecommers.api.usersAddresses.UserAddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "second_last_name", nullable = false)
    private String secondLastName;

    @NotEmpty(message = "The email is not empty")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore()
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserAddressModel> addresses;

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
        updatedAt = new Date();
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public UserModel(CreateUserDto dto) {
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.secondLastName = dto.getSecondLastName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }
}
