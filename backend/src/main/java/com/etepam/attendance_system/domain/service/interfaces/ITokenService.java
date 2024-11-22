package com.etepam.attendance_system.domain.service.interfaces;

import com.etepam.attendance_system.domain.model.user.User;

public interface ITokenService {
    String generateToken(User user);
}
