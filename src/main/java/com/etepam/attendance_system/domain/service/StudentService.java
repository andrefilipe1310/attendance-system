package com.etepam.attendance_system.domain.service;

import com.etepam.attendance_system.domain.model.student.Student;
import com.etepam.attendance_system.domain.model.student.StudentResponseDTO;
import com.etepam.attendance_system.domain.service.interfaces.IStudentService;
import com.etepam.attendance_system.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class StudentService implements IStudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentResponseDTO create(Student student) {
        Student studentResponse = studentRepository.save(student);
        return convertToDTO(studentResponse);
    }

    @Override
    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
        return convertToDTO(student);
    }

    @Override
    public List<StudentResponseDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = students.stream()
                .map(student -> convertToDTO(student))
                .collect(Collectors.toList());

        return studentResponseDTOs;
    }

    @Override
    public StudentResponseDTO update(Long id, Student student) {
        Student updateStudent = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
        updateStudent = student;
        studentRepository.save(updateStudent);
        return convertToDTO(updateStudent);
    }

    @Override
    public String delete(Long id) {
        studentRepository.deleteById(id);
    }

    private Map<LocalDate,Boolean> createWeeklyFrequency(Student student){
        Map<LocalDate,Boolean> weeklyFrequency = new HashMap<>();

        LocalDate today = LocalDate.now();


        for (int i = 0; i < 7; i++) {
            today = today.minusDays(i);
            weeklyFrequency.put(today,true);
        }

        for (int i = 0; i < student.getAbsences().size(); i++) {
            LocalDate abcense = student.getAbsences().get(i).getAbsenceDay();
            if (weeklyFrequency.containsKey(abcense)){
                weeklyFrequency.put(abcense,false);
            }
        }
        return weeklyFrequency;
    }
    private Map<LocalDate,Boolean> createMonthFrequency(Student student){
        Map<LocalDate,Boolean> monthFrequency = new HashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 1; i <= today.getDayOfMonth(); i++) {
            monthFrequency.put(today.withDayOfMonth(i),true);
        }
        for (int i = 0; i < student.getAbsences().size(); i++) {
            LocalDate abcense = student.getAbsences().get(i).getAbsenceDay();
            if (monthFrequency.containsKey(abcense)){
                monthFrequency.put(abcense,false);
            }
        }
        return monthFrequency;
    }
    private StudentResponseDTO convertToDTO(Student student){

        Map<LocalDate,Boolean> weeklyFrequency = createWeeklyFrequency(student);
        Map<LocalDate,Boolean> monthFrequency = createMonthFrequency(student);
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getStudentImage(),
                student.getBirth(),
                student.getMother(),
                student.getFather(),
                student.getAbsences(),
                student.getAbsences().size(),
                (int) monthFrequency.values().stream()
                        .filter(value -> !value)
                        .count(),
                (int) weeklyFrequency.values().stream()
                        .filter(value -> !value)
                        .count(),
                monthFrequency,
                weeklyFrequency,
                LocalDate.now()
        );
    }
}
