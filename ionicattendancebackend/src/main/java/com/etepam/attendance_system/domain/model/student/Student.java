package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.absence.Absence;
import com.etepam.attendance_system.domain.model.guardian.Guardian;
import com.etepam.attendance_system.domain.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    //@Column(name = "student_image", columnDefinition = "BYTEA")
    private byte[] studentImage;

    private String phone;
    private LocalDate birth;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Guardian> guardians;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Absence> absences = new ArrayList<>();

    public void update(StudentUpdateDTO studentUpdateDTO){
        if(studentUpdateDTO.name() != null && !studentUpdateDTO.name().equals(this.name)){
            this.name = studentUpdateDTO.name();
        }
        if(studentUpdateDTO.studentImage() != null && !studentUpdateDTO.studentImage().equals(this.studentImage)){
            this.studentImage = studentUpdateDTO.studentImage();
        }
        if(studentUpdateDTO.phone() != null && !studentUpdateDTO.phone().equals(this.phone)){
            this.phone = studentUpdateDTO.phone();
        }

        if(studentUpdateDTO.birth() != null && !studentUpdateDTO.birth().equals(this.birth)){
            this.birth = studentUpdateDTO.birth();
        }
        if(studentUpdateDTO.guardians() != null){
            List<Guardian> guardians = studentUpdateDTO.guardians().stream()
                    .map(guardianRequestDTO -> {
                        return new Guardian(guardianRequestDTO.name(),guardianRequestDTO.phone());
                    }).collect(Collectors.toList());
            if (!guardians.equals(this.guardians)){
                this.guardians = guardians;
            }

        }
        if(studentUpdateDTO.absences() != null){
            List<Absence> absences = studentUpdateDTO.absences()
                    .stream().map(
                            absenceRequestDTO ->{
                                return new Absence(LocalDate.parse(absenceRequestDTO.absenceDay()),absenceRequestDTO.isJustificationValid(),absenceRequestDTO.justificationText());
                            }
                    ).collect(Collectors.toList());
            this.absences = absences;
        }
    }
}
