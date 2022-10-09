package com.fatihturker.seslikitap.AdapterFile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatihturker.seslikitap.ClassFolder.ListClass;
import com.fatihturker.seslikitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 11.10.2019.
 */

public class CustomListViewAdapter extends ArrayAdapter<ListClass> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<ListClass> listClasses;

    public CustomListViewAdapter(Context context, ArrayList<ListClass> listClasses) {
        super(context,0, listClasses);
        this.context = context;
        this.listClasses = listClasses;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listClasses.size();
    }

    @Nullable
    @Override
    public ListClass getItem(int position) {
        return listClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listClasses.get(position).hashCode();
    }
    private static class ViewHolder {
        TextView yazarAD;
        ImageView yazarFOTO;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.listview_item, null);

            holder = new ViewHolder();
            holder.yazarFOTO = (ImageView) convertView.findViewById(R.id.person_image);
            holder.yazarAD = (TextView) convertView.findViewById(R.id.person_name_label);

            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        ListClass listClass = listClasses.get(position);
        if(listClass != null){
            Picasso.with(context).load(listClass.getYazarFoto()).into(holder.yazarFOTO);
            holder.yazarAD.setText(listClass.getYazarAdi());


        }
        return convertView;
    }

}
