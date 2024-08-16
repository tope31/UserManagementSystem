package com.javakun.usermanagementsystem.service;

import com.javakun.usermanagementsystem.dto.UserDto;
import com.javakun.usermanagementsystem.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    User findUserById(Long id);
    void deleteUser(Long id);
    User updateUser(Long id, User user);
    List<User> searchUsers(String username, Long id);
}
