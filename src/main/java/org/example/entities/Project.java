package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "projektas")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pavadinimas")
    private String title;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Employee> employeesByProject = new ArrayList<>();
}
