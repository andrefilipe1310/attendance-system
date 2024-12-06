package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.guardian.Guardian;
import com.etepam.attendance_system.domain.model.guardian.GuardianRequestDTO;

import java.time.LocalDate;
import java.util.List;

public record StudentRequestDTO(String name,
                                byte[] studentImage ,
                                String phone,
                                LocalDate birth,
                                List<GuardianRequestDTO> guardians) {


}
