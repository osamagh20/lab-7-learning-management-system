package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Students {
    @NotEmpty(message = "Enter the ID")
    @Size(min=4,message = "Enter the ID at least 4 digits ")
    @Pattern(regexp = "^0\\d+$",message = "Enter just digits and start with number 0")
    private String id;
    @NotEmpty(message = "Enter the Name")
    @Size(min=4,message = "Enter the name at least 4 characters ")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Enter just characters")
    private String name;
    @NotNull(message = "Enter the age")
    @Positive
    @Min(18)
    private int age;
    @NotEmpty(message = "Enter the gender")
    @Pattern(regexp = "^(Male|Female)$",message = "Enter the gender Male or Female")
    private String gender;
    @Email
    private String email;
    @NotEmpty(message = "Enter the phone number")
    @Pattern(regexp = "^05\\d{8}$",message = "Start with 05 and exactly 10 digits")
    private String phone;
    @NotNull(message = "Enter th GPA")
    @Positive
    private double GPA;
    @NotEmpty(message = "Enter courses")
    private String courses;

}
