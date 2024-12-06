package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.absence.Absence;
import com.etepam.attendance_system.domain.model.absence.AbsenceRequestDTO;
import com.etepam.attendance_system.domain.model.guardian.GuardianRequestDTO;

import java.time.LocalDate;
import java.util.List;

public record StudentUpdateDTO(String name, byte[] studentImage,
                               String phone, LocalDate birth, List<GuardianRequestDTO> guardians, List<AbsenceRequestDTO> absences) {


}
