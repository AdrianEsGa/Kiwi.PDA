package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.EmployeesRepository;
import com.xander.kiwipda.Model.Repositories.TablesRepository;
import com.xander.kiwipda.R;

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
}
