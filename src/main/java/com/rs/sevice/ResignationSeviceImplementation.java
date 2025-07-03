package com.rs.sevice;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rs.Entity.Employee;
import com.rs.Entity.Resignation;
import com.rs.Exception.DuplicateFound;
import com.rs.Exception.ResourceNotFound;
import com.rs.dto.ResignationDto;
import com.rs.repo.EmployeeRepository;
import com.rs.repo.ResignationRepository;


@Service
public class ResignationSeviceImplementation implements ResignationService {

    @Autowired
    private ResignationRepository resignationRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> applyResignation(Long id, ResignationDto dto) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFound("Employee not found with ID: " + id);
        }

        Optional<Resignation> existing = resignationRepo.findByEmployeeId(id);
        if (existing.isPresent()) {
            throw new DuplicateFound("Employee has already applied for resignation.");
        }

        Resignation resignation = new Resignation();
        resignation.setDateOfApplying(dto.getDateOfApplying());
        resignation.setReason(dto.getReason());
        resignation.setStatus(dto.getStatus());
        resignation.setEmployee(employee.get());

        resignationRepo.save(resignation);
        return ResponseEntity.ok("Resignation applied successfully.");
    }

    @Override
    public ResponseEntity<List<ResignationDto>> getAll() {
        List<Resignation> data = resignationRepo.findAll();
        if (data.isEmpty()) {
            throw new ResourceNotFound("No resignation records found.");
        }

        List<ResignationDto> dtoList = data.stream()
            .map(res -> modelMapper.map(res, ResignationDto.class))
            .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
    
    
@Override
public ResponseEntity<?> getById(Long id){
	try {
		Optional<Resignation> data=resignationRepo.findById(id);
		if(data.isPresent()) {
		ResignationDto dto=modelMapper.map(data.get(),ResignationDto.class);
		return ResponseEntity.ok(dto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID:"+id);
		}
		}
	catch(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:"+e.getMessage());
	}
	}
@Override
public ResponseEntity<String> deleteById(Long id) {
    Optional<Resignation> data = resignationRepo.findById(id);

    if (data.isPresent()) {
    	System.out.println("deleting : "+id);
        resignationRepo.deleteById(id);  
        return ResponseEntity.status(HttpStatus.OK).body("Resignation deleted with ID: " + id);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resignation not found with ID: " + id);
    }
}

@Override
public ResponseEntity<String> updateById(Long id, ResignationDto dto) {
    Optional<Resignation> optionalResignation = resignationRepo.findById(id);

    if (optionalResignation.isEmpty()) {
        throw new ResourceNotFound("Resignation request with ID " + id + " not found.");
    }

    Resignation existingData = optionalResignation.get();

    // Validate current status
    String currentStatus = existingData.getStatus();
    if (!currentStatus.equalsIgnoreCase("request") && !currentStatus.equalsIgnoreCase("pending")) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Cannot update resignation with status: " + currentStatus);
    }

    // Validate date input
    if (dto.getDateOfApplying() == null || dto.getDateOfApplying().isBlank()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Date of applying must not be null or empty.");
    }

    try {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate oldDate = LocalDate.parse(existingData.getDateOfApplying(), format);
        LocalDate newDate = LocalDate.parse(dto.getDateOfApplying(), format);

        if (!newDate.isAfter(oldDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("New date must be after existing date of applying.");
        }

        // Update fields
        existingData.setDateOfApplying(dto.getDateOfApplying());

        if (dto.getStatus() != null && !dto.getStatus().isBlank()) {
            existingData.setStatus(dto.getStatus());
        }

        // Save back to DB
        resignationRepo.save(existingData);

        return ResponseEntity.ok("Resignation date updated successfully.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid date format. Please use yyyy-MM-dd.");
    }
}
}
