package com.example.tanialeif.sistemadecontrolscout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomUserAdapter extends ArrayAdapter {
    public CustomUserAdapter(@NonNull Context context, ArrayList<CustomItemsUsers> customItemsUsers) {
        super(context, 0, customItemsUsers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout, parent, false);
        }
        CustomItemsUsers items = (CustomItemsUsers) getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.imgCustomSpinner);
        TextView spinnerName = convertView.findViewById(R.id.txtCustomSpinner);
        if(items != null){
            spinnerImage.setImageResource(items.getSpinnerImage());
            spinnerName.setText(items.getSpinnerText());
        }
        return convertView;
    }
}
