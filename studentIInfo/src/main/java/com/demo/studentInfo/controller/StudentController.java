package com.demo.studentInfo.controller;


import com.demo.studentInfo.entity.Student;
import com.demo.studentInfo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    /**
     * Get ALl student Details
     * @return
     */
    @GetMapping("/getAllStudents")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if(!students.isEmpty() ){
                return  ResponseEntity.ok(students);
            }
            else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }

        } catch (Exception e) {
            log.error("Error occurred while fetching students details: {}", e.getMessage(), e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching students details");

        }

    }

    /**
     * For Capaturing student details based on studentID
     * @param id
     * @return
     */
    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try{
            Student student=studentService.getStudentById(id);
            if (student== null){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Found");
            }
            return ResponseEntity.ok(student);
            } catch (Exception e) {
                log.error("Error occurred while fetching student details by Id: {}", e.getMessage(), e);
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching student details by Id");
            }
        }


    /**
     * Post mapping to create a new student
     * @param student
     * @return
     */

    @PostMapping("/createStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try{
            ResponseEntity<String> newStudent=studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student account is created");
        }
        catch (Exception e){
            log.error("Error occurred while creating student : {}", e.getMessage(), e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating student ");
        }

    }

    /**
     *
     * @param id
     * @param studentDetails
     * @return
     */

    @PutMapping("/updateById/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentService.updateStudent(id, studentDetails);
    }

    /**
     *
     * @param id
     * @return
     */

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Deleted SuccesFully");
    }


}
