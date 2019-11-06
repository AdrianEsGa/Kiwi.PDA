package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.CommandRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.TableAdapter;

public class TablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
    }

    @Override
    public void onResume(){
        super.onResume();
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
                OpenCommandsOrProductTypesActivity();
            }
        });
    }

    private void OpenCommandsOrProductTypesActivity(){

        CommandRepository commandRepository = new CommandRepository();

        if (commandRepository.HasByEmployeeAndTable(GlobalApp.Business.SelectedEmployee.GetId(),  GlobalApp.Business.SelectedTable.GetId())){
            Intent myIntent = new Intent(this, CommandsActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(myIntent);
        }
        else {
            GlobalApp.Business.SelectedCommand  = new Command(GlobalApp.Business.SelectedEmployee.GetId(), GlobalApp.Business.SelectedTable.GetId());
            Intent myIntent = new Intent(this, ProductTypesActivity.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(myIntent);
        }
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.GetName());
    }
}
