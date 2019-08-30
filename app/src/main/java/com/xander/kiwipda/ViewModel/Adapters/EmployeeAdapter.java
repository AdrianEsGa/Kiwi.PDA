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
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Employee> items;

    public EmployeeAdapter (Activity activity, ArrayList<Employee> items) {
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

    public void addAll(ArrayList<Employee> employee) {
        for (int i = 0; i < employee.size(); i++) {
            items.add(employee.get(i));
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
            view = inf.inflate(R.layout.listviewitem_employee, null);
        }

        Employee employee = items.get(position);

        TextView title = view.findViewById(R.id.category);
        title.setText(employee.GetName());

        TextView description = view.findViewById(R.id.texto);
        description.setText(employee.GetDescription());

        ImageView imagen = view.findViewById(R.id.ImageViewEmployee);
        imagen.setImageDrawable(employee.GetImage());

        return view;
    }
}
