package com.etepam.attendance_system.controller;


import com.etepam.attendance_system.domain.model.user.User;
import com.etepam.attendance_system.domain.model.user.AuthenticationDTO;
import com.etepam.attendance_system.domain.model.user.LoginResponseDTO;
import com.etepam.attendance_system.domain.model.user.RegisterDTO;
import com.etepam.attendance_system.domain.service.TokenService;
import com.etepam.attendance_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(),data.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.userRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.email(),encryptedPassword,data.roles());

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
