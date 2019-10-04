package com.xander.kiwipda.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    private Product selectedProduct;
    private Product combinedProduct;
    private EditText textSearch;
    private ListView listViewProducts;
    private int selectedProductTypeId;
    private boolean selectCombinedProductMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
    }

    @Override
    public void onResume(){
        super.onResume();
        listViewProducts = findViewById(R.id.ListViewProducts);
        SetViewInfo();
        SetTextSearchHandler();
        selectedProductTypeId = GlobalApp.Business.SelectedProductType.GetId();
        LoadListViewProducts(true);
    }

    private void SetTextSearchHandler() {
        textSearch = findViewById(R.id.TextSearch);

        textSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                LoadListViewProducts(!selectCombinedProductMode);
            }
        });
    }

    private void LoadListViewProducts(boolean showButtons) {

        ArrayList<Product> productsByType = new ArrayList<>();
        for (Product product: GlobalApp.Business.Products) {

            product.SetQuantity(0);

            if(product.GetType() == selectedProductTypeId)
            {
                if(textSearch.getText().toString() != "") {
                    if (product.GetName().toUpperCase().contains(textSearch.getText().toString().toUpperCase())) {
                        productsByType.add(product);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    productsByType.add(product);
                }

                for (CommandDetail commandDetail: GlobalApp.Business.SelectedCommand.GetDetails()) {

                    if(product.GetId() == commandDetail.GetProduct().GetId()){

                        product.SetQuantity(commandDetail.GetQuantity() + product.GetQuantity());
                    }
                }
            }
        }

        ProductAdapter arrayAdapter = new ProductAdapter (this, productsByType, showButtons);
        listViewProducts.setAdapter(arrayAdapter);

        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                if(selectCombinedProductMode) {
                    boolean isNewElement = true;

                    combinedProduct = (Product) adapter.getItemAtPosition(position);

                    for (CommandDetail commandDetail : GlobalApp.Business.SelectedCommand.GetDetails()) {
                        if (commandDetail.GetProduct().GetId() == selectedProduct.GetId() && commandDetail.GetCombinedProduct().GetId() == combinedProduct.GetId()) {
                            isNewElement = false;
                            commandDetail.SetQuantity(commandDetail.GetQuantity() + 1);
                        }
                    }

                    if (isNewElement) {
                        CommandDetail commandDetail = new CommandDetail(selectedProduct, combinedProduct, 1);
                        GlobalApp.Business.SelectedCommand.GetDetails().add(commandDetail);
                    }

                    selectCombinedProductMode = false;
                    selectedProductTypeId = GlobalApp.Business.SelectedProductType.GetId();
                    textSearch.setText("");
                }
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
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                }
                else {
                    commandDetail.SetQuantity(newQuantity);
                }
                break;
            }
        }

        newQuantity = 0;
        for (CommandDetail commandDetail: GlobalApp.Business.SelectedCommand.GetDetails()) {
            if(product.GetId() == commandDetail.GetProduct().GetId()){
                newQuantity += commandDetail.GetQuantity();
            }
        }

        return newQuantity;
    }

    private int Increment(Product product){

        boolean isNewElement = true;
        int newQuantity = 1;

        selectedProduct = product;

        if(product.GetType() == 1){
            selectCombinedProductMode = true;
            selectedProductTypeId = 2;
            LoadListViewProducts(false);
        }
        else {

            for (CommandDetail commandDetail : GlobalApp.Business.SelectedCommand.GetDetails()) {
                if (product.GetId() == commandDetail.GetProduct().GetId()) {
                    newQuantity = commandDetail.GetQuantity() + 1;
                    commandDetail.SetQuantity(newQuantity);
                    isNewElement = false;
                    break;
                }
            }

            if (isNewElement) {
                CommandDetail commandDetail = new CommandDetail(product, newQuantity);
                GlobalApp.Business.SelectedCommand.GetDetails().add(commandDetail);
            }
        }

      return newQuantity;
    }


}
