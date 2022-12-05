package com.stegnin.jiraved.storage.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Alexandr Stegnin
 */
@Data
@Entity
@Table(name = "app_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUser {

    @Id
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone")
    String phone;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    public String getUsername() {
        if (Objects.isNull(firstName) && Objects.isNull(lastName)) {
            return phone;
        }
        if (Objects.nonNull(firstName) && Objects.nonNull(lastName)) {
            return "%s %s".formatted(firstName, lastName);
        }
        if (Objects.nonNull(firstName)) {
            return firstName;
        }
        return lastName;
    }

}
