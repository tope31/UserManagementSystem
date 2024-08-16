package com.javakun.usermanagementsystem.controller;

import com.javakun.usermanagementsystem.dto.UserDto;
import com.javakun.usermanagementsystem.mapper.UserMapper;
import com.javakun.usermanagementsystem.model.User;
import com.javakun.usermanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> responseDto = userMapper.toDtoList(userService.getAllUsers());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        userService.addUser(entity);
        UserDto responseDto = userMapper.toDto(entity);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted Successful", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
        User entity = userMapper.toEntity(userDto);
        userService.updateUser(id, entity);
        UserDto responseDto = userMapper.toDto(entity);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam(required = false) String username,
                                                     @RequestParam(required = false) Long id) {
        List<User> users = userService.searchUsers(username, id);
        List<UserDto> responseDto = userMapper.toDtoList(users);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
