package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xander.kiwipda.Configuration.Preference;
import com.xander.kiwipda.Configuration.PreferencesController;
import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.R;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
    }

    @Override
    public void onResume(){
        super.onResume();
        LoadConfiguration();
    }

    public void BtnSave_Click(View target) {

        try {
            PreferencesController preferencesController = new PreferencesController();

            EditText textServer = findViewById(R.id.TextServer);
            EditText textDatabase = findViewById(R.id.TextDatabase);
            EditText textUser = findViewById(R.id.TextUser);
            EditText textPassword = findViewById(R.id.TextPassword);

            if (textServer.getText().toString() != "" && textDatabase.getText().toString() != "" && textUser.getText().toString() != "" && textPassword.getText().toString() != "") {

                GlobalApp.Business.DbConfiguration = new Preference();
                GlobalApp.Business.DbConfiguration.ServerName = textServer.getText().toString();
                GlobalApp.Business.DbConfiguration.Database = textDatabase.getText().toString();
                GlobalApp.Business.DbConfiguration.User = textUser.getText().toString();
                GlobalApp.Business.DbConfiguration.Password = textPassword.getText().toString();

                preferencesController.SavePreferents(this, GlobalApp.Business.DbConfiguration);

                Intent myIntent = new Intent(this, MainActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(myIntent);

            } else {
                GlobalApp.Messages.ShowToast(this, "Debe cubrir todos los campos.");
            }
        }
        catch (Exception ex){

        }
    }

    private void LoadConfiguration(){
        EditText textServer = findViewById(R.id.TextServer);
        EditText textDatabase = findViewById(R.id.TextDatabase);
        EditText textUser = findViewById(R.id.TextUser);
        EditText textPassword = findViewById(R.id.TextPassword);

        textServer.setText(GlobalApp.Business.DbConfiguration.ServerName);
        textDatabase.setText(GlobalApp.Business.DbConfiguration.Database);
        textUser.setText(GlobalApp.Business.DbConfiguration.User);
        textPassword.setText(GlobalApp.Business.DbConfiguration.Password);
    }
}
