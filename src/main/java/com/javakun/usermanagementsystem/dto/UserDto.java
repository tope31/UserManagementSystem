package com.javakun.usermanagementsystem.dto;

import com.javakun.usermanagementsystem.model.Status;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private Status status;
    private String email;
}
