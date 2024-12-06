package com.etepam.attendance_system.domain.service.interfaces;

import com.etepam.attendance_system.domain.model.student.Student;
import com.etepam.attendance_system.domain.model.student.StudentRequestDTO;
import com.etepam.attendance_system.domain.model.student.StudentResponseDTO;
import com.etepam.attendance_system.domain.model.student.StudentUpdateDTO;

import java.util.List;


public interface IStudentService {
    StudentResponseDTO create(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO findById(Long id);
    List<StudentResponseDTO> findAll();
    StudentResponseDTO update(Long id, StudentUpdateDTO studentUpdateDTO);
    void delete(Long id);
}
