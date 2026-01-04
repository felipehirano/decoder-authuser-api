package com.ead.authuser.services;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    List<UserModel> findAll();
    Optional<UserModel> findUser(UUID userId);
    void delete(UserModel user);
    UserModel registerUser(UserRecordDto userRecordDto);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    UserModel updateUser(UUID userId, UserRecordDto userRecordDto);
    UserModel updateUserPassword(UUID userId, UserRecordDto userRecordDto);
    UserModel updateUserImage(UUID userId, UserRecordDto userRecordDto);
    Page<UserModel> findAll(Pageable pageable);
}
