package com.example.mobile4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class objectAdapter extends ArrayAdapter<object> {
    public objectAdapter(Context context, int resource, ArrayList<object> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        object item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notes, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView desc = (TextView) convertView.findViewById(R.id.disc);
        ImageView img =(ImageView) convertView.findViewById(R.id.img);



        name.setText(item.username);
        desc.setText(item.desc);
        img.setBackgroundResource(item.img);
        return convertView;
    }


}
