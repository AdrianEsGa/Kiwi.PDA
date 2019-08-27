package com.xander.kiwipda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xander.kiwipda.Business.Repositories.EmployeesRepository;

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



    private void LoadListViewEmployees(){

        List<String> employees = new ArrayList<String>();

        employeesRepository.GetAllActive();

        employees.add("Adrián Estévez");
        employees.add("Pepito de los palotes");
        employees.add("Juan Martinez");
        employees.add("Mónica Alvarez");

        final ListView listViewEmployees = (ListView) findViewById(R.id.ListViewEmployees);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, employees);

        listViewEmployees.setAdapter(arrayAdapter);
    }

}
