package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.EmployeesRepository;
import com.xander.kiwipda.Model.Repositories.TablesRepository;
import com.xander.kiwipda.R;

import java.util.List;

public class TablesActivity extends AppCompatActivity {

    private TablesRepository tablesRepository = new TablesRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        SetViewInfo();
        LoadListViewTables();
    }

    private void LoadListViewTables() {

        List<Table> tables = tablesRepository.GetAllActive();

        ListView listViewTables = findViewById(R.id.ListViewTables);

        ArrayAdapter<Table> arrayAdapter = new ArrayAdapter<Table>
                (this, android.R.layout.simple_list_item_1, tables);

        listViewTables.setAdapter(arrayAdapter);

        listViewTables.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.SelectedTable = (Table) adapter.getItemAtPosition(position);
                OpenOrdersActivity();
            }
        });
    }

    private void OpenOrdersActivity(){

    }

    public void btnBackToEmployeesView_Click(View target) {
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.Name());

    }
}
