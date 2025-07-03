package com.rs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	
public	Optional<Employee> findByMobileNo(String mobilNo);

public	Optional<Employee> findByEmailId(String emailId);

}

