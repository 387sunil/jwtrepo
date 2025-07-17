package com.auth.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.auth.DTO.StudentDTO;
import com.auth.controller.AdminController;
import com.auth.entity.Student;
import com.auth.repository.StudentRepo;
import com.auth.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Override
	public Boolean saveStudent(StudentDTO studentDTO) {
       Student student = mapper.map(studentDTO, Student.class);
       Student saveStudent = studentRepo.save(student);
       if(ObjectUtils.isEmpty(saveStudent)) {
    	   return false;
       }else {
    	   return true; 
       }
		
	}

	@Override
	public List<StudentDTO> getAllStudents(){
      List<Student> allStudent = studentRepo.findAll();
      List<StudentDTO> list = allStudent.stream().map(student->mapper.map(student,StudentDTO.class)).collect(Collectors.toList());
      return list;
	}

}
