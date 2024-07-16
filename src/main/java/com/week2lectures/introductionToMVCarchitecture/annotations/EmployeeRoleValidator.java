package com.week2lectures.introductionToMVCarchitecture.annotations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String>{
 @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext){
     List<String> roles =List.of("USER","ADMIN");
     return roles.contains(inputRole);
 }
}
