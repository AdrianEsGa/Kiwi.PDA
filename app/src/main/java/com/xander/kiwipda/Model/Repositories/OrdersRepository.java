package com.xander.kiwipda.Model.Repositories;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {

    public OrdersRepository(){}

    public List<Order> GetAllActive(){

        List<Order> orders = new ArrayList<Order>();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "Select top 5 IdVehiculos From Vehiculos";
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                Order order = new Order(reader.getInt("IdVehiculos"));
                orders.add(order);
            }

        } catch (SQLException e) {

        }
        return orders;
    }
}
