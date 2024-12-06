package com.etepam.attendance_system.domain.model.absence;

import java.time.LocalDate;

public record AbsenceRequestDTO (String absenceDay, boolean isJustificationValid, String justificationText){
}
