package com.ufh.SpringAssignment1.controller;

import com.ufh.SpringAssignment1.model.Course;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    // Static hardcoded courses (Question 1)
    private final List<String> foundationCourses = Arrays.asList(
            "CSC 111F: Introduction to Computers and Computing",
            "CSC 121F: Introduction to Programming Concepts"
    );

    private final List<String> undergraduateCourses = Arrays.asList(
            "CSC 113: Introduction to Computing and Programming Concepts",
            "CSC 211: Advanced Programming",
            "CSC 223: Data Structures and Algorithms",
            "CSC 313: Object Oriented Programming",
            "CSC 323: Introduction to Computer Networks"
    );

    private final List<String> honoursCourses = Arrays.asList(
            "CSC 501: Mini Project",
            "CSC 512: Computer Networks",
            "CSC 515: Advanced Java",
            "CSC 518: Intelligent Systems"
    );

    @GetMapping("offered/foundation")
    public List<String> getFoundationCourseNames() {
        return foundationCourses;
    }

    @GetMapping("offered/undergraduate")
    public List<String> getUndergraduateCourseNames() {
        return undergraduateCourses;
    }

    @GetMapping("offered/honours")
    public List<String> getHonoursCourseNames() {
        return honoursCourses;
    }

    // NEW: Endpoint to get all the hardcoded (offered) courses
    @GetMapping("/offered")
    public Map<String, List<String>> getOfferedCourses() {
        Map<String, List<String>> groupedCourses = new LinkedHashMap<>();

        groupedCourses.put("Foundation", foundationCourses);
        groupedCourses.put("Undergraduate", undergraduateCourses);
        groupedCourses.put("Honours", honoursCourses);

        return groupedCourses;
    }


    // CRUD and validation-based data (Question 2)
    private final Map<Long, Course> courseMap = new HashMap<>();
    private Long idCounter = 1L;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        course.setId(idCounter++);
        courseMap.put(course.getId(), course);
        return ResponseEntity.ok(course);
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
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course updatedCourse) {
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

    // Validation error handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}

