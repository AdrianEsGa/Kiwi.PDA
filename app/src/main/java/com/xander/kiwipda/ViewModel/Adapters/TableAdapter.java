package com.xander.kiwipda.ViewModel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xander.kiwipda.Model.Entities.Table;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class TableAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Table> items;

    public TableAdapter (Activity activity, ArrayList<Table> items) {
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

    public void addAll(ArrayList<Table> table) {
        for (int i = 0; i < table.size(); i++) {
            items.add(table.get(i));
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
            view = inf.inflate(R.layout.listviewitem_table, null);
        }

        Table table = items.get(position);

        TextView title = view.findViewById(R.id.TextViewTableName);
        title.setText(table.GetName());

        ImageView imagen = view.findViewById(R.id.ImageViewTable);
        imagen.setImageDrawable(table.GetImage());

        return view;
    }
}
