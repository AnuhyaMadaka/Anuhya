package com.rs.sevice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rs.dto.LeaveRequestDto;

public interface LeaveRequestService {

public	ResponseEntity<String> add(LeaveRequestDto data);

public ResponseEntity<List<LeaveRequestDto>> getAll();

public ResponseEntity<?> getById(Long id);

public ResponseEntity<String> deleteById(Long id);

public ResponseEntity<String> delete(Long id);

 public ResponseEntity<String> updateLeave(long id, LeaveRequestDto data);

}
