package com.fatihturker.seslikitap.AdapterFile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatihturker.seslikitap.ClassFolder.ListClass;
import com.fatihturker.seslikitap.ClassFolder.ListClass2;
import com.fatihturker.seslikitap.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fatih on 11.10.2019.
 */

public class CustomListViewAdapter3 extends ArrayAdapter<ListClass2> {
    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<ListClass2> listClasses;

    public CustomListViewAdapter3(Context context, ArrayList<ListClass2> listClasses) {
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
    public ListClass2 getItem(int position) {
        return listClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listClasses.get(position).hashCode();
    }
    private static class ViewHolder {
        TextView yazarAD;
        TextView format;
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
            holder.format= convertView.findViewById(R.id.person_address_label);

            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
            holder = (ViewHolder)convertView.getTag();
        }

        ListClass2 listClass = listClasses.get(position);
        if(listClass != null){
            Picasso.with(context).load(listClass.getKitapKapak()).into(holder.yazarFOTO);
            holder.yazarAD.setText(listClass.getKitapAdi());
            holder.format.setText("Format: Sesli Kitap");


        }
        return convertView;
    }

}
