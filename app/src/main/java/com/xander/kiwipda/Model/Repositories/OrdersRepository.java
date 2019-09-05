package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {

    public OrdersRepository(){}

    public List<Command> GetAllActive(){

        List<Command> commands = new ArrayList<Command>();
        Statement commandSql;
        try {

            commandSql = Database.SQLServer.Connect().createStatement();
            String strSQL = "Select top 5 IdVehiculos From Vehiculos";
            ResultSet reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Command order = new Command(reader.getInt("IdVehiculos"),1,1,null, 1);
                commands.add(order);
            }

        } catch (SQLException e) {

        }
        return commands;
    }
}
