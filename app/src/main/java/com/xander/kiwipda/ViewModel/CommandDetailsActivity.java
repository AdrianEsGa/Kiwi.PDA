package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Repositories.CommandRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.CommandDetailAdapter;

public class CommandDetailsActivity extends AppCompatActivity {

    private CommandRepository commandRepository = new CommandRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_detail);
    }

    @Override
    public void onResume(){
        super.onResume();
        SetViewInfo();
        LoadListViewCommandDetails();

    }

    private void LoadListViewCommandDetails() {

        commandRepository.GetDetails(GlobalApp.Business.SelectedCommand);
        ListView listViewCommandDetails = findViewById(R.id.ListViewCommandDetails);
        CommandDetailAdapter arrayAdapter = new CommandDetailAdapter (this, GlobalApp.Business.SelectedCommand.GetDetails(), false);
        listViewCommandDetails.setAdapter(arrayAdapter);

        listViewCommandDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

            }
        });
    }
    private void SetViewInfo(){
        TextView textViewCommandDetailsInfo = findViewById(R.id.TextViewCommandDetailsInfo);
        textViewCommandDetailsInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " / " + GlobalApp.Business.SelectedCommand.GetName());
    }

}
