package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xander.kiwipda.GlobalApp;
import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.ProductAdapter;
import com.xander.kiwipda.ViewModel.Adapters.ProductTypeAdapter;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        SetViewInfo();
        LoadListViewProducts();
    }

    private void LoadListViewProducts() {

        ArrayList<Product> productsByType = new ArrayList<>();
        for (Product product: GlobalApp.Business.Products) {

            if(product.GetType() == GlobalApp.Business.SelectedProductType.GetId())
            {
                productsByType.add(product);
            }
        }

        ListView listViewProducts = findViewById(R.id.ListViewProducts);
        ProductAdapter arrayAdapter = new ProductAdapter (this, productsByType);
        listViewProducts.setAdapter(arrayAdapter);

        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
            }
        });
    }

    public void btnBackToProductTypesView_Click(View target) {
        Intent myIntent = new Intent(this, ProductTypesActivity.class);
        this.startActivity(myIntent);
    }

    public void btConfirm_Click(View target) {
        Intent myIntent = new Intent(this, NewCommandActivity.class);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewProductsInfo = findViewById(R.id.TextViewProductsInfo);
        textViewProductsInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " / " + "Nueva Comanda");
    }
}
