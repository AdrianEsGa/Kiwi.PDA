package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.TablesRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.TableAdapter;

public class TablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        SetViewInfo();
        LoadListViewTables();
    }

    private void LoadListViewTables() {

        ListView listViewTables = findViewById(R.id.ListViewTables);
        TableAdapter arrayAdapter = new TableAdapter(this, GlobalApp.Business.Tables);
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
        Intent myIntent = new Intent(this, OrdersActivity.class);
        this.startActivity(myIntent);
    }

    public void btnBackToEmployeesView_Click(View target) {
        Intent myIntent = new Intent(this, EmployeesActivity.class);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.GetName());
    }
}
