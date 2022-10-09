package com.fatihturker.seslikitap.altSayfalar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fatihturker.seslikitap.AdapterFile.YazarKitapAdapter;
import com.fatihturker.seslikitap.AdapterFile.inter;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.fragments.kitap_bilgi;

import java.util.ArrayList;

public class kategori_fragment_2 extends Fragment {
    ImageView kategori_image;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategori_fragment_2,container,false);
        kategori_image= view.findViewById(R.id.kategori_image);
        recyclerView=view.findViewById(R.id.recylerViewKategori);
        final GlobalClass globalClass = (GlobalClass) getActivity().getApplication();
        String kategori= getArguments().getString("kategori");

        if(kategori.equalsIgnoreCase("KLASİKLER"))
        {
            kategori_image.setImageResource(R.drawable.klasik);
        }
        if(kategori.equalsIgnoreCase("ÇOCUK"))
        {
            kategori_image.setImageResource(R.drawable.cocuk);
        }
        if(kategori.equalsIgnoreCase("ÖYKÜ"))
        {
            kategori_image.setImageResource(R.drawable.oyku);
        }
        if(kategori.equalsIgnoreCase("ROMAN"))
        {
            kategori_image.setImageResource(R.drawable.roman);
        }
        if(kategori.equalsIgnoreCase("POLİSİYE"))
        {
            kategori_image.setImageResource(R.drawable.polisiye);
        }
        ArrayList<String> arrayList =new ArrayList<>();
        for(int i=0;i<globalClass.getKitapkategori().size();i++)
        {
            if(globalClass.getKitapkategori().get(i).equalsIgnoreCase(kategori))
            {
                arrayList.add(globalClass.getKitapResim().get(i));
            }
        }

        final ArrayList<String> kitapresim=new ArrayList<>();
        final ArrayList<String> kitapses=new ArrayList<>();
        final ArrayList<String> kitapseslendirmen=new ArrayList<>();
        final ArrayList<String> kitapyazar=new ArrayList<>();
        final ArrayList<String> kitapozet=new ArrayList<>();
        final ArrayList<String> kitapisim=new ArrayList<>();

        for (int i=0;globalClass.getKitapResim().size()>i;i++)
        {
            if (kategori.equalsIgnoreCase(globalClass.getKitapkategori().get(i)))
            {
                Log.e("kitapkategori","çalıştı");
                kitapresim.add(globalClass.getKitapResim().get(i));
                kitapses.add(globalClass.getKitapseslink().get(i));
                kitapseslendirmen.add(globalClass.getKitapseslendirmen().get(i));
                kitapyazar.add(globalClass.getYazarlar().get(i));
                kitapozet.add(globalClass.getKitapozet().get(i));
                kitapisim.add(globalClass.getKitapisim().get(i));
            }
        }

        YazarKitapAdapter adapter= new YazarKitapAdapter(arrayList,getActivity(),new inter(){
            @Override
            public void onItemClick(View v, int position) {
                Log.e("tıklama","tıklama yapıldı");
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


        return view;
    }
}
