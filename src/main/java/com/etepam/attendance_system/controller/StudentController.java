package com.etepam.attendance_system.controller;


import com.etepam.attendance_system.domain.model.student.Student;
import com.etepam.attendance_system.domain.model.student.StudentResponseDTO;
import com.etepam.attendance_system.domain.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody Student student){
        return ResponseEntity.ok(studentService.create(student));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<StudentResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable("id") Long id,@RequestBody Student student){
        return ResponseEntity.ok(studentService.update(id,student));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(studentService.delete(id));
    }


}
