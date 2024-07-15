package com.week2lectures.introductionToMVCarchitecture.controllers;


import com.week2lectures.introductionToMVCarchitecture.dto.EmployeeDTO;
import com.week2lectures.introductionToMVCarchitecture.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {

//    @GetMapping(path="/getMySecretMessage")
//    public String getMySecretMessage(){
//        return "sak,fbv%#@lkdj";
//    }

//    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;


    public EmployeeController (ModelMapper modelMapper, EmployeeService employeeService){

        this.employeeService=employeeService;
    }


    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name="employeeID") Long id){

        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeByID(id);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required=false,name="InputAge") Integer age,
                                @RequestParam(required=false) String sortBy){
//        return "hi age "+age +" Sort By " +sortBy;
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
        EmployeeDTO savedEmployee= employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateNewEmployeeById(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateNewEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted=employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId,
                                     @RequestBody Map<String,Object> updates){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }


}
