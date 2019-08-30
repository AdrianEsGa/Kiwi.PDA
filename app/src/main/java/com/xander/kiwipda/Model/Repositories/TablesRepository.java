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

    public ArrayList<Table> GetAllActive(){

        ArrayList<Table> tables = new ArrayList<Table>();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "Select IdAlmacenes, Nombre From Almacenes";
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                Table table = new Table(reader.getInt("IdAlmacenes"), reader.getString("Nombre"), "Soy una mesa",null);
                tables.add(table);
            }

        } catch (SQLException e) {

        }
        return tables;
    }
}
