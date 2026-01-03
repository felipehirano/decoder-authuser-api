package com.ead.authuser.controllers;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserModel>> getOneUser(@PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findUser(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        this.userService.delete(this.userService.findUser(userId).get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
        @PathVariable UUID userId,
        @RequestBody @Validated(UserRecordDto.UserView.UserPut.class) @JsonView(UserRecordDto.UserView.UserPut.class) UserRecordDto userRecordDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
            this.userService.updateUser(userId, userRecordDto)
        );
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updateUserPassword(
        @PathVariable UUID userId,
        @RequestBody @Validated(UserRecordDto.UserView.PasswordPut.class) @JsonView(UserRecordDto.UserView.PasswordPut.class) UserRecordDto userRecordDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
            this.userService.updateUserPassword(userId, userRecordDto)
        );
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateUserImage(
        @PathVariable UUID userId,
        @RequestBody @Validated(UserRecordDto.UserView.ImagePut.class) @JsonView(UserRecordDto.UserView.ImagePut.class) UserRecordDto userRecordDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
            this.userService.updateUserImage(userId, userRecordDto)
        );
    }
}