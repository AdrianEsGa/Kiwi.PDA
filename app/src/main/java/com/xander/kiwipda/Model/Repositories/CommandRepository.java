package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.CommandDetail;
import com.xander.kiwipda.Model.Entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

    public ArrayList<Command> GetByTable(int tableId){

        ArrayList<Command> commands = new ArrayList<Command>();
        Statement commandSql;
        try {

            commandSql = Database.SQLServer.Connect().createStatement();
            String strSQL = "SELECT Id, EmployeeId, BarTableId, StationId, Date, Status FROM Commands WHERE BarTableId = " + tableId;
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

    public void GetDetails(Command command){

        ProductsRepository productsRepository = new ProductsRepository();
        CommandDetail commandDetail;
        Statement commandSql;
        try {

            commandSql = Database.SQLServer.Connect().createStatement();
            String strSQL = "SELECT Id, ProductId, Quantity FROM CommandDetails WHERE CommandId = " + command.GetId();
            ResultSet reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Product product = productsRepository.GetById(reader.getInt("ProductId"));
                commandDetail = new CommandDetail(product, reader.getInt("Quantity"));
                command.GetDetails().add(commandDetail);
            }

        } catch (SQLException e) {

        }
    }

    public void Save(Command command){

        Statement  commandSql;
        try {


            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            commandSql = Database.SQLServer.Connect().createStatement();

            String strSQL = "INSERT INTO Commands (EmployeeId, BarTableId, Date, Status)\n" +
                            "VALUES (" + command.GetEmployeeId() + "," + command.GetTableId() +  ",'" +  currentDateandTime + "',0)";

            commandSql.executeUpdate(strSQL, Statement.RETURN_GENERATED_KEYS);

            int commandId = 0;
            ResultSet rs = commandSql.getGeneratedKeys();
            if(rs.next())
                commandId = rs.getInt(1);

            for (CommandDetail commandDetail: command.GetDetails()) {
                 strSQL = "INSERT INTO CommandDetails (CommandId, ProductId, Quantity)\n" +
                         "VALUES (" + commandId + "," + commandDetail.GetProduct().GetId() + "," + commandDetail.GetQuantity() + ")";

                commandSql.executeUpdate(strSQL);
            }

        } catch (SQLException e) {
                String aa = e.getMessage();
        }

    }


}
