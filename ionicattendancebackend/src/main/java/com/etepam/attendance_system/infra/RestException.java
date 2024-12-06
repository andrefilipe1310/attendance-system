package com.etepam.attendance_system.infra;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class RestException {
    HttpStatus StatusCode;
    String Message;

}
