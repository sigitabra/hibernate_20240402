package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skyrius")
public class Department {
    @Id
    @Column(name = "pavadinimas")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee manager;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employeesByDep = new ArrayList<>();

}
