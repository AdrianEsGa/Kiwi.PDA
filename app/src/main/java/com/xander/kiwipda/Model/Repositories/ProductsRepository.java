package com.xander.kiwipda.Model.Repositories;

import android.graphics.drawable.Drawable;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductsRepository {

    public ProductsRepository(){}

    public Product GetById(int id){

        Product product = new Product();
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();

            String strSQL = "SELECT Id, Name, Type, Image FROM Products WHERE Id = " + id;
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                product = new Product(reader.getInt("Id"), reader.getString("Name"),reader.getInt("Type"), Drawable.createFromStream(reader.getBinaryStream("Image"),""));
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }

        return product;
    }

    public ArrayList<Product> GetAllActive(){

        ArrayList<Product> products = new ArrayList<Product>();
        Statement commandSql = null;
        Connection connectionSql = null;
        ResultSet reader = null;

        try {

            connectionSql = Database.SQLServer.Connect();
            commandSql = connectionSql.createStatement();

            String strSQL = "SELECT Id, Name, Type, Image FROM Products WHERE Active = 1";
            reader = commandSql.executeQuery(strSQL);

            while (reader.next()) {
                Product product = new Product(reader.getInt("Id"), reader.getString("Name"),reader.getInt("Type"), Drawable.createFromStream(reader.getBinaryStream("Image"),""));
                products.add(product);
            }

        } catch (SQLException e) {

        }
        finally {
            try { reader.close(); } catch (Exception e) { /* ignored */ }
            try { commandSql.close(); } catch (Exception e) { /* ignored */ }
            try { connectionSql.close(); } catch (Exception e) { /* ignored */ }
        }
        return products;
    }
}
