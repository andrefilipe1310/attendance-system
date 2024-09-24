package com.etepam.attendance_system.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime birth;
    private String fatherName;
    private String motherName;
    private String fatherPhone;
    private String motherPhone;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Absence> absences;
    @Embedded
    private WeeklyAbsenceCalendar weeklyAbsenceCalendar = new WeeklyAbsenceCalendar(); // Relacionamento com o calendário semanal

    // Método para atualizar o calendário semanal de ausências
    public void updateWeeklyAbsenceCalendar(LocalDate currentDate) {
        // Extrair as datas de ausência do aluno
        List<LocalDate> absenceDays = new ArrayList<>();
        absences.forEach(absence -> absenceDays.add(absence.getAbsenceDay()));

        // Gerar o calendário de faltas baseado na data atual e nas ausências do estudante
        weeklyAbsenceCalendar.generateCalendar(currentDate, absenceDays);
    }


    }




