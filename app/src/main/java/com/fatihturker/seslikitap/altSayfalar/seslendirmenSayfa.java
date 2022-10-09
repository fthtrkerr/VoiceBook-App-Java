package com.fatihturker.seslikitap.altSayfalar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter;
import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter3;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.ClassFolder.ListClass;
import com.fatihturker.seslikitap.ClassFolder.ListClass2;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.fragments.kitap_bilgi;

import java.util.ArrayList;

/**
 * Created by Fatih on 6.10.2019.
 */

public class seslendirmenSayfa extends Fragment {
TextView seslendirmenİsim;
ListView kitaplist;
CustomListViewAdapter3 customListViewAdapter3;
ArrayList<ListClass2> listClasses;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seslendirmen_sayfa,container,false);
        GlobalClass globalClass= (GlobalClass) getActivity().getApplicationContext();
        seslendirmenİsim = view.findViewById(R.id.seslendirmen_isim);
        kitaplist=view.findViewById(R.id.kitap_list);

        initialize();
        fillArrayList(listClasses);
     final    ArrayList<String> kitapyazar= new ArrayList<>();
      final   ArrayList<String> kitapresim= new ArrayList<>();
      final   ArrayList<String> kitapseslendirmen= new ArrayList<>();
      final   ArrayList<String> kitapozet= new ArrayList<>();
      final    ArrayList<String> kitapisim= new ArrayList<>();
        final ArrayList<String> kitapses= new ArrayList<>();

        for (int i=0;globalClass.getKitapResim().size()>i;i++)
        {
            if(getArguments().getString("id").equalsIgnoreCase(globalClass.getKitapseslendirmen().get(i))) {
                kitapyazar.add(globalClass.getYazarlar().get(i));
                kitapresim.add(globalClass.getKitapResim().get(i));
                kitapseslendirmen.add(globalClass.getKitapseslendirmen().get(i));
                kitapozet.add(globalClass.getKitapozet().get(i));
                kitapisim.add(globalClass.getKitapisim().get(i));
                kitapses.add(globalClass.getKitapseslink().get(i));
            }
        }


        kitaplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = (getActivity()).getSupportFragmentManager();
                kitap_bilgi gosterme= new kitap_bilgi();
                Bundle args = new Bundle();
                Log.e("tıklama","tıklama yapıldı");

                args.putString("yazarismi", kitapyazar.get(position));
                args.putString("resimurl", kitapresim.get(position));
                args.putString("seslendirmen", kitapseslendirmen.get(position));
                args.putString("ozet", kitapozet.get(position));
                args.putString("kitapismi", kitapisim.get(position));
                args.putString("sesurl", kitapses.get(position));
                FragmentTransaction transaction= manager.beginTransaction();
                gosterme.setArguments(args);

                transaction.replace(R.id.framelayout,gosterme).addToBackStack("seslendirmensayfa").commit();
            }
        });


        seslendirmenİsim.setText(getArguments().getString("id"));
        return view;
            }
    private void initialize() {

        listClasses = new ArrayList<ListClass2>();
        customListViewAdapter3 = new CustomListViewAdapter3(getActivity(),listClasses);
        kitaplist.setAdapter(customListViewAdapter3);
    }

    private void fillArrayList(ArrayList<ListClass2> listClasses) {
        GlobalClass globalClass=(GlobalClass) getActivity().getApplicationContext();
        ArrayList<String> kitapadi=new ArrayList<>();
        ArrayList<String> kitapkapak = new ArrayList<>();
        for (int i=0;globalClass.getKitapResim().size()>i;i++)
        {
            if(getArguments().getString("id").equalsIgnoreCase(globalClass.getKitapseslendirmen().get(i)))
            {
                kitapadi.add(globalClass.getKitapisim().get(i));
                kitapkapak.add(globalClass.getKitapResim().get(i));
            }
        }

        int i=0;
        while(kitapadi.size()>i)
        {
            ListClass2 person = new ListClass2();
            person.setKitapAdi(kitapadi.get(i));
            person.setKitapKapak(kitapkapak.get(i));
            listClasses.add(person);

            i++;
        }


    }
}
