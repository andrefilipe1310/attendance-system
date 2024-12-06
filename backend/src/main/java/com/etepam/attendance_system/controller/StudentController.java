package com.etepam.attendance_system.controller;


import com.etepam.attendance_system.domain.model.student.*;
import com.etepam.attendance_system.domain.service.FaceDetectionService;
import com.etepam.attendance_system.domain.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor


@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    private FaceDetectionService faceDetectionService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(studentRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<StudentResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable("id") Long id,@RequestBody StudentUpdateDTO student){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.update(id,student));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/image/{studentId}")
    public ResponseEntity<?> uploadImage(@PathVariable("studentId") Long studentId,@RequestParam ("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo não enviado");
        }
        studentService.uploadImage(file, studentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/compare-face/{studentId}")
    public boolean compareFaceWithStudent(@RequestParam("file") MultipartFile file, @PathVariable Long studentId) {
        try {
            return faceDetectionService.compareFaceWithStudents(file);
        } catch (Exception e) {
            return false; // Caso haja algum erro, considera como não sendo a mesma pessoa
        }
    }



    private Boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }




}
