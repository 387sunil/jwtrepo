package com.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

}
