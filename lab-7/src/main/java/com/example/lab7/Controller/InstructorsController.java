package com.example.lab7.Controller;

import com.example.lab7.Model.Instructors;
import com.example.lab7.Service.InstructorsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/instructor")
@RequiredArgsConstructor
public class InstructorsController {
    private final InstructorsService instructorsService;

    @GetMapping("/get")
    public ResponseEntity getAllInstructors(){
        ArrayList<Instructors> instructors = instructorsService.getInstructorsList();
        return ResponseEntity.status(200).body(instructors);
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid  Instructors instructors, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        instructorsService.addInstructor(instructors);
        return ResponseEntity.status(200).body(instructors);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInstructor(@PathVariable String id, @RequestBody @Valid Instructors instructor, Errors errors){
        boolean isUpdated = instructorsService.updateInstructor(id, instructor);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated){
            return ResponseEntity.status(200).body("instructor updated successfully");
        }
        return ResponseEntity.status(400).body("id not found");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable String id){
        boolean isDeleted = instructorsService.deleteInstructor(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("instructor deleted successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @GetMapping("/get-instructor-by-id/{id}")
    public ResponseEntity getInstructorById(@PathVariable String id){
        Instructors instructor = instructorsService.getInstructorById(id);
        if(instructor == null){
            return ResponseEntity.status(400).body("id not found");
        }
        return ResponseEntity.status(200).body(instructor);
    }

    @GetMapping("/get-instructor-courses/{id}")
    public ResponseEntity getInstrcutorCourses(@PathVariable String id){
        String courses = instructorsService.viewCoursesInstructor(id);
        if(courses == null){
            return ResponseEntity.status(400).body("id not found");
        }
        return ResponseEntity.status(200).body(courses);
    }

    @GetMapping("/get-instructor-by-department/{department}")
    public ResponseEntity getInstructorByDepartment(@PathVariable String department){
        ArrayList<Instructors> instructorsByDepartment = instructorsService.viewInstructorsByDepartment(department);
        if(instructorsByDepartment == null){
            return ResponseEntity.status(400).body("department not have instructors");
        }
        return ResponseEntity.status(200).body(instructorsByDepartment);
    }

    @PutMapping("/change-instructor-department/{id}")
    public ResponseEntity changeInstructorDepartment(@PathVariable String id ,@RequestBody @Valid String  department,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdatedDepartment = instructorsService.changeInstructorDepartment(id, department);
        if(isUpdatedDepartment){
            return ResponseEntity.status(200).body("department changed successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }
}
