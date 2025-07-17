package com.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
	@GetMapping("/")
	public ResponseEntity<?> getDetails(){
		return new ResponseEntity<>("this is user dashboard", HttpStatus.OK);
	}
	
}
