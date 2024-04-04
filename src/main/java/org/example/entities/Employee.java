package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "darbuotojas")
public class Employee {

    @Id
    @Column(name = "asmenskodas")
    private String asmenskodas;
    @Column(name = "vardas")
    private String name;
    @Column(name = "pavarde")
    private String surname;
    @Column(name = "dirbanuo")
    private Date startDate;
    @Column(name = "gimimometai")
    private Date birthDate;
    @Column(name = "pareigos")
    private String jobTitle;
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

}
