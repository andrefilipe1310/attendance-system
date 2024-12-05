package com.etepam.attendance_system.repository;

import com.etepam.attendance_system.domain.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {
}
