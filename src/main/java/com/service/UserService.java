package com.service;

import com.dto.UserDto;
import com.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
    void deleteUserByEmail(String email);
    void updateUserByEmail(String email, UserDto updatedUserDto);
    int getTotalUserRoles();
    
}