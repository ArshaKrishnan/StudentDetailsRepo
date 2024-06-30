package com.demo.studentInfo.repository;

import com.demo.studentInfo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {

    boolean existsByNameAndAgeAndEmailAndTerm(String name, int age, String email, String term);
}
