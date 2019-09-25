package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TablesRepository {

    public TablesRepository(){}

    public ArrayList<Table> GetAllActive(){

        ArrayList<Table> tables = new ArrayList<Table>();
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();
            String strSQL = "Select Id, Name From BarTables WHERE Active = 1 ORDER BY Code";
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Table table = new Table(reader.getInt("Id"), reader.getString("Name") ,null);
                tables.add(table);
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }

        return tables;
    }
}
