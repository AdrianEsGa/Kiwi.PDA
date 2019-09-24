package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.ViewModel.Adapters.EmployeeAdapter;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.R;

public class EmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
    }

    @Override
    public void onResume(){
        super.onResume();
        LoadListViewEmployees();
    }


    private void LoadListViewEmployees() {

        ListView listViewEmployees = findViewById(R.id.ListViewEmployees);
        EmployeeAdapter arrayAdapter = new EmployeeAdapter (this, GlobalApp.Business.Employees);
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
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(myIntent);
    }
}

