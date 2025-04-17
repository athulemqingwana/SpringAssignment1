package com.ufh.SpringAssignment1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Course {

    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Course type is required")
    @Pattern(regexp = "Foundation|Undergraduate|Honours", message = "Type must be Foundation, Undergraduate, or Honours")
    private String type;

    public Course() {}

    public Course(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

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

