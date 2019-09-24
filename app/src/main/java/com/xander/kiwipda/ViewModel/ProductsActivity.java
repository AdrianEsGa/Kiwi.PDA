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
import com.xander.kiwipda.Model.Entities.CommandDetail;
import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.R;
import com.xander.kiwipda.ViewModel.Adapters.ProductAdapter;
import com.xander.kiwipda.ViewModel.Adapters.ProductTypeAdapter;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private ListView listViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
    }

    @Override
    public void onResume(){
        super.onResume();
        SetViewInfo();
        LoadListViewProducts();
        listViewProducts = findViewById(R.id.ListViewProducts);
    }

    private void LoadListViewProducts() {

        ArrayList<Product> productsByType = new ArrayList<>();
        for (Product product: GlobalApp.Business.Products) {

            if(product.GetType() == GlobalApp.Business.SelectedProductType.GetId())
            {
                productsByType.add(product);

                for (CommandDetail commandDetail: GlobalApp.Business.SelectedCommand.GetDetails()) {

                    if(product.GetId() == commandDetail.GetProduct().GetId()){
                        product.SetQuantity(commandDetail.GetQuantity());
                    }
                }
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

    public void btnDecrement_Click(View view) {

        int position = listViewProducts.getPositionForView((View) view.getParent());
        Product product = (Product) listViewProducts.getItemAtPosition(position);

        LinearLayout parentRow = (LinearLayout) view.getParent();
        TextView quantityView = parentRow.findViewById(R.id.TextViewQuantity);

        quantityView.setText(String.valueOf(Decrement(product)));
    }

    public void btnIncrement_Click(View view) {

        LinearLayout parentRow = (LinearLayout) view.getParent();
        TextView quantityView = parentRow.findViewById(R.id.TextViewQuantity);

        int position = listViewProducts.getPositionForView((View) view.getParent());
        Product product = (Product) listViewProducts.getItemAtPosition(position);

        quantityView.setText(String.valueOf(Increment(product)));
    }

    public void btViewCommand_Click(View target) {
        Intent myIntent = new Intent(this, NewCommandActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity(myIntent);
    }

    private void SetViewInfo(){
        TextView textViewProductsInfo = findViewById(R.id.TextViewProductsInfo);
        textViewProductsInfo.setText(GlobalApp.Business.SelectedEmployee.GetName() + " / " + GlobalApp.Business.SelectedTable.GetName() + " /  Nueva Comanda / " + GlobalApp.Business.SelectedProductType.GetName());
    }

    private int Decrement(Product product){

        int newQuantity = 0;

        for (CommandDetail commandDetail: GlobalApp.Business.SelectedCommand.GetDetails()) {

            if(product.GetId() == commandDetail.GetProduct().GetId()){
                newQuantity = commandDetail.GetQuantity() - 1;

                if (newQuantity == 0) {
                    GlobalApp.Business.SelectedCommand.GetDetails().remove(commandDetail);
                    product.SetQuantity(0);
                }
                else {
                    commandDetail.SetQuantity(newQuantity);
                }
                break;
            }
        }

        return newQuantity;
    }

    private int Increment(Product product){

        boolean isNewElement = true;
        int newQuantity = 1;

        for (CommandDetail commandDetail : GlobalApp.Business.SelectedCommand.GetDetails()) {

            if (product.GetId() == commandDetail.GetProduct().GetId()) {
                newQuantity = commandDetail.GetQuantity() + 1;
                commandDetail.SetQuantity(newQuantity);
                isNewElement = false;
                break;
            }
        }

        if(isNewElement){
            CommandDetail commandDetail = new CommandDetail(product, newQuantity);
            GlobalApp.Business.SelectedCommand.GetDetails().add(commandDetail);
        }

      return newQuantity;
    }

}
