package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.xander.kiwipda.Database;
import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Repositories.EmployeesRepository;
import com.xander.kiwipda.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeesRepository employeesRepository = new EmployeesRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadListViewEmployees();
    }

    private void LoadListViewEmployees() {

        List<Employee> employees = employeesRepository.GetAllActive();

        ListView listViewEmployees = findViewById(R.id.ListViewEmployees);

        ArrayAdapter<Employee> arrayAdapter = new ArrayAdapter<Employee>
                (this, android.R.layout.simple_list_item_1, employees);

        listViewEmployees.setAdapter(arrayAdapter);

        listViewEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.SelectedEmployee = (Employee) adapter.getItemAtPosition(position);
                OpenTablesActivity();
            }
        });
    }

    private void OpenTablesActivity(){
        Intent myIntent = new Intent(this, TablesActivity.class);
        this.startActivity(myIntent);
    }

    private void ShowToast(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }



}

