package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandRepository {

    public CommandRepository(){}

    public ArrayList<Command> GetAllActive(){

        ArrayList<Command> commands = new ArrayList<Command>();
        Statement commandSql;
        try {

            commandSql = Database.SQLServer.Connect().createStatement();
            String strSQL = "SELECT Id, EmployeeId, BarTableId, StationId, Date, Status FROM Commands";
            ResultSet reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Command order = new Command(reader.getInt("Id"),reader.getInt("EmployeeId"),
                                            reader.getInt("BarTableId"),reader.getInt("StationId"),
                                            reader.getDate("Date"), reader.getInt("Status"));
                commands.add(order);
            }

        } catch (SQLException e) {

        }
        return commands;
    }
}
