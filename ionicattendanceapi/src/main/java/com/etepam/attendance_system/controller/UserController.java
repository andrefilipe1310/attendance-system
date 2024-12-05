package com.etepam.attendance_system.controller;


import com.etepam.attendance_system.domain.model.user.UserRequestDTO;
import com.etepam.attendance_system.domain.model.user.UserResponseDTO;
import com.etepam.attendance_system.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @RequestBody UserRequestDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.delete(id));
    }
}
