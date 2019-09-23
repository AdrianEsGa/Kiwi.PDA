package com.xander.kiwipda.ViewModel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Product> items;

    public ProductAdapter(Activity activity, ArrayList<Product> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Product> product) {
        for (int i = 0; i < product.size(); i++) {
            items.add(product.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.listviewitem_product, null);
        }

        Product product = items.get(position);

        TextView title = view.findViewById(R.id.TextViewProductName);
        title.setText(product.GetName());

        TextView quantity = view.findViewById(R.id.TextViewQuantity);
        quantity.setText(String.valueOf(product.GetQuantity()));

        ImageView imagen = view.findViewById(R.id.ImageViewProduct);
        imagen.setImageDrawable(product.GetImage());

        return view;
    }
}
