package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.R;

public class CommandDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_detail);
        SetViewInfo();
    }

    private void SetViewInfo(){
        TextView textViewCommandDetailsInfo = findViewById(R.id.TextViewCommandDetailsInfo);
        textViewCommandDetailsInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " / " + GlobalApp.Business.SelectedCommand.GetName());
    }
}
