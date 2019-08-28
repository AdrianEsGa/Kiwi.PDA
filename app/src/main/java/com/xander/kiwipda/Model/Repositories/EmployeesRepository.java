package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Model.Entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesRepository {

    public EmployeesRepository(){}

    public List<Employee> GetAllActive(){

        List<Employee> employees = new ArrayList<Employee>();

        Employee employee1 = new Employee(1, "Adrián Estévez");
        Employee employee2 = new Employee(2, "Juan de los palotes");
        Employee employee3 = new Employee(3, "Pepe y Juan");

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        return employees;
    }

}
