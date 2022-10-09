package com.fatihturker.seslikitap.altSayfalar;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatihturker.seslikitap.AdapterFile.YazarKitapAdapter;
import com.fatihturker.seslikitap.AdapterFile.inter;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.fragments.kitap_bilgi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Fatih on 6.10.2019.
 */

public class yazarSayfa extends Fragment {
    TextView yazarisim,yazarbilgi,yazardogumolum;
    ImageView yazarresim;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yazar_sayfa_fragment,container,false);

        yazarisim= view.findViewById(R.id.yazarismi);
        yazarbilgi= view.findViewById(R.id.yazarbilgi);
        yazardogumolum= view.findViewById(R.id.dogumolum);
        yazarresim=view.findViewById(R.id.yazarresim);
        int a =  Integer.parseInt(getArguments().getString("id"));
        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        yazarisim.setText(globalClass.getYazarisim().get(a));
        yazarbilgi.setText(globalClass.getYazarbilgi().get(a));
        yazardogumolum.setText(globalClass.getYdogumolum().get(a));
        recyclerView=view.findViewById(R.id.recylerView);
        adapterislemleri();
        Picasso.with(getContext()).load(globalClass.getYazarresim().get(a)).into(yazarresim);


        return view;
    }
    public void adapterislemleri()
    {
        final ArrayList<String> kitapresim=new ArrayList<>();
        final ArrayList<String> kitapses=new ArrayList<>();
        final ArrayList<String> kitapseslendirmen=new ArrayList<>();
        final ArrayList<String> kitapyazar=new ArrayList<>();
        final ArrayList<String> kitapozet=new ArrayList<>();
        final ArrayList<String> kitapisim=new ArrayList<>();
        final GlobalClass globalClass= (GlobalClass) getActivity().getApplicationContext();
        for (int i=0;globalClass.getKitapResim().size()>i;i++)
        {
            if (yazarisim.getText().equals(globalClass.getYazarlar().get(i)))
            {
                kitapresim.add(globalClass.getKitapResim().get(i));
                kitapses.add(globalClass.getKitapseslink().get(i));
                kitapseslendirmen.add(globalClass.getKitapseslendirmen().get(i));
                kitapyazar.add(globalClass.getYazarlar().get(i));
                kitapozet.add(globalClass.getKitapozet().get(i));
                kitapisim.add(globalClass.getKitapisim().get(i));
            }
        }
        YazarKitapAdapter adapter= new YazarKitapAdapter(kitapresim,getActivity(),new inter(){
            @Override
            public void onItemClick(View v, int position) {
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

                transaction.replace(R.id.framelayout,gosterme).addToBackStack("yazarsayfa").commit();

            }
        });

        LinearLayoutManager lmanager =new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);



        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
