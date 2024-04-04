package org.example;

import org.example.entities.Department;
import org.example.entities.Employee;
import org.example.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Transaction tx = null;
        try (SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                tx = session.beginTransaction();
                for (Department dep : generateTestData(20, 5, 5)) {
                    session.persist(dep);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<Employee> generateTestEmployees(int count) {
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Employee employee = new Employee();
            if (i < 10) {
                employee.setAsmenskodas("4780101012" + i);
            } else {
                employee.setAsmenskodas("478010101" + i);
            }
            employee.setName("Vardenis" + i);
            employee.setSurname("Pavardenis" + i);
            employee.setStartDate(Date.valueOf(LocalDate.of(2023, 10, 10).plusMonths(i)));
            employee.setBirthDate(Date.valueOf(LocalDate.of(2000, 10, 10).plusMonths(i)));
            employee.setJobTitle("Pareigos" + i);
            employees.add(employee);
        }
        return employees;
    }

    private static List<Department> generateTestDeps(int count) {
        List<Department> departments = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Department department = new Department();
            department.setTitle("Java" + i);
            departments.add(department);
        }
        return departments;
    }

    private static List<Project> generateTestProjects(int count) {
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Project project = new Project();
            project.setTitle("Project" + i);
            projects.add(project);
        }
        return projects;
    }

    private static List<Department> generateTestData(int employeesCount, int depsCount, int projectsCount) {
        List<Employee> employees = generateTestEmployees(employeesCount);
        List<Department> departments = generateTestDeps(depsCount);
        List<Project> projects = generateTestProjects(projectsCount);
        connectTestData(employees, departments, projects);

        return departments;

    }

    private static void connectTestData(List<Employee> employees, List<Department> departments, List<Project> projects) {
        for (Employee s : employees) {
            Department department = departments.get(new Random().nextInt(departments.size() - 1));
            department.getEmployeesByDep().add(s);
            s.setDepartment(department);
            Project project = projects.get(new Random().nextInt(projects.size() - 1));
            project.getEmployeesByProject().add(s);
            s.setProject(project);
        }

        for (Department d : departments) {
            Employee employee = employees.get(new Random().nextInt(employees.size() - 1));
            employee.getManagedDepartments().add(d);
            d.setManager(employee);
        }
    }

}