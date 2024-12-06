package com.etepam.attendance_system.domain.service;

import com.etepam.attendance_system.domain.model.user.User;
import com.etepam.attendance_system.domain.model.user.UserRequestDTO;
import com.etepam.attendance_system.domain.model.user.UserResponseDTO;
import com.etepam.attendance_system.domain.service.interfaces.IUserService;
import com.etepam.attendance_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDTO create(UserRequestDTO user) {

        return userRepository.save(new User(user)).toDTO();
    }

    @Override
    public UserResponseDTO findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(()->{
                    return new RuntimeException("User not found.");
                })
                .toDTO();
    }

    @Override
    public List<UserResponseDTO> findAll() {

        return userRepository.findAll()
                .stream().map((user)->{
                    return user.toDTO();
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO user) {
        User updatedUser = userRepository.findById(id).orElseThrow(()->{
            return new RuntimeException("User not found.");
        });
        updatedUser.update(user);

        return userRepository.save(updatedUser).toDTO();
    }

    @Override
    public String delete(Long id) {
        userRepository.deleteById(id);
        if (userRepository.existsById(id)){
            throw new RuntimeException("failure deletion.");
        }
        return "User "+id+" deleted";
    }
}
