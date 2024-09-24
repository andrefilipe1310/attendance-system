package com.etepam.attendance_system;

import com.etepam.attendance_system.domain.model.Student;
import com.etepam.attendance_system.domain.model.WeeklyAbsenceCalendar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		Student student = new Student();
		Set<LocalDate> days = student.getWeeklyAbsenceCalendar().getWeekAbsenceDays().keySet();
		System.out.println(days);
		days.forEach(System.out::println);
		SpringApplication.run(AttendanceSystemApplication.class, args);

	}

}
