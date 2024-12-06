package com.etepam.attendance_system.domain.model.absence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity(name = "tb_absence")
@Table(name = "tb_absence")
@Getter
@Setter
@NoArgsConstructor
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate absenceDay;
    private boolean isJustificationValid;
    private String justificationText;

    public Absence(LocalDate absenceDay, boolean isJustificationValid, String justificationText){
        this.absenceDay = absenceDay;
        this.isJustificationValid = isJustificationValid;
        this.justificationText = justificationText;
    }
}
