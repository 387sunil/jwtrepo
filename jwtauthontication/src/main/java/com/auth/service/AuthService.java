package com.auth.service;

import java.util.List;

import com.auth.DTO.LoginRequest;
import com.auth.DTO.UserDto;
import com.auth.entity.User;

public interface AuthService {
   public Boolean register(UserDto userDto);
   public String login(LoginRequest request);
   public List<User> getUserDtls();

}
