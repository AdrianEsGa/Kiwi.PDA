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
import com.xander.kiwipda.Model.Entities.CommandStatus;
import com.xander.kiwipda.Model.Repositories.CommandRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.CommandAdapter;

import java.util.ArrayList;

public class CommandsActivity extends AppCompatActivity {

    private ListView listViewCommands;
    private CommandRepository commandRepository = new CommandRepository();
    private ArrayList<Command> commands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);
    }

    @Override
    public void onResume(){
        super.onResume();
        listViewCommands = findViewById(R.id.ListViewCommands);
        SetViewInfo();
        LoadListViewCommands();
        GlobalApp.Business.SelectedCommand = null;
    }

    private void LoadListViewCommands() {

        commands = commandRepository.GetByEmployeeAndTable(GlobalApp.Business.SelectedEmployee.GetId(), GlobalApp.Business.SelectedTable.GetId());

        for (Command command: commands) {
            command.SetImage(getResources().getDrawable(R.drawable.defaultcommand));
        }

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
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(myIntent);
    }

    public void btnNewCommandView_Click(View target) {
        NewCommand();
    }

    private void NewCommand(){
        GlobalApp.Business.SelectedCommand  = new Command(GlobalApp.Business.SelectedEmployee.GetId(), GlobalApp.Business.SelectedTable.GetId());
        Intent myIntent = new Intent(this, ProductTypesActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName());
    }

    public void BtnChangeCommandToServed_Click(View view) {
        int position = listViewCommands.getPositionForView((View) view.getParent());
        Command command = (Command) listViewCommands.getItemAtPosition(position);
        command.SetStatus(CommandStatus.Servida);
        commandRepository.Update(command);
        LoadListViewCommands();
    }
}
