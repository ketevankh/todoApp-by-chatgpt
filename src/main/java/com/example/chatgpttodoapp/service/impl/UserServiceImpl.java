package com.example.chatgpttodoapp.service.impl;

import com.example.chatgpttodoapp.model.User;
import com.example.chatgpttodoapp.repository.UserRepository;
import com.example.chatgpttodoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}