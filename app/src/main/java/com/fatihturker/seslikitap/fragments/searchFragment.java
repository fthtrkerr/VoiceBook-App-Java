package com.fatihturker.seslikitap.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.altSayfalar.seslendirmenSayfa;
import com.fatihturker.seslikitap.altSayfalar.yazarSayfa;

import java.util.ArrayList;

/**
 * Created by Fatih on 6.10.2019.
 */

public class searchFragment extends Fragment {

EditText searchEdit;
ArrayAdapter spinnerAdapter;
ListView list_search;
Spinner spinner;
int spinnerInfo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.search_fragment,container,false);
         spinnerInfo=0;
        searchEdit = view.findViewById(R.id.searchEdit);
        list_search = view.findViewById(R.id.listView_search);

       final GlobalClass globalClass=(GlobalClass) getActivity().getApplicationContext();
        spinner=view.findViewById(R.id.spinner);
        String[] secenekler={"Kitap","Yazar","Seslendirmen"};
        spinnerAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_dropdown_item_1line,secenekler);
        spinner.setAdapter(spinnerAdapter);

        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.ara);

        final ArrayAdapter<String> search_adaptor=new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,globalClass.getKitapisim());
        list_search.setAdapter(search_adaptor);

        final ArrayAdapter<String> kitap_adaptor=new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,globalClass.getKitapisim());

        final ArrayAdapter<String> yazar_adaptor=new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,globalClass.getYazarisim());

        final ArrayAdapter<String> seslendirmen_adaptor=new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,globalClass.getSeslendirmenAdi());
        list_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerInfo==0)
                {
                    String secilen= String.valueOf(list_search.getItemAtPosition(i));
                    String kitapyazar,kitapresim="",kitapseslendirmen="",kitapozet="",kitapisim="",kitapses="";
                    kitapyazar="";
                    for (int a=0;globalClass.getKitapisim().size()>a;a++) {
                        if (secilen.equalsIgnoreCase(globalClass.getKitapisim().get(a)))
                        {
                            kitapyazar = globalClass.getYazarlar().get(a);
                            kitapresim=globalClass.getKitapResim().get(a);
                            kitapseslendirmen=globalClass.getKitapseslendirmen().get(a);
                            kitapozet=globalClass.getKitapozet().get(a);
                            kitapisim=globalClass.getKitapisim().get(a);
                            kitapses=globalClass.getKitapseslink().get(a);
                        }
                    }


                  Log.e("seçilen",secilen);
                    Log.e("tıklama","tıklama yapıldı");
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    kitap_bilgi gosterme= new kitap_bilgi();
                    Bundle args = new Bundle();
                    Log.e("tıklama","tıklama yapıldı");

                    args.putString("yazarismi", kitapyazar);
                    args.putString("resimurl", kitapresim);
                    args.putString("seslendirmen", kitapseslendirmen);
                    args.putString("ozet", kitapozet);
                    args.putString("kitapismi", kitapisim);
                    args.putString("sesurl", kitapses);
                    FragmentTransaction transaction= manager.beginTransaction();
                    gosterme.setArguments(args);

                    transaction.replace(R.id.framelayout,gosterme).addToBackStack("aramasayfa").commit();
                }
                else if(spinnerInfo==1)
                {
                    String secilen= String.valueOf(list_search.getItemAtPosition(i));
                    String kitapyazar="";

                    kitapyazar="";
                    for (int a=0;globalClass.getYazarisim().size()>a;a++) {
                        if (secilen.equalsIgnoreCase(globalClass.getYazarisim().get(a)))
                        {
                            kitapyazar=String.valueOf(a);
                        }
                    }


                    Log.e("seçilen",secilen);
                    Log.e("tıklama","tıklama yapıldı");
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    yazarSayfa gosterme= new yazarSayfa();
                    Bundle args = new Bundle();
                    Log.e("tıklama","tıklama yapıldı");

                    args.putString("id", kitapyazar);

                    FragmentTransaction transaction= manager.beginTransaction();
                    gosterme.setArguments(args);

                    transaction.replace(R.id.framelayout,gosterme).addToBackStack("aramasayfa").commit();
                }
                else if(spinnerInfo==2)
                {
                    String secilen= String.valueOf(list_search.getItemAtPosition(i));
                    String kitapyazar="";

                    kitapyazar="";
                    for (int a=0;globalClass.getKitapseslendirmen().size()>a;a++) {
                        if (secilen.equalsIgnoreCase(globalClass.getKitapseslendirmen().get(a)))
                        {
                            kitapyazar=globalClass.getKitapseslendirmen().get(a);
                        }
                    }


                    Log.e("seçilen",secilen);
                    Log.e("tıklama","tıklama yapıldı");
                    FragmentManager manager = (getActivity()).getSupportFragmentManager();
                    seslendirmenSayfa gosterme= new seslendirmenSayfa();
                    Bundle args = new Bundle();
                    Log.e("tıklama","tıklama yapıldı");

                    args.putString("id", kitapyazar);

                    FragmentTransaction transaction= manager.beginTransaction();
                    gosterme.setArguments(args);

                    transaction.replace(R.id.framelayout,gosterme).addToBackStack("aramasayfa").commit();
                }

            }
        });

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               if(i==0)
               {
                   spinnerInfo=i;
                   list_search.setAdapter(kitap_adaptor);
               }
               else if(i==1)
               {
                   spinnerInfo=i;
                   list_search.setAdapter(yazar_adaptor);
               }
               else if(i==2)
               {
                   spinnerInfo=i;
                   list_search.setAdapter(seslendirmen_adaptor);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });







       searchEdit.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(spinnerInfo==0)
               {
                   kitap_adaptor.getFilter().filter(charSequence);
               }
               else if(spinnerInfo==1)
               {
                   yazar_adaptor.getFilter().filter(charSequence);
               }
               else if(spinnerInfo==2)
               {
                   seslendirmen_adaptor.getFilter().filter(charSequence);
               }
               else
               {
                   search_adaptor.getFilter().filter(charSequence);
               }



               // TODO: 28.11.2019 görünümleri ayarla 
              
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
        return view;
    }
}
