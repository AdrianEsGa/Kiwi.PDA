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

import com.xander.kiwipda.Model.Entities.Command;
import com.xander.kiwipda.Model.Entities.CommandStatus;
import com.xander.kiwipda.R;

import java.util.ArrayList;

public class CommandAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Command> items;

    public CommandAdapter(Activity activity, ArrayList<Command> items) {
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

    public void addAll(ArrayList<Command> command) {
        for (int i = 0; i < command.size(); i++) {
            items.add(command.get(i));
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
            view = inf.inflate(R.layout.listviewitem_command, null);
        }

        Command command = items.get(position);

        TextView title = view.findViewById(R.id.TextViewCommandName);
        title.setText(command.GetName());

        TextView description = view.findViewById(R.id.TextViewCommandDescription);
        description.setText(command.GetDescription());

        ImageView imagen = view.findViewById(R.id.ImageViewCommand);
        imagen.setImageDrawable(command.GetImage());

        Button buttonChangeToServed = view.findViewById(R.id.BtnChangeCommandToServed);
        if (command.GetStatus() == CommandStatus.Finalizada){
            buttonChangeToServed.setVisibility(View.VISIBLE);
        }
        else {
            buttonChangeToServed.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}
