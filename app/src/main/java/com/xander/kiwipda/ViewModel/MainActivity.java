package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.EmployeesRepository;
import com.xander.kiwipda.Model.Repositories.ProductsRepository;
import com.xander.kiwipda.Model.Repositories.TablesRepository;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadData();
    }

    private void LoadData(){

        try
        {
            LoadEmployees();
            LoadTables();
            LoadProductTypes();
            LoadProducts();

            Intent myIntent = new Intent(this, EmployeesActivity.class);
            this.startActivity(myIntent);
        }
        catch (Exception ex){
              GlobalApp.Messages.ShowToast(this, ex.getMessage());
        }
    }

    private void LoadEmployees(){
        EmployeesRepository employeesRepository = new EmployeesRepository();
        GlobalApp.Business.Employees = employeesRepository.GetAllActive();

        for (Employee employee : GlobalApp.Business.Employees) {
            if(employee.GetImage() == null){
                employee.SetImage(getResources().getDrawable(R.drawable.defaultwaiter));
            }
        }
    }

    private void LoadTables(){
        TablesRepository tablesRepository = new TablesRepository();
        GlobalApp.Business.Tables = tablesRepository.GetAllActive();

        for (Table table : GlobalApp.Business.Tables) {
            table.SetImage(getResources().getDrawable(R.drawable.defaulttable));
        }
    }

    private void LoadProductTypes(){

        ProductType copas = new ProductType(1, "Copas", "Ginebra, Wisky, Ron ...", null);
        ProductType refrescos = new ProductType(2, "Refrescos", "", null);
        ProductType cervezas = new ProductType(3, "Cervezas", "", null);
        ProductType varios = new ProductType(4, "Varios", "", null);
        ProductType cafes = new ProductType(6, "Cafés", "", null);
        ProductType tes = new ProductType(7, "Tés", "", null);
        ProductType cocktails = new ProductType(8, "Cocktails", "", null);
        ProductType infusiones = new ProductType(9, "Infusiones", "", null);
        ProductType vinos = new ProductType(10, "Vinos", "Mencia, Alvariño ..", null);

        GlobalApp.Business.ProductTypes = new ArrayList<ProductType>();

        GlobalApp.Business.ProductTypes.add(copas);
        GlobalApp.Business.ProductTypes.add(refrescos);
        GlobalApp.Business.ProductTypes.add(cervezas);
        GlobalApp.Business.ProductTypes.add(varios);
        GlobalApp.Business.ProductTypes.add(cafes);
        GlobalApp.Business.ProductTypes.add(tes);
        GlobalApp.Business.ProductTypes.add(cocktails);
        GlobalApp.Business.ProductTypes.add(infusiones);
        GlobalApp.Business.ProductTypes.add(vinos);
    }

    private void LoadProducts() {

        ProductsRepository productsRepository = new ProductsRepository();
        GlobalApp.Business.Products = productsRepository.GetAllActive();

    }
}
