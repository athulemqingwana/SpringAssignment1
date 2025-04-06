package com.ufh.SpringAssignment1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/foundation-courses")
    public List<String> getFoundationCourses() {
        return Arrays.asList("CSC 111F: Introduction to Computers and Computing", "CSC 121F: Introduction to Programming Concepts");
    }

    @GetMapping("/undergraduate-courses")
    public List<String> getUndergraduateCourses() {
        return Arrays.asList("CSC 113: Introduction to Computing and Programming Concepts", "CSC211: Advanced Programming", "CSC 223: Data Structures and Algorithms", "CSC 313: Object Oriented Programming", "CSC 323: Introduction to Computer Networks");
    }

    @GetMapping("/honours-courses")
    public List<String> getHonoursCourses() {
        return Arrays.asList("CSC 501: Mini Project", "CSC 512: Computer Networks", "CSC 515: Advanced Java", "CSC 518: Intelligent Systems");
    }
}