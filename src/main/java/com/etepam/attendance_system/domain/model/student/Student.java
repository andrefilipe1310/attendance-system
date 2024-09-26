package com.etepam.attendance_system.domain.model.student;

import com.etepam.attendance_system.domain.model.Absence;
import com.etepam.attendance_system.domain.model.Guardian;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String studentImage;
    private LocalDate birth;

    @OneToOne(cascade = CascadeType.ALL)
    private Guardian mother;

    @OneToOne(cascade = CascadeType.ALL)
    private Guardian father;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Absence> absences = new ArrayList<>();
}
