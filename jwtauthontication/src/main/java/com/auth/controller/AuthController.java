package com.auth.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.LoginRequest;
import com.auth.DTO.UserDto;
import com.auth.response.ApiResponse;
import com.auth.service.AuthService;

@RestController
public class AuthController {
    @Autowired
	private AuthService authService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
    @PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody UserDto userDto){
		Boolean register = authService.register(userDto);
		if(register) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true,"User registerd succesfully"));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, "Unable to register"));
		}
	}
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request){
    	String token = authService.login(request);
    	if(token == null) {
    		return ResponseEntity.internalServerError().body(new ApiResponse<>(false, "failed to create token"));
    	}
    	HashMap<String,String> map = new HashMap<>();
    	map.put("username", request.getUsername());
    	map.put("token", token);
    	return ResponseEntity.ok().body(new ApiResponse<>(true, "Token Fetched Successfully", map));
    	
    }
    
}
