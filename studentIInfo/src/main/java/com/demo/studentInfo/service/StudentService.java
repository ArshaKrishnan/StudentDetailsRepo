package com.demo.studentInfo.service;

import com.demo.studentInfo.entity.Student;
import com.demo.studentInfo.entity.TransactionRequest;
import com.demo.studentInfo.feign.StudentInterface;
import com.demo.studentInfo.repository.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private StudentInterface studentInterface;




    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
    }

    public ResponseEntity<String> createStudent(Student student) {
        if (isStudentExists(student)) {
            throw new IllegalArgumentException("Student with the same name, age, emailId, and term already exists");
        }
        Student newStudent = studentRepository.save(student);
        //Will call the Transaction Service to save fee details using Eureka and openFeign
        TransactionRequest transactions=new TransactionRequest();
        transactions.setId(student.getId());
        transactions.setTerm(student.getTerm());
        ResponseEntity<String> value= studentInterface.addTransaction(transactions);
        return newStudent != null &&  !value.getBody().isEmpty() ?
                ResponseEntity.ok("Account saved successfully") :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add student");


    }

    private boolean isStudentExists(Student student) {
        // Check if a student with the same name, age, emailId, and term exists
        return studentRepository.existsByNameAndAgeAndEmailAndTerm(
                student.getName(), student.getAge(), student.getEmail(), student.getTerm());
    }
    public ResponseEntity<?> updateStudent(Long id, Student studentDetails) {
        try {

            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));
            if (studentDetails.getId().equals(student.getId())) {
                student.setId(studentDetails.getId());
            }
            if (studentDetails.getName() != null) {
                student.setName(studentDetails.getName());
            }
            if (studentDetails.getAge() >= 0) {
                student.setAge(studentDetails.getAge());
            }
            if (studentDetails.getGender() != null) {
                student.setGender(studentDetails.getGender());
            }
            if (studentDetails.getTerm() != null) {
                student.setTerm(studentDetails.getTerm());
            }

            return ResponseEntity.status(HttpStatus.OK).body(studentRepository.save(student));

        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error occurred while updating Student with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with id: " + id));

        studentRepository.delete(student);
    }


}
