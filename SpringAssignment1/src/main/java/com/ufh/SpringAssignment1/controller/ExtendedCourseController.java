package com.ufh.SpringAssignment1.controller;

import com.ufh.SpringAssignment1.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class ExtendedCourseController {

    private final Map<Long, Course> courseMap = new HashMap<>();
    private Long idCounter = 1L;

    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course) {
        course.setId(idCounter++);
        courseMap.put(course.getId(), course);
        return course;
    }

    @GetMapping
    public Collection<Course> getAllCourses() {
        return courseMap.values();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseMap.get(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,@Valid @RequestBody Course updatedCourse) {
        Course course = courseMap.get(id);
        if (course != null) {
            course.setName(updatedCourse.getName());
            course.setType(updatedCourse.getType());
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        if (courseMap.remove(id) != null) {
            return ResponseEntity.ok("Course with ID " + id + " was deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- FILTER: Foundation Courses ---
    @GetMapping("/foundation")
    public List<Course> getFoundationCourses() {
        return courseMap.values().stream()
                .filter(course -> "Foundation".equalsIgnoreCase(course.getType()))
                .collect(Collectors.toList());
    }

    @GetMapping("/undergraduate")
    public List<Course> getUndergraduateCourses() {
        return courseMap.values().stream()
                .filter(course -> "Undergraduate".equalsIgnoreCase(course.getType()))
                .collect(Collectors.toList());
    }

    @GetMapping("/honours")
    public List<Course> getHonoursCourses() {
        return courseMap.values().stream()
                .filter(course -> "Honours".equalsIgnoreCase(course.getType()))
                .collect(Collectors.toList());
    }
}

