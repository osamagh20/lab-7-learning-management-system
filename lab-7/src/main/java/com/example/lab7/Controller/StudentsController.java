package com.example.lab7.Controller;

import com.example.lab7.Model.Courses;
import com.example.lab7.Model.Students;
import com.example.lab7.Service.StudentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsController {
    private final StudentsService studentsService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        ArrayList<Students> students = studentsService.getStudentsList();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Students student, Errors error) {
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentsService.addStudents(student);
        return ResponseEntity.status(200).body("Success added student");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Students student, Errors error) {
        boolean isUpdated = studentsService.updateStudents(id, student);
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated){
            return ResponseEntity.status(200).body("Success updated student");
        }
            return ResponseEntity.status(400).body("id not found");

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        boolean isDeleted = studentsService.deleteStudents(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Success deleted student");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @GetMapping("/get-gpa/{id}")
    public ResponseEntity viewTheGPA(@PathVariable String id) {
        double studentGPA = studentsService.viewGPA(id);
        if(studentGPA == 0){
            return ResponseEntity.status(400).body("the student does not exist");
        }
        return ResponseEntity.status(200).body(studentGPA);
    }

    @GetMapping("/view-courses/{id}")
    public ResponseEntity viewCourses(@PathVariable String id) {
        String courses = studentsService.viewCourses(id);
        if(courses == null){
            return ResponseEntity.status(400).body("the student does not exist or not have courses");
        }
        return ResponseEntity.status(200).body(courses);
    }

    @PutMapping("/change-courses/{id}")
    public ResponseEntity changeTheCourses(@PathVariable String id, @RequestBody @Valid String courses, Errors error) {
        boolean isChanged = studentsService.changeCourses(id,courses);
        if(error.hasErrors()){
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isChanged){
            return ResponseEntity.status(200).body("Success changed");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @GetMapping("/get-greater-than-gpa/{GPA}")
    public ResponseEntity getStudentsGreaterThanGPA(@PathVariable double GPA){
        ArrayList<Students> studentsGPA = studentsService.getStudentsGreaterThanGPA(GPA);
        if(studentsGPA == null){
            return ResponseEntity.status(400).body("Not have GPA students greater than your GPA entered");
        }
        return ResponseEntity.status(200).body(studentsGPA);
    }

}
