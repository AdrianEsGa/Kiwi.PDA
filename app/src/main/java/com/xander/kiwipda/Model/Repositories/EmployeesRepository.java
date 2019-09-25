package com.xander.kiwipda.Model.Repositories;

import android.graphics.drawable.Drawable;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeesRepository {

    public EmployeesRepository(){}

    public ArrayList<Employee> GetAllActive(){

        ArrayList<Employee> employees = new ArrayList<Employee>();
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {
            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            String strSQL = "Select Id, Name, Image From Employees WHERE Active = 1";
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Employee employee = new Employee(reader.getInt("Id"), reader.getString("Name"), Drawable.createFromStream(reader.getBinaryStream("Image"),""));
                employees.add(employee);
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }
        return employees;
    }

}
