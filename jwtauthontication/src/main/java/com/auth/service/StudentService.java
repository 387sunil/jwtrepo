package com.auth.service;

import java.util.List;

import com.auth.DTO.StudentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StudentService {
   public Boolean saveStudent(StudentDTO studentDTO);
   public List<StudentDTO> getAllStudents();
}
