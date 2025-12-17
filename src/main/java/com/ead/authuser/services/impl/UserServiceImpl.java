package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserModel findUser(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public void delete(UUID userId) {
        UserModel user = this.findUser(userId);
        this.userRepository.delete(user);
    }
}
