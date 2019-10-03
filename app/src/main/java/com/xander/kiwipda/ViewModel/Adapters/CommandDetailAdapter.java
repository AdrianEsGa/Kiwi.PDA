package com.xander.kiwipda.ViewModel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xander.kiwipda.Model.Entities.CommandDetail;
import com.xander.kiwipda.Model.Entities.Product;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class CommandDetailAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<CommandDetail> items;
    protected boolean showButtons;

    public CommandDetailAdapter(Activity activity, ArrayList<CommandDetail> items, boolean showButtons) {
        this.activity = activity;
        this.items = items;
        this.showButtons = showButtons;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<CommandDetail> commandDetail) {
        for (int i = 0; i < commandDetail.size(); i++) {
            items.add(commandDetail.get(i));
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

        CommandDetail commandDetail = items.get(position);

        TextView title = view.findViewById(R.id.TextViewProductName);

        if(commandDetail.GetCombinedProduct() == null) {
            title.setText(commandDetail.GetProduct().GetName());
        }
        else {
            title.setText(commandDetail.GetProduct().GetName() + " - " + commandDetail.GetCombinedProduct().GetName());
        }

        TextView quantity = view.findViewById(R.id.TextViewQuantity);
        quantity.setText(String.valueOf(commandDetail.GetQuantity()));

        ImageView imagen = view.findViewById(R.id.ImageViewProduct);
        imagen.setImageDrawable(commandDetail.GetProduct().GetImage());

        if (!showButtons){
            Button btnDecrement = view.findViewById(R.id.btnDecrement);
            Button btnIncrement = view.findViewById(R.id.btnIncrement);
            btnDecrement.setVisibility(View.INVISIBLE);
            btnIncrement.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
