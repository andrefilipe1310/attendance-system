package com.etepam.attendance_system.domain.service;

import com.etepam.attendance_system.domain.model.guardian.Guardian;
import com.etepam.attendance_system.domain.model.student.*;
import com.etepam.attendance_system.domain.service.interfaces.IStudentService;
import com.etepam.attendance_system.exceptions.StudentNotFoundException;
import com.etepam.attendance_system.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {

        return convertToDTO(studentRepository.save(this.convertToEntity(studentRequestDTO)));
    }

    @Override
    public StudentResponseDTO findById(Long id) {

        return convertToDTO(studentRepository
                .findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student Not Found")));
    }

    @Override
    public List<StudentResponseDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = students.stream()
                .map(student -> convertToDTO(student))
                .collect(Collectors.toList());

        return studentResponseDTOs;
    }

    public List<StudentFeatureResponseDTO> findAllFeatures() {
        List<Student> students = studentRepository.findAll();
        List<StudentFeatureResponseDTO> studentResponseDTOs = students.stream()
                .map(student -> {
                    return new StudentFeatureResponseDTO(student.getId(),student.getName());
                })
                .collect(Collectors.toList());

        return studentResponseDTOs;
    }

    @Override
    public StudentResponseDTO update(Long id, StudentUpdateDTO studentUpdateDTO) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(()-> new StudentNotFoundException());

        student.update(studentUpdateDTO);
        return convertToDTO(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)){
            throw new StudentNotFoundException();
        }
        studentRepository.deleteById(id);

    }

    public void uploadImage(MultipartFile file, Long id) throws IOException {
        // Validar se o arquivo foi enviado corretamente
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo de imagem não foi enviado.");
        }

        // Verificar se o estudante existe
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Estudante com ID " + id + " não encontrado."));

        // Obter bytes do arquivo
        byte[] imageBytes = file.getBytes();

        // Validar se a imagem é válida utilizando OpenCV
        Mat image = Imgcodecs.imdecode(new MatOfByte(imageBytes), Imgcodecs.IMREAD_COLOR);
        if (image.empty()) {
            throw new IllegalArgumentException("Arquivo de imagem inválido ou corrompido.");
        }

        // Salvar a imagem no banco de dados
        student.setStudentImage(imageBytes);
        studentRepository.save(student);
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
    private Student convertToEntity(StudentRequestDTO studentRequestDTO){
        Student student = new Student();
       List<Guardian> guardians = studentRequestDTO.guardians().stream()
                .map(guardianRequestDTO -> {
                    return new Guardian(guardianRequestDTO.name(),guardianRequestDTO.phone());
                }).collect(Collectors.toList());
        student.setName(studentRequestDTO.name());
        student.setStudentImage(studentRequestDTO.studentImage());
        student.setPhone(studentRequestDTO.phone());
        student.setBirth(studentRequestDTO.birth());
        student.setGuardians(guardians);

        return  student;
    }
    private StudentResponseDTO convertToDTO(Student student){

        Map<LocalDate,Boolean> weeklyFrequency = createWeeklyFrequency(student);
        Map<LocalDate,Boolean> monthFrequency = createMonthFrequency(student);
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getBirth(),
                student.getGuardians(),
                student.getEmail(),
                student.getPassword(),
                student.getPhone(),
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
