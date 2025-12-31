package com.ead.authuser.services.impl;

import com.ead.authuser.dto.UserRecordDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.exceptions.NotFoundException;
import com.ead.authuser.exceptions.ResourceAlreadyExistsException;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findUser(UUID userId) {
        Optional<UserModel> user = this.userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new NotFoundException("User Not Found");
        }
        return user;
    }

    @Override
    public void delete(UserModel user) {
        this.userRepository.delete(user);
    }

    @Override
    public UserModel registerUser(UserRecordDto userRecordDto) {

        if(this.existsByUserName(userRecordDto.userName())) {
            throw new ResourceAlreadyExistsException("Username already exists: " + userRecordDto.userName());
        }
        
        if(this.existsByEmail(userRecordDto.email())) {
            throw new ResourceAlreadyExistsException("Email already exists: " + userRecordDto.email());
        }

        var userModel = new UserModel();
        
        BeanUtils.copyProperties(userRecordDto, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);
        
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        return this.userRepository.save(userModel);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
