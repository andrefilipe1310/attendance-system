package com.etepam.attendance_system.domain.model.user;

public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
