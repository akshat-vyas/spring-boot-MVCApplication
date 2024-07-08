package com.week2lectures.introductionToMVCarchitecture.controllers;


import com.week2lectures.introductionToMVCarchitecture.dto.EmployeeDTO;
import com.week2lectures.introductionToMVCarchitecture.entities.EmployeeEntity;
import com.week2lectures.introductionToMVCarchitecture.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

//    @GetMapping(path="/getMySecretMessage")
//    public String getMySecretMessage(){
//        return "sak,fbv%#@lkdj";
//    }

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeByID(@PathVariable(name="employeeID") Long id){
//        return new EmployeeDTO(id,"Akshat","akshat@gmail.com",22, LocalDate.of(2024,1,2),true);
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required=false,name="InputAge") Integer age,
                                @RequestParam(required=false) String sortBy){
//        return "hi age "+age +" Sort By " +sortBy;
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateNewEmployee(){
        return "Hello from PUT";
    }


}
