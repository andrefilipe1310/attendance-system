package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.absence.Absence;
import com.etepam.attendance_system.domain.model.guardian.Guardian;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record StudentResponseDTO(
        Long id,
        String name,
        LocalDate birth,
        List<Guardian> guardians,
        String email,
        String password,
        String phone,
        List<Absence> absences,
        int totalAbsences,
        int totalMonthAbsences,
        int totalWeekAbsences,
        Map<LocalDate,Boolean> monthFrequency,
        Map<LocalDate,Boolean> weeklyFrequency,
        LocalDate todayDate
) {
}