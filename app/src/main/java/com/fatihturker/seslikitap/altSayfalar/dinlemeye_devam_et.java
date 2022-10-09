package com.fatihturker.seslikitap.altSayfalar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter4_dinleme;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.fatihturker.seslikitap.fragments.kitap_bilgi;

import java.util.ArrayList;
import java.util.List;

public class dinlemeye_devam_et extends android.support.v4.app.Fragment {
    ListView dinleme_list;
    List<kitaplar> list;

    verikaynagi vk;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dinlemeye_devam_et,container,false);
         vk= new verikaynagi(getContext());
        vk.ac();
        list = new ArrayList<>();
        list=vk.listele();
        CustomListViewAdapter4_dinleme adapter = new CustomListViewAdapter4_dinleme(getContext(),getActivity().getApplicationContext(),list);
        dinleme_list= view.findViewById(R.id.dinleme_list);
        dinleme_list.setAdapter(adapter);


        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.dinlemeyedevam);
        final GlobalClass globalClass= (GlobalClass) getActivity().getApplicationContext();








        return view;
    }

    @Override
    public void onResume() {
        vk.ac();
        super.onResume();
    }

    @Override
    public void onPause() {
        vk.kapat();
        super.onPause();
    }
}
