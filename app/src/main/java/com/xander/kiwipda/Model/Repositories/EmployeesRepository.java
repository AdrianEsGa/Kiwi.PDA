package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesRepository {

    public EmployeesRepository(){}

    public ArrayList<Employee> GetAllActive(){

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "Select IdEmpleados, Nombre From Empleados";
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                Employee employee = new Employee(reader.getInt("IdEmpleados"), reader.getString("Nombre"), "Camarero",  null);
                employees.add(employee);
            }

        } catch (SQLException e) {

        }
        return employees;
    }

}
