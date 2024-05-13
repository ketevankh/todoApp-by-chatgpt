package com.example.chatgpttodoapp.service;

import com.example.chatgpttodoapp.model.User;

public interface UserService {
    User getUserByUsername(String username);
}
