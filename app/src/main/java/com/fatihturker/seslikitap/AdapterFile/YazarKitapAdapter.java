package com.fatihturker.seslikitap.AdapterFile;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.fatihturker.seslikitap.ClassFolder.YazarKitapGetandSet;
import com.fatihturker.seslikitap.ClassFolder.image;
import com.fatihturker.seslikitap.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 11.03.2018.
 */

public class YazarKitapAdapter extends RecyclerView.Adapter<YazarKitapAdapter.ViewHolder> {




    ArrayList<String> dress_lists;
    Context context;
    inter list;




    public YazarKitapAdapter(ArrayList<String> dress_lists, Context context, inter list) {
        Log.e("recylerview adapter","recyler view adatper çalıştı");
        this.dress_lists = dress_lists;
        this.context=context;
        this.list=list;




    }





     public  static class ViewHolder extends RecyclerView.ViewHolder {


         ImageView dressImagel;



        public ViewHolder(View view) {
            super(view);
             dressImagel= view.findViewById(R.id.book_ımage);



        }



     }


    @NonNull
    @Override
    public YazarKitapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               list.onItemClick(v,view_holder.getPosition());
            }
        });


        return view_holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("recylerview","nesne atandı");

        Picasso.with(context).load(dress_lists.get(position)).into(holder.dressImagel);





    }

    @Override
    public int getItemCount() {
        return dress_lists.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }





}
