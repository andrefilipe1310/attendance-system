package com.etepam.attendance_system.domain.service.interfaces;

import com.etepam.attendance_system.domain.model.user.UserRequestDTO;
import com.etepam.attendance_system.domain.model.user.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO create(UserRequestDTO administrator);
    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findAll();
    UserResponseDTO update(Long id, UserRequestDTO administrator);
    String delete(Long id);
}
