package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Courses {
    @NotEmpty(message = "Enter the ID")
    @Size(min=4,message = "Enter the ID at least 4 digits ")
    @Pattern(regexp = "^2\\d+$",message = "Enter just digits and start with number 2")
    private String id;
    @NotEmpty(message = "Enter the Name")
    @Size(min=4,message = "Enter the name at least 4 characters ")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Enter just characters")
    private String name;
    @NotEmpty(message = "Enter the department")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Enter just characters")
    private String department;
    @AssertFalse
    private boolean isActive;
    @NotNull(message = "Enter the limit")
    @Positive
    private int limit;
}
