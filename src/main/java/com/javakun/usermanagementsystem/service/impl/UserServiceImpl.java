package com.javakun.usermanagementsystem.service.impl;

import com.javakun.usermanagementsystem.model.Status;
import com.javakun.usermanagementsystem.model.User;
import com.javakun.usermanagementsystem.repository.UserRepository;
import com.javakun.usermanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        user.setStatus(Status.NEW);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser =  userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> searchUsers(String username, Long id) {
        if (username != null && !username.isEmpty()) {
            return userRepository.findByUsername(username);
        } else if (id != null) {
            return userRepository.findUserById(id);
        } else {
            return List.of();
        }
    }

}
