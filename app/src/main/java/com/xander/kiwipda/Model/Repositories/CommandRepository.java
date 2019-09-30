package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.CommandDetail;
import com.xander.kiwipda.Model.Entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommandRepository {

    public CommandRepository(){}

    public ArrayList<Command> GetByEmployeeAndTable(int employeeId, int tableId){

        ArrayList<Command> commands = new ArrayList<Command>();
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            String strSQL = "SELECT Id, EmployeeId, BarTableId, StationId, Date, Status FROM Commands WHERE Status <> 3 AND EmployeeId = " + employeeId + " AND BarTableId = " + tableId;
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Command order = new Command(reader.getInt("Id"),reader.getInt("EmployeeId"),
                        reader.getInt("BarTableId"),reader.getInt("StationId"),
                        reader.getDate("Date"), reader.getInt("Status"));
                commands.add(order);
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }

        return commands;
    }

    public void GetDetails(Command command){

        ProductsRepository productsRepository = new ProductsRepository();
        CommandDetail commandDetail;
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            String strSQL = "SELECT Id, ProductId, Quantity FROM CommandDetails WHERE CommandId = " + command.GetId();
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Product product = productsRepository.GetById(reader.getInt("ProductId"));
                commandDetail = new CommandDetail(product, reader.getInt("Quantity"));
                command.GetDetails().add(commandDetail);
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public void Save(Command command){

        Statement  commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            connectionSql.setAutoCommit(false);

            String strSQL = "INSERT INTO Commands (EmployeeId, BarTableId, Date, Status)\n" +
                            "VALUES (" + command.GetEmployeeId() + "," + command.GetTableId() +  ",'" +  currentDateandTime + "',0)";

            commandSql.executeUpdate(strSQL, Statement.RETURN_GENERATED_KEYS);

            int commandId = 0;
            reader = commandSql.getGeneratedKeys();
            if(reader.next())
                commandId = reader.getInt(1);

            for (CommandDetail commandDetail: command.GetDetails()) {
                 strSQL = "INSERT INTO CommandDetails (CommandId, ProductId, Quantity)\n" +
                         "VALUES (" + commandId + "," + commandDetail.GetProduct().GetId() + "," + commandDetail.GetQuantity() + ")";

                commandSql.executeUpdate(strSQL);
            }

            connectionSql.commit();
            connectionSql.setAutoCommit(true);

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public void Update(Command command){

        Statement  commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            String strSQL = "UPDATE Commands SET Status = " + command.GetStatus().ordinal() + " WHERE Id = " + command.GetId();
            commandSql.executeUpdate(strSQL);

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }
    }
}
