package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.R;

public class TablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        SetViewInfo();
    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.Name());
    }
}
