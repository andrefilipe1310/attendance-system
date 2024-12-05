package com.etepam.attendance_system.domain.service.interfaces;

import com.etepam.attendance_system.domain.model.student.Student;
import com.etepam.attendance_system.domain.model.student.StudentResponseDTO;

import java.util.List;


public interface IStudentService {
    StudentResponseDTO create(Student student);
    StudentResponseDTO findById(Long id);
    List<StudentResponseDTO> findAll();
    StudentResponseDTO update(Long id, Student student);
    String delete(Long id);
}
