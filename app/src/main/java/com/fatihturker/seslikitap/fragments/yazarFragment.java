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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.ClassFolder.ListClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.altSayfalar.yazarSayfa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 6.10.2019.
 */

public class yazarFragment extends Fragment {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> Yazar;
    private ArrayList<ListClass> listClasses;
    private CustomListViewAdapter customListViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yazar_fragment,container,false);

        listView= view.findViewById(R.id.listVieww);
        final ArrayList<String> TekilYazarlar=new ArrayList<>();
        final GlobalClass globalClass= (GlobalClass) getActivity().getApplicationContext();
        ArrayList<String> ciftler= new ArrayList<>();
/*
        int b= globalClass.getYazarlar().size();
        for (int i=0;b>i;i++)
        {
            int d=0;
            Log.e("döngü",String.valueOf(i)+"i");
           boolean tY = false;
           for (int a=0;b>a;a++)
           {
               Log.e("döngü",String.valueOf(a)+"a");
               if (globalClass.getYazarlar().get(i).equalsIgnoreCase(globalClass.getYazarlar().get(a)))
               {

                  d++;


               }

           }
            if(d>1)
            {
                ciftler.add(globalClass.getYazarlar().get(i));
            }
           if (d==1)
           {
               Log.e("veri aktarıldı",String.valueOf(i)+".Veri aktarıldı");
               TekilYazarlar.add(globalClass.getYazarlar().get(i));

           }


            Log.e("dönggü sonu",String.valueOf(i)+"indeksli değer");
        }
        for (int i=0;ciftler.size()>i;i=+2)
        {
            TekilYazarlar.add(ciftler.get(i));
        }



        arrayAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,globalClass.getYazarisim());
        listView.setAdapter(arrayAdapter);

*/
                        initialize();
                        fillArrayList(listClasses);
        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.yazarlar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                yazarSayfa gosterme= new yazarSayfa();
                Bundle args = new Bundle();



                args.putString("id", String.valueOf(position));

                FragmentTransaction transaction= manager.beginTransaction();
                gosterme.setArguments(args);

                transaction.replace(R.id.framelayout,gosterme).addToBackStack("ana_menu").commit();
            }
        });






        return view;

    }
    private void initialize() {
        listClasses = new ArrayList<ListClass>();

        customListViewAdapter = new CustomListViewAdapter(getActivity(),listClasses);
        listView.setAdapter(customListViewAdapter);
    }

    private void fillArrayList(ArrayList<ListClass> listClasses) {
        GlobalClass globalClass=(GlobalClass) getActivity().getApplicationContext();
        int i=0;
        while(globalClass.getYazarisim().size()>i)
        {
            ListClass person = new ListClass();
            person.setYazarAdi(globalClass.getYazarisim().get(i));
            person.setYazarFoto(globalClass.getYazarresim().get(i));
            listClasses.add(person);

            i++;
        }


    }


}
