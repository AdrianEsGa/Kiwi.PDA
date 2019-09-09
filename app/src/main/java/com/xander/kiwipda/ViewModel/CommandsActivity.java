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
import com.xander.kiwipda.Model.Repositories.CommandRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.CommandAdapter;

import java.util.ArrayList;

public class CommandsActivity extends AppCompatActivity {

    private CommandRepository commandRepository = new CommandRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);
        SetViewInfo();
        LoadListViewCommands();
    }

    private void LoadListViewCommands() {

        ArrayList<Command> commands = commandRepository.GetAllActive();

        for (Command command: commands) {
            command.SetImage(getResources().getDrawable(R.drawable.defaultcommand));
        }

        ListView listViewCommands = findViewById(R.id.ListViewCommands);
        CommandAdapter arrayAdapter = new CommandAdapter (this, commands);
        listViewCommands.setAdapter(arrayAdapter);

        listViewCommands.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.SelectedCommand = (Command) adapter.getItemAtPosition(position);
                OpenCommandDetailsActivity();
            }
        });
    }

    private void OpenCommandDetailsActivity(){
        Intent myIntent = new Intent(this, CommandDetailsActivity.class);
        this.startActivity(myIntent);
    }

    public void btnBackToTablesView_Click(View target) {
        Intent myIntent = new Intent(this, TablesActivity.class);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName());
    }
}
