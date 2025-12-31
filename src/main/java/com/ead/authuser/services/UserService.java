package com.ead.authuser.services;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();
    Optional<UserModel> findUser(UUID userId);
    void delete(UserModel user);
    UserModel registerUser(UserRecordDto userRecordDto);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
