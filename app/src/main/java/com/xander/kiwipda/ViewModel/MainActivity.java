package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xander.kiwipda.GlobalApp;
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
            EmployeesRepository employeesRepository = new EmployeesRepository();
            GlobalApp.Business.Employees = employeesRepository.GetAllActive();

            TablesRepository tablesRepository = new TablesRepository();
            GlobalApp.Business.Tables = tablesRepository.GetAllActive();

            Intent myIntent = new Intent(this, EmployeesActivity.class);
            this.startActivity(myIntent);
        }
        catch (Exception ex){
              GlobalApp.Messages.ShowToast(this, ex.getMessage());
        }
    }
}
