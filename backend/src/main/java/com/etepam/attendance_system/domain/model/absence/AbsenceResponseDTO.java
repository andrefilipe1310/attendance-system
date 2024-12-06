package com.etepam.attendance_system.domain.model.absence;

public record AbsenceResponseDTO(String absenceDay, boolean isJustificationValid, String justificationText){
}
