package com.etepam.attendance_system.domain.model.user;

public record RegisterDTO(String email, String password, UserRoles roles) {
}
