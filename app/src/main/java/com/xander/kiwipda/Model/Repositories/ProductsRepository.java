package com.xander.kiwipda.Model.Repositories;

import android.graphics.drawable.Drawable;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.Model.Entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductsRepository {

    public ProductsRepository(){}

    public Product GetById(int id){

        Product product = new Product();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "SELECT Id, Name, Type, Image FROM Products WHERE Id = " + id;
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                product = new Product(reader.getInt("Id"), reader.getString("Name"),reader.getInt("Type"), Drawable.createFromStream(reader.getBinaryStream("Image"),""));
            }

        } catch (SQLException e) {

        }

        return product;
    }

    public ArrayList<Product> GetAllActive(){

        ArrayList<Product> products = new ArrayList<Product>();
        Statement command;
        try {

            command = Database.SQLServer.Connect().createStatement();
            String strSQL = "SELECT Id, Name, Type, Image FROM Products";
            ResultSet reader = command.executeQuery(strSQL);

            while (reader.next()) {
                Product product = new Product(reader.getInt("Id"), reader.getString("Name"),reader.getInt("Type"), Drawable.createFromStream(reader.getBinaryStream("Image"),""));
                products.add(product);
            }

        } catch (SQLException e) {

        }
        return products;
    }
}
