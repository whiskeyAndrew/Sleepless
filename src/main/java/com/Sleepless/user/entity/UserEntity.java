package com.Sleepless.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "users")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "UserEntity")
public class UserEntity  {

    @Id
    private String id;
    private Instant creationDate;

    private String login;

    private String displayName;

    private String type;

    private String broadcasterType;

    private String description;

    private String email;

    private Instant accountCreationTime;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(displayName, that.displayName) && Objects.equals(type, that.type) && Objects.equals(broadcasterType, that.broadcasterType) && Objects.equals(description, that.description) && Objects.equals(email, that.email) && Objects.equals(accountCreationTime, that.accountCreationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, displayName, type, broadcasterType, description, email, accountCreationTime);
    }
}
