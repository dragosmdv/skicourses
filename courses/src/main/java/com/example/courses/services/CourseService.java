package com.example.courses.services;

import com.example.courses.models.Course;
import com.example.courses.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        course.setId(0L);
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course, Long courseId) {
        course.setId(courseId);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        Course course = courseRepository.getById(courseId);
        courseRepository.delete(course);
    }
}
