package com.fatihturker.seslikitap.AdapterFile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import com.fatihturker.seslikitap.R;


import java.util.ArrayList;

/**
 * Created by Fatih on 11.10.2019.
 */

public class CustomListViewAdapter2 extends ArrayAdapter<String> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private  ArrayList<String> listClasses;

    public CustomListViewAdapter2(Context context, ArrayList<String> listClasses) {
        super(context,0, listClasses);
        this.context = context;
        this.listClasses = listClasses;
        inflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return listClasses.get(position);
    }

    @Override
    public int getCount() {
        return listClasses.size();
    }


    @Override
    public long getItemId(int position) {
        return listClasses.get(position).hashCode();
    }
    private static class ViewHolder {
        TextView yazarAD;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.listview_item, null);

            holder = new ViewHolder();

            holder.yazarAD = (TextView) convertView.findViewById(R.id.person_name_label);



            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();

        }
        holder.yazarAD.setText(listClasses.get(position));

        return convertView;
    }

}
