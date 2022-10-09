package com.fatihturker.seslikitap.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.altSayfalar.kategori_fragment_2;

import static com.fatihturker.seslikitap.R.string.kategori;

/**
 * Created by Fatih on 28.09.2019.
 */

public class kategori_fragment extends android.support.v4.app.Fragment {
ImageButton klasik,oyku,cocuk,polisiye,roman;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.kategoriler,container,false);
        klasik=view.findViewById(R.id.klasik);
        oyku=view.findViewById(R.id.oyku);
        cocuk=view.findViewById(R.id.cocuk);
        polisiye=view.findViewById(R.id.polisiye);
        roman=view.findViewById(R.id.roman);
        final kategori_fragment_2 kategori=new kategori_fragment_2();
        FragmentManager manager=getFragmentManager();
       final FragmentTransaction transaction=manager.beginTransaction();
        final Bundle args=new Bundle();
         DrawerLayout drawer;
        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.kategori);

        klasik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("kategori","KLASİKLER");
                kategori.setArguments(args);

                transaction.replace(R.id.framelayout,kategori).addToBackStack("kategori").commit();

            }
        });

        oyku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("kategori","ÖYKÜ");
                kategori.setArguments(args);

                transaction.replace(R.id.framelayout,kategori).addToBackStack("kategori").commit();
            }
        });

        cocuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("kategori","ÇOCUK");
                kategori.setArguments(args);

                transaction.replace(R.id.framelayout,kategori).addToBackStack("kategori").commit();

            }
        });

        polisiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("kategori","POLİSİYE");
                kategori.setArguments(args);

                transaction.replace(R.id.framelayout,kategori).addToBackStack("kategori").commit();
            }
        });

        roman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                args.putString("kategori","ROMAN");
                kategori.setArguments(args);

                transaction.replace(R.id.framelayout,kategori).addToBackStack("kategori").commit();
            }
        });


        return view;
    }

}
