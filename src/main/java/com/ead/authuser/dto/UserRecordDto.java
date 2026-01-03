package com.ead.authuser.dto;

import com.ead.authuser.validations.PasswordConstraint;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRecordDto(
    @NotBlank(groups = {UserView.RegistrationPost.class}, message = "Username is required")
    @Size(groups = {UserView.RegistrationPost.class}, min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    @JsonView(UserView.RegistrationPost.class)
    String userName,

    @NotBlank(groups = {UserView.RegistrationPost.class}, message = "Email is required")
    @Email(groups = {UserView.RegistrationPost.class}, message = "Email should be valid")
    @JsonView(UserView.RegistrationPost.class)
    String email,

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class}, message = "Password is required")
    @Size(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class}, min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @PasswordConstraint(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    String password,

    @NotBlank(groups = {UserView.PasswordPut.class}, message = "Old Password is required")
    @Size(groups = {UserView.PasswordPut.class}, min = 6, max = 20, message = "Old Password must be between 6 and 20 characters")
    @PasswordConstraint(groups = {UserView.PasswordPut.class})
    @JsonView(UserView.PasswordPut.class)
    String oldPassword,

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserPut.class}, message = "Full Name is required")
    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    String fullName,

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    String phoneNumber,

    @NotBlank(groups = {UserView.ImagePut.class}, message = "Image URL is required")
    @JsonView(UserView.ImagePut.class)
    String imageUrl
) {

    public interface UserView {
        interface RegistrationPost {};
        interface UserPut {};
        interface PasswordPut {};
        interface ImagePut {};    
    }
    
}
