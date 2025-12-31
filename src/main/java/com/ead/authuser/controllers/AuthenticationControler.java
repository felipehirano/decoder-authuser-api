package com.ead.authuser.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationControler {

    final UserService userService;

    public AuthenticationControler(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<UserModel> signup(@RequestBody UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.registerUser(userRecordDto));
    }

}
