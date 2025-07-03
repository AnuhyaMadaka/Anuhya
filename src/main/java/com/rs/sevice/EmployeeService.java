package com.rs.sevice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rs.dto.EmployeeDto;

public interface EmployeeService {

public	ResponseEntity<String> add(EmployeeDto data);

public	ResponseEntity<List<EmployeeDto>> getAll();

public	ResponseEntity<?> getById(Long id);

public	ResponseEntity<String> deleteById(Long id);

public ResponseEntity<?> updateById(long id, EmployeeDto data);


}
