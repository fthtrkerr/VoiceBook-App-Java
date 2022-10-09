package com.fatihturker.seslikitap.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter;
import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter2;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;

import com.fatihturker.seslikitap.ClassFolder.ListClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.altSayfalar.seslendirmenSayfa;
import com.fatihturker.seslikitap.altSayfalar.yazarSayfa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 6.10.2019.
 */

public class seslendirenFragment extends Fragment {
    ListView listView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seslendiren_fragment,container,false);
        listView=view.findViewById(R.id.listvieww);
        final GlobalClass globalClass=(GlobalClass) getActivity().getApplicationContext();
        CustomListViewAdapter2 customListViewAdapter2=new CustomListViewAdapter2(getActivity(),globalClass.getSeslendirmenAdi());
        listView.setAdapter(customListViewAdapter2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                seslendirmenSayfa gosterme= new seslendirmenSayfa();
                Bundle args = new Bundle();



                args.putString("id", globalClass.getSeslendirmenAdi().get(position));

                FragmentTransaction transaction= manager.beginTransaction();
                gosterme.setArguments(args);

                transaction.replace(R.id.framelayout,gosterme).addToBackStack("ana_menu").commit();
            }
        });



        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.seslendirmenler);



        return view;
    }




}
