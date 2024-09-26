package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.Absence;
import com.etepam.attendance_system.domain.model.Guardian;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record StudentResponseDTO(
        Long id,
        String name,
        String studentImage,
        LocalDate birth,
        Guardian mother,
        Guardian father,
        List<Absence> absences,
        int totalAbsences,
        int totalMonthAbsences,
        int totalWeekAbsences,
        Map<LocalDate,Boolean> monthFrequency,
        Map<LocalDate,Boolean> weeklyFrequency,
        LocalDate todayDate
) {
}