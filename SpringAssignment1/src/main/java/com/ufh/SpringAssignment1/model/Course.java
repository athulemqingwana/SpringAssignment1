package com.ufh.SpringAssignment1.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNul;

public class Course {
    @Valid
    
    @NotBlank(message = "Id is required")
    @NotNull(message = "Id is required")
    @Size(min = 3, max = 13, message = "Course name must be at least 3 numbers")
    private Long id;
    @NotBlank(message = "Course name is required")
    @NotNull(message = "Course name is required")
    @Size(min = 2, message = "Course type must be at least 2 characters")
    private String name;
    @NotBlank(message = "Course type is required")
    @NotNull(message = "Course type is required")
    @Size(min = 6, message = "Course type must be at least 6 characters")
    private String type;  // e.g., "Foundation", "Undergraduate", "Honours"

    // Constructors
    public Course() {}

    public Course(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
