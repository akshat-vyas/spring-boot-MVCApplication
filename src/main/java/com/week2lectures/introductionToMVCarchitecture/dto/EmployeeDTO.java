package com.week2lectures.introductionToMVCarchitecture.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of Employee can not be empty.")
    @Size(min =3,max=10 , message = "Number of characters in the name should be in the range 3 to 10.")
    private String name;

    @NotBlank(message = "The email of Employee can not be blank.")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "The age of Employee can not be blank.")
    @Max(value = 80, message = "Age of Employee can not be greater than 80.")
    @Min(value = 18, message = "Age of Employee can not be less than 18.")
    private Integer age;

    @NotBlank(message = "The role of Employee can not be blank.")
    @Pattern(regexp = "^(ADMIN|USER)$" , message = "The role of Employee can either be ADMIN or USER.")
    private String role;

    @NotNull(message = "Salary of Employee should not be null.")
    @Positive(message = "Salary of employee should be positive.")
    @Digits(integer = 6, fraction = 2, message = "Salary can be in the form XXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "Date of joining for Employee can not be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active.")
    @JsonProperty("isActive")
    private Boolean isActive;

}
