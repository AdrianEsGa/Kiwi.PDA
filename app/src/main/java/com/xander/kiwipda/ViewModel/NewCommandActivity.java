package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.CommandDetail;
import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.Model.Repositories.CommandRepository;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.CommandDetailAdapter;
import com.xander.kiwipda.ViewModel.Adapters.ProductAdapter;

public class NewCommandActivity extends AppCompatActivity {

    private ListView listViewNewCommandDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_command);
    }

    @Override
    public void onResume(){
        super.onResume();
        listViewNewCommandDetails = findViewById(R.id.ListViewNewCommandDetails);
        SetViewInfo();
        LoadListViewCommandDetails();
    }

    public void btnAddProduct_Click(View target) {
        Intent myIntent = new Intent(this, ProductTypesActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewNewCommandInfo = findViewById(R.id.TextViewNewCommandInfo);
        textViewNewCommandInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " / " + "Nueva Comanda");
    }

    private void LoadListViewCommandDetails() {

        ListView listViewNewCommandDetails = findViewById(R.id.ListViewNewCommandDetails);
        CommandDetailAdapter arrayAdapter = new CommandDetailAdapter (this, GlobalApp.Business.SelectedCommand.GetDetails(), true);
        listViewNewCommandDetails.setAdapter(arrayAdapter);

        listViewNewCommandDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

            }
        });
    }

    public void btnDecrement_Click(View view) {
        int position = listViewNewCommandDetails.getPositionForView((View) view.getParent());
        CommandDetail commandDetail = (CommandDetail) listViewNewCommandDetails.getItemAtPosition(position);

        LinearLayout parentRow = (LinearLayout) view.getParent();
        TextView quantityView = parentRow.findViewById(R.id.TextViewQuantity);

        quantityView.setText(String.valueOf(Decrement(commandDetail)));
    }

    public void btnIncrement_Click(View view) {
        int position = listViewNewCommandDetails.getPositionForView((View) view.getParent());
        CommandDetail commandDetail = (CommandDetail) listViewNewCommandDetails.getItemAtPosition(position);

        LinearLayout parentRow = (LinearLayout) view.getParent();
        TextView quantityView = parentRow.findViewById(R.id.TextViewQuantity);

        quantityView.setText(String.valueOf(Increment(commandDetail)));
    }

    private int Increment(CommandDetail commandDetail){
        int newQuantity;
        newQuantity = commandDetail.GetQuantity() + 1;
        commandDetail.SetQuantity(newQuantity);
        return newQuantity;
    }

    private int Decrement(CommandDetail commandDetail){
        int newQuantity;
        newQuantity = commandDetail.GetQuantity() - 1;

        if(newQuantity == 0){
            GlobalApp.Business.SelectedCommand.GetDetails().remove(commandDetail);
            LoadListViewCommandDetails();
        }
        else{
            commandDetail.SetQuantity(newQuantity);
        }
        return newQuantity;
    }

    public void btnFinalizeCommand_Click(View view) {
        CommandRepository commandRepository = new CommandRepository();
        commandRepository.Save(GlobalApp.Business.SelectedCommand);

        GlobalApp.Business.SelectedCommand = new Command();
        Intent myIntent = new Intent(this, TablesActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(myIntent);
    }
}
