package com.example.courses.controllers;

import com.example.courses.models.Course;
import com.example.courses.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }


    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@RequestBody Course course, @PathVariable Long courseId) {
        return courseService.updateCourse(course, courseId);
    }


    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Successfully deleted course", HttpStatusCode.valueOf(204));
    }
}
