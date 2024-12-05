package com.etepam.attendance_system.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_guardian")
@Table(name = "tb_guardian")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
}
