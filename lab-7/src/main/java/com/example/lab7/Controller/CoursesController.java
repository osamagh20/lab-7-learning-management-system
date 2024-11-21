package com.example.lab7.Controller;

import com.example.lab7.Model.Courses;
import com.example.lab7.Service.CoursesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CoursesController {
    private final CoursesService coursesService;


    @GetMapping("/get")
    public ResponseEntity getAllCourses() {
        ArrayList<Courses> courses = coursesService.getCoursesList();
        return ResponseEntity.status(200).body(courses);
    }


    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Courses courses, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        coursesService.addCoursesList(courses);
        return ResponseEntity.status(200).body("Success add course");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Courses courses, Errors error) {
        boolean isUpdated = coursesService.updateCourse(id, courses);
        if(error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated) {
            return ResponseEntity.status(200).body("Success update course");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        boolean isDeleted = coursesService.deleteCourse(id);
        if(isDeleted) {
            return ResponseEntity.status(200).body("Success delete course");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @GetMapping("/get-course-by-id/{id}")
    public ResponseEntity findCourseById(@PathVariable String id) {
        Courses course = coursesService.getCourseById(id);
        if(course == null) {
            return ResponseEntity.status(400).body("course not found");
        }
        return ResponseEntity.status(200).body(course);
    }

    @GetMapping("/get-course-activated")
    public ResponseEntity getCourseActivated () {
        ArrayList<Courses> coursesIsActivated = coursesService.getCoursesActivated();
        if(!coursesIsActivated.isEmpty()) {
            return ResponseEntity.status(200).body(coursesIsActivated);
        }
        return ResponseEntity.status(400).body("not have activated courses");


    }

    @GetMapping("/get-course-not-activated")
    public ResponseEntity getCourseNotActivated () {
        ArrayList<Courses> coursesNotActivated = coursesService.getCoursesNotActivated();
        if(!coursesNotActivated.isEmpty()) {
            return ResponseEntity.status(200).body(coursesNotActivated);

        }
        return ResponseEntity.status(400).body("We do not have an activated course.");


    }

    @GetMapping("/get-course-by-department/{department}")
    public ResponseEntity getCourseByDepartment(@PathVariable String department) {
        ArrayList<Courses> coursesByDepartment = coursesService.getCoursesByDepartment(department);
        if(coursesByDepartment == null) {
            return ResponseEntity.status(400).body("department not have any courses");
        }
        return ResponseEntity.status(200).body(coursesByDepartment);
    }

    @PutMapping("/change-limi-course/{id}")
    public ResponseEntity changeLimitOfCourse(@PathVariable String id,@RequestBody int limit) {
        boolean isUpdated = coursesService.changeLimitOfCourse(id, limit);
        if(isUpdated) {
            return ResponseEntity.status(200).body("Success change limit of course");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @PutMapping("/make-active-course/{id}")
    public ResponseEntity makeActiveCourse(@PathVariable String id) {
        boolean isActivated = coursesService.isActiveCourse(id);
        if(isActivated) {
            return ResponseEntity.status(200).body("Activated course");
        }
        return ResponseEntity.status(400).body("id not found");
    }
}
