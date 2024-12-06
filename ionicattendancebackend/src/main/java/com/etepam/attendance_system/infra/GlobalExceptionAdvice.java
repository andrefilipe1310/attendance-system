package com.etepam.attendance_system.infra;


import com.etepam.attendance_system.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(StudentNotFoundException.class)
    public RestException handleStudentNotFoundException(StudentNotFoundException exception){
        return new RestException(HttpStatus.NOT_FOUND,exception.getMessage());
    }
}
