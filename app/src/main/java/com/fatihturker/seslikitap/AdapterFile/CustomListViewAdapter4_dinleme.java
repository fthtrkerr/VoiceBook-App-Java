package com.fatihturker.seslikitap.AdapterFile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.fatihturker.seslikitap.fragments.kitap_bilgi;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fatih on 11.10.2019.
 */

public class CustomListViewAdapter4_dinleme extends ArrayAdapter<kitaplar> {
    private final LayoutInflater inflater;
    private final Context context;
    private final Context ctx2;
    private ViewHolder holder;
    private List<kitaplar> listClasses;

    public CustomListViewAdapter4_dinleme(Context context, Context ctx2,List<kitaplar> listClasses) {
        super(context,0, listClasses);
        this.context = context;
        this.listClasses = listClasses;
        this.ctx2=ctx2;
        inflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public kitaplar getItem(int position) {
        return listClasses.get(position);
    }

    @Override
    public int getCount() {
        return listClasses.size();
    }


    @Override
    public long getItemId(int position) {
        return listClasses.get(position).hashCode();
    }
    private static class ViewHolder {
        SeekBar seekBar;
        ImageView ımageView;
        TextView textView;
        TextView txtSure;


    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.custom_listview_dinlemeye_devam_et, null);

            holder = new ViewHolder();

            holder.seekBar = convertView.findViewById(R.id.dinleme_seekbar);
            holder.ımageView=convertView.findViewById(R.id.kitap_ımage_dinleme);
            holder.textView=convertView.findViewById(R.id.dinleme_kitap_isim);
            holder.txtSure=convertView.findViewById(R.id.kitap_kalan_sure);



            convertView.setTag(holder);

        }
        else{
            //Get viewholder we already created
        holder = (ViewHolder)convertView.getTag();

        }
        holder.textView.setText(listClasses.get(position).getKitapismi());
        holder.txtSure.setText(String.valueOf(listClasses.get(position).getKitapsure()));
        int tamsure = Integer.parseInt(listClasses.get(position).getKitaptamsure());
        int bulundugusure=  Integer.parseInt(listClasses.get(position).getKitapsure()) ;
        int kalansure= tamsure-bulundugusure;
        holder.seekBar.setEnabled(false);

        holder.seekBar.setMax(tamsure);
        holder.seekBar.setProgress(bulundugusure);
        holder.txtSure.setText(String.format("%d:%d:%d", TimeUnit.MILLISECONDS.toHours(kalansure),
                (TimeUnit.MILLISECONDS.toMinutes(kalansure)-(TimeUnit.MILLISECONDS.toHours(kalansure)*60)),
                (TimeUnit.MILLISECONDS.toSeconds(kalansure)-TimeUnit.MILLISECONDS.toMinutes(kalansure)*60)
        ));

        Picasso.with(context).load(listClasses.get(position).getKitapresmi()).into(holder.ımageView);

        verikaynagi vk;




      final   GlobalClass globalClass = (GlobalClass) ctx2 ;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("dinlemelsit","tıklama yapıldı");


                for (int a=0;globalClass.getKitapisim().size()>a;a++)
                {
                    Log.e("getCount",String.valueOf(getCount()));
                    if(listClasses.get(position).getKitapismi().equalsIgnoreCase(globalClass.getKitapisim().get(a)))
                    {
                        FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                        kitap_bilgi gosterme= new kitap_bilgi();
                        Bundle args = new Bundle();
                        args.putString("yazarismi", globalClass.getYazarlar().get(a));
                        args.putString("resimurl", globalClass.getKitapResim().get(a));
                        args.putString("seslendirmen", globalClass.getKitapseslendirmen().get(a));
                        args.putString("ozet", globalClass.getKitapozet().get(a));
                        args.putString("kitapismi", globalClass.getKitapisim().get(a));
                        args.putString("sesurl", globalClass.getKitapseslink().get(a));
                        FragmentTransaction transaction= manager.beginTransaction();
                        gosterme.setArguments(args);

                        transaction.replace(R.id.framelayout,gosterme).addToBackStack("ana_menu").commit();

                        break;
                    }
                }


                /*
                int kontrolDegiskeni = 0;
                for (int a=0;globalClass.getKitapisim().size()>a;a++)
                {
                    for (int b=0;listClasses.size()>b;b++)
                    {
                        if(kontrolDegiskeni==1)
                        {
                            break;
                        }
                        if(globalClass.getKitapisim().get(a).equalsIgnoreCase(listClasses.get(b).getKitapismi()))
                        {
                            FragmentManager manager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                            kitap_bilgi gosterme= new kitap_bilgi();
                            Bundle args = new Bundle();
                            args.putString("yazarismi", globalClass.getYazarlar().get(a));
                            args.putString("resimurl", globalClass.getKitapResim().get(a));
                            args.putString("seslendirmen", globalClass.getKitapseslendirmen().get(a));
                            args.putString("ozet", globalClass.getKitapozet().get(a));
                            args.putString("kitapismi", globalClass.getKitapisim().get(a));
                            args.putString("sesurl", globalClass.getKitapseslink().get(a));
                            FragmentTransaction transaction= manager.beginTransaction();
                            gosterme.setArguments(args);

                            transaction.replace(R.id.framelayout,gosterme).addToBackStack("ana_menu").commit();
                            kontrolDegiskeni=1;
                            break;

                        }

                    }


                }*/
            }
        });

       // holder.yazarAD.setText(listClasses.get(position));

        return convertView;
    }

}
