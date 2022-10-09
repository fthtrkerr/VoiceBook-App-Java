package com.fatihturker.seslikitap.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.database.ekleguncellesill;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fatih on 27.06.2019.
 */

public class kitap_bilgi extends Fragment {
    ImageView kitapResmi;
    TextView kitapİsmi,kitapSure;
    TextView kitapYazari;
    private InterstitialAd mInterstitialAd;
    TextView kitapSeslendirmeni;
    TextView kitapOzeti;
    ImageButton playbutton;
    List<kitaplar> list;

    verikaynagi vk;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.kitap_bilgi_fragment, container, false);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-1118359777089249/5520880601");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());





        vk = new verikaynagi(getContext());
        vk.ac();
        list = new ArrayList<>();
        list=vk.listele();
        final GlobalClass globalClass= (GlobalClass) getActivity().getApplicationContext();
        int kitapID=0;
        for (int i=0;globalClass.getKitapisim().size()>i;i++)
        {
            if(globalClass.getKitapisim().get(i).equalsIgnoreCase(getArguments().getString("kitapismi")))
            {
                kitapID=i;
                break;
            }
        }
        final int b = kitapID;
         kitapResmi= view.findViewById(R.id.kitapImage);
        Picasso.with(getContext()).load(Uri.parse(getArguments().getString("resimurl"))).into(kitapResmi);
        kitapİsmi = view.findViewById(R.id.bookTitle);
        kitapİsmi.setText(getArguments().getString("kitapismi"));
        kitapYazari = view.findViewById(R.id.yazar);
        kitapYazari.setText("Yazar: "+getArguments().getString("yazarismi"));
        kitapSeslendirmeni = view.findViewById(R.id.seslendirmen);
        kitapSeslendirmeni.setText("Seslendirmen: "+getArguments().getString("seslendirmen"));
        kitapOzeti = view.findViewById(R.id.ozet);
        kitapSure=view.findViewById(R.id.sure);
        kitapOzeti.setText(getArguments().getString("ozet"));
        playbutton= view.findViewById(R.id.playButton);
        kitapSure.setText(globalClass.getKitapsure().get(b));


        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager= getFragmentManager();
                kitap_gosterme gosterme= new kitap_gosterme();
                android.support.v4.app.FragmentTransaction transaction= manager.beginTransaction();
                Bundle args =new Bundle();
                String seslink= getArguments().getString("sesurl");
                args.putString("sesurl",seslink);
                args.putString("kitapsuresi",globalClass.getKitapsure().get(b));
                args.putString("kitapkategorisi",globalClass.getKitapkategori().get(b));
                String a= getArguments().getString("resimurl");
                args.putString("resimurl",a);
                args.putString("kitapismi",getArguments().getString("kitapismi"));
                gosterme.setArguments(args);
                if(globalClass.getMediaPlayer()!=null)
                {
                    if(globalClass.getPlayBook().equalsIgnoreCase(getArguments().getString("kitapismi")))
                    {
                        String kitapismi=globalClass.getPlayBook();
                        vk= new verikaynagi(getContext());
                        vk.ac();
                        list = new ArrayList<>();
                        list=vk.listele();
                        int hatapayi=0;
                        for(int i=0;list.size()>i;i++)
                        {
                            if(kitapismi.equalsIgnoreCase(list.get(i).getKitapismi()))
                            {
                                String id= String.valueOf(i);
                                args.putString("listID",id);
                                args.putString("online","online");
                                args.putString("veritabani","kayitvar");
                                if (mInterstitialAd.isLoaded()) {
                                    mInterstitialAd.show();
                                } else {
                                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                                }
                                transaction.replace(R.id.framelayout,gosterme).addToBackStack("kitap_bilgi").commit();
                            }
                            else
                            {
                              hatapayi++;
                            }
                        }
                        if(hatapayi==list.size())
                        {
                            args.putString("online","online");
                            args.putString("veritabani","kayityok");
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.");
                            }
                            transaction.replace(R.id.framelayout,gosterme).addToBackStack("kitap_bilgi").commit();
                        }






                    }
                    else
                    {

                        args.putString("online","offline");
                        kitaplar k = new kitaplar();
                        ekleguncellesill eg=new ekleguncellesill();
                        k.setKitapresmi(globalClass.getResim());
                        k.setKitapismi(globalClass.getPlayBook());
                        k.setKitaptamsure(String.valueOf(globalClass.getMediaPlayer().getDuration()));
                        k.setKitapsure(String.valueOf(globalClass.getMediaPlayer().getCurrentPosition()));
                        eg.eklesil(k,getContext(),globalClass.getMediaPlayer(),globalClass.getPlayBook());
                        globalClass.getMediaPlayer().stop();;
                        globalClass.setMediaPlayer(null);
                        int hatapayi=0;
                        for(int i=0;list.size()>i;i++)
                        {
                            if(getArguments().getString("kitapismi").equalsIgnoreCase(list.get(i).getKitapismi()))
                            {
                                String id= String.valueOf(i);
                                args.putString("listID",id);
                                args.putString("veritabani","kayitvar");
                                if (mInterstitialAd.isLoaded()) {
                                    mInterstitialAd.show();
                                } else {
                                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                                }

                                transaction.replace(R.id.framelayout,gosterme).addToBackStack("kitap_bilgi").commit();
                            }
                            else
                            {
                                hatapayi++;
                            }
                        }
                        if(hatapayi==list.size())
                        {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.");
                            }
                            args.putString("listID","");
                            args.putString("veritabani","kayityok");
                            transaction.replace(R.id.framelayout,gosterme).addToBackStack("kitap_bilgi").commit();

                        }

                       //

                    }

                }
                else
                {
                    boolean gecis=false;
                    for(int i=0;list.size()>i;i++)
                    {
                        if(getArguments().getString("kitapismi").equalsIgnoreCase(list.get(i).getKitapismi()))
                        {
                            String id= String.valueOf(i);
                            args.putString("listID",id);
                            args.putString("online","offline");
                            args.putString("veritabani","kayitvar");
                            args.putString("sesurl",getArguments().getString("sesurl"));
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.");
                            }

                            transaction.replace(R.id.framelayout,gosterme).addToBackStack("kitap_bilgi").commit();
                            gecis=true;


                        }
                    }
                    if(gecis==false) {
                        args.putString("online", "offline");
                        args.putString("veritabani", "kayityok");

                        args.putString("sesurl",getArguments().getString("sesurl"));
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG", "The interstitial wasn't loaded yet.");
                        }
                        transaction.replace(R.id.framelayout, gosterme).addToBackStack("kitap_bilgi").commit();
                    }
                }




            }
        });


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
