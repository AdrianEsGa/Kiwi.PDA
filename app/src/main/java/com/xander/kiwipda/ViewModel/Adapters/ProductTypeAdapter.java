package com.xander.kiwipda.ViewModel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xander.kiwipda.Model.Entities.Employee;
import com.xander.kiwipda.Model.Entities.ProductType;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class ProductTypeAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<ProductType> items;

    public ProductTypeAdapter(Activity activity, ArrayList<ProductType> items) {
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

    public void addAll(ArrayList<ProductType> productTypes) {
        for (int i = 0; i < productTypes.size(); i++) {
            items.add(productTypes.get(i));
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
            view = inf.inflate(R.layout.listviewitem_producttype, null);
        }

        ProductType productType = items.get(position);

        TextView title = view.findViewById(R.id.TextViewProductTypeName);
        title.setText(productType.GetName());

        ImageView imagen = view.findViewById(R.id.ImageViewProductType);
        imagen.setImageDrawable(productType.GetImage());

        return view;
    }
}
