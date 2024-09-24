package com.etepam.attendance_system.domain.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor // Construtor padrão para JPA
@Embeddable
public class WeeklyAbsenceCalendar {
    @ElementCollection
    private Map<LocalDate, Boolean> weekAbsenceDays = new HashMap<>(); // Mapa que contém as faltas da semana

    // Método para inicializar o calendário semanal
    public void generateCalendar(LocalDate currentDate, List<LocalDate> absences) {
        weekAbsenceDays.clear(); // Limpa o mapa antes de gerar a nova semana
        LocalDate sunday = getSundayOfCurrentWeek(currentDate);

        // Gerar os dias da semana de domingo até o dia atual
        LocalDate day = sunday;
        while (!day.isAfter(currentDate)) {
            weekAbsenceDays.put(day, absences.contains(day)); // Verifica se há falta no dia e insere true/false
            day = day.plusDays(1);
        }
    }

    // Método para encontrar o domingo da semana atual
    private LocalDate getSundayOfCurrentWeek(LocalDate date) {
        return date.with(DayOfWeek.SUNDAY);
    }
}
