package com.ead.authuser.models;

import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name="tb_users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel extends RepresentationModel<UserModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID userId;

    @Column(unique = true, nullable = false, length = 50)
    String userName;

    @Column(unique = true, nullable = false, length = 50)
    String email;

    @Column(nullable = false, length = 20)
    @JsonIgnore
    String password;

    @Column(nullable = false, length = 150)
    String fullName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    UserType userType;

    @Column(length = 20)
    String phoneNumber;

    @Column(length = 255)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String imageUrl;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime lastUpdateDate;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
