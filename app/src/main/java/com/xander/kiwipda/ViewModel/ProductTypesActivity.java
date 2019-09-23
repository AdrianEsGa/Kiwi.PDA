package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.EmployeeAdapter;
import com.xander.kiwipda.ViewModel.Adapters.ProductTypeAdapter;

public class ProductTypesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_types);
        SetViewInfo();
        LoadListViewProductTypes();
    }

    private void LoadListViewProductTypes() {

        ListView listViewProductTypes = findViewById(R.id.ListViewProductTypes);
        ProductTypeAdapter arrayAdapter = new ProductTypeAdapter (this, GlobalApp.Business.ProductTypes);
        listViewProductTypes.setAdapter(arrayAdapter);

        listViewProductTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3) {
                GlobalApp.Business.SelectedProductType = (ProductType) adapter.getItemAtPosition(position);
                OpenProductsActivity();
            }
        });
    }

    private void OpenProductsActivity(){
        Intent myIntent = new Intent(this, ProductsActivity.class);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewProductTypesInfo = findViewById(R.id.TextViewProductTypesInfo);
        textViewProductTypesInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " / " + "Nueva Comanda");
    }
}
