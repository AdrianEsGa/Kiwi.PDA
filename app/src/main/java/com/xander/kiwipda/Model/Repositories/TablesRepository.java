package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablesRepository {

    public TablesRepository(){}

    public List<Table> GetAllActive(){

        List<Table> tables = new ArrayList<Table>();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "Select top 5 IdClientesProv, Nombre From ClientesProv";
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                Table table = new Table(reader.getInt("IdClientesProv"), reader.getString("Nombre"));
                tables.add(table);
            }

        } catch (SQLException e) {

        }
        return tables;
    }
}
