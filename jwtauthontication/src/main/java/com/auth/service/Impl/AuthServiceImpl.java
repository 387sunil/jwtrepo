package com.auth.service.Impl;


import java.net.Authenticator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.auth.DTO.LoginRequest;
import com.auth.DTO.UserDto;
import com.auth.entity.User;
import com.auth.repository.UserRepository;
import com.auth.service.AuthService;
import com.auth.service.JwtService;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
	private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired 
    private JwtService jwtService;
	@Override
	public Boolean register(UserDto userDto) {
		User u = new User();
		u.setUsername(userDto.getUsername());
		u.setPassword(passwordEncoder.encode(userDto.getPassword()));
		u.setRole(userDto.getRole());
		User save = userRepository.save(u);
		if(ObjectUtils.isEmpty(save)) {
			return false;
		}else {
			return true;
		}

	}

	@Override
	public String login(LoginRequest request) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		if(authenticate.isAuthenticated()) {
			return jwtService.generateToken(request.getUsername());
		}
		return null;
	}

	@Override
	public List<User> getUserDtls() {
		return userRepository.findAll();
	}

}
