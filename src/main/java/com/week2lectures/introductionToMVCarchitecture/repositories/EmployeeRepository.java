package com.week2lectures.introductionToMVCarchitecture.repositories;

import com.week2lectures.introductionToMVCarchitecture.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
