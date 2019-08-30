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
import com.xander.kiwipda.Model.Entities.Order;
import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.Model.Repositories.OrdersRepository;
import com.xander.kiwipda.Model.Repositories.TablesRepository;
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

    public void btnBackToTablesView_Click(View target) {
        Intent myIntent = new Intent(this, TablesActivity.class);
        this.startActivity(myIntent);
    }

    private void LoadListViewOrders() {

        List<Order> orders = ordersRepository.GetAllActive();

        ListView listViewOrders = findViewById(R.id.ListViewOrders);

        ArrayAdapter<Order> arrayAdapter = new ArrayAdapter<Order>
                (this, android.R.layout.simple_list_item_1, orders);

        listViewOrders.setAdapter(arrayAdapter);

        listViewOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.SelectedOrder = (Order) adapter.getItemAtPosition(position);
                OpenOrderDetailActivity();
            }
        });
    }

    private void OpenOrderDetailActivity(){

    }

    private void SetViewInfo(){
        TextView textViewEmployee = findViewById(R.id.TextViewEmployee);
        textViewEmployee.setText(GlobalApp.Business.SelectedEmployee.Name() + " / " + GlobalApp.Business.SelectedTable.Name());
    }
}
