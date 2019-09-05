package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Repositories.OrdersRepository;
import com.xander.kiwipda.R;

import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private OrdersRepository ordersRepository = new OrdersRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        SetViewInfo();
        LoadListViewOrders();
    }

    private void LoadListViewOrders() {

        List<Command> commands = ordersRepository.GetAllActive();

        ListView listViewOrders = findViewById(R.id.ListViewOrders);

        ArrayAdapter<Command> arrayAdapter = new ArrayAdapter<Command>
                (this, android.R.layout.simple_list_item_1, commands);

        listViewOrders.setAdapter(arrayAdapter);

        listViewOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.selectedCommand = (Command) adapter.getItemAtPosition(position);
                OpenOrderDetailActivity();
            }
        });
    }

    private void OpenOrderDetailActivity(){

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
