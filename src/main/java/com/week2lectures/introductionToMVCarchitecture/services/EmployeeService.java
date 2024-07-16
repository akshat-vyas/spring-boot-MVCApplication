package com.week2lectures.introductionToMVCarchitecture.services;

import com.week2lectures.introductionToMVCarchitecture.dto.EmployeeDTO;
import com.week2lectures.introductionToMVCarchitecture.entities.EmployeeEntity;
import com.week2lectures.introductionToMVCarchitecture.exceptions.ResourceNotFoundException;
import com.week2lectures.introductionToMVCarchitecture.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeByID(Long id) {
        isExistsByEmployeeID(id);
        return employeeRepository.findById(id).map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return
                employeeEntities
                        .stream()
                        .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                        .collect(Collectors.toList());


    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEntity= employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateNewEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistsByEmployeeID(employeeId);

        EmployeeEntity employeeEntity= modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);

    }
    public void isExistsByEmployeeID(Long employeeId){
        boolean exists= employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeID(employeeId);

        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeID(employeeId);
        EmployeeEntity employeeEntity= employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);

        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }

}
