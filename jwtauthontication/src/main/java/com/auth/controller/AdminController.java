package com.auth.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.StudentDTO;
import com.auth.DTO.WeatherResponse;
import com.auth.response.ApiResponse;
import com.auth.service.StudentService;
import com.auth.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private WeatherService weatherService;
	
	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
   
	@PostMapping("/save-student")
	public ResponseEntity<ApiResponse> saveStudent(@RequestBody StudentDTO studentDTO){
		Boolean saveStudent = studentService.saveStudent(studentDTO);
		
		if(saveStudent) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "student saved successfully"));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false,"student not saved"));
		}
		
		
	}
	
	@GetMapping("/get-students")
	public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() throws JsonProcessingException{
		List<StudentDTO> allStudents = studentService.getAllStudents();
		if(ObjectUtils.isEmpty(allStudents)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, "no student data Present", Collections.emptyList()));
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "student data fetched successfully",allStudents));
		}
	}
	
	@GetMapping("/get-weather")
	public ResponseEntity<ApiResponse> getWeather(@RequestParam String city) {
		try {
			WeatherResponse response = weatherService.getWeather(city);
			HashMap<String, Double> cr = new HashMap<>();
			cr.put("temp", response.getMain().getTemp());
			cr.put("feels_like", response.getMain().getFeels_like());
			return ResponseEntity.ok().body(new ApiResponse<>(false, "response fetched succesfully",response.getMain()));
		}catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse<>(false, "failed to fetch" ,e.getMessage()));
		}
		 
		 
		
	}
	
}
