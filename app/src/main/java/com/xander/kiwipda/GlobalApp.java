package com.xander.kiwipda;

import android.app.Activity;
import android.widget.Toast;

import com.xander.kiwipda.Configuration.Preference;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.Model.Entities.Table;

import java.util.ArrayList;

public class GlobalApp {

    public static class Business {

        public static Preference DbConfiguration;

        public static ArrayList<Employee> Employees;
        public static ArrayList<Table> Tables;
        public static ArrayList<ProductType> ProductTypes;
        public static ArrayList<Product> Products;

        public static Employee SelectedEmployee;
        public static Table SelectedTable;
        public static Command SelectedCommand;
        public static ProductType SelectedProductType;
    }

    public static class Messages {

        public static void ShowToast(Activity activity, String message){
            Toast.makeText(activity, message,
                    Toast.LENGTH_LONG).show();
        }
    }
}
