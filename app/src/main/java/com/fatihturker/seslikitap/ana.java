package com.fatihturker.seslikitap;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fatihturker.seslikitap.AdapterFile.Firebase;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.ClassFolder.seslendirmenGetandSet;
import com.fatihturker.seslikitap.ClassFolder.yazarGetandSet;
import com.fatihturker.seslikitap.altSayfalar.dinlemeye_devam_et;
import com.fatihturker.seslikitap.database.ekleguncellesill;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.fatihturker.seslikitap.fragments.Ana_menu;
import com.fatihturker.seslikitap.fragments.kategori_fragment;
import com.fatihturker.seslikitap.fragments.searchFragment;
import com.fatihturker.seslikitap.fragments.seslendirenFragment;
import com.fatihturker.seslikitap.fragments.yazarFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ana extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
DatabaseReference reference,reference2,reference3;
ImageButton btn_play,btn_stop;
TextView kitapadii;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);
        final GlobalClass globalClass= (GlobalClass) getApplicationContext();
        final ArrayList<String> yazarr= new ArrayList<>();
        navigationView = findViewById(R.id.design_navigation_view);

        View hedadView= navigationView.getHeaderView(0);
        btn_play= hedadView.findViewById(R.id.minBtnPlay);
        kitapadii=hedadView.findViewById(R.id.kitapadi);
        btn_play.setVisibility(View.GONE);

        btn_stop = hedadView.findViewById(R.id.minBtnStop);
        btn_stop.setVisibility(View.GONE);
        kitapadii.setVisibility(View.GONE);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kitaplar k = new kitaplar();
                ekleguncellesill eg=new ekleguncellesill();
                String sart=globalClass.getPlayBook();
                k.setKitapismi(globalClass.getPlayBook());
                k.setKitapresmi(globalClass.getResim());
                k.setKitaptamsure(String.valueOf(globalClass.getMediaPlayer().getDuration()));
                k.setKitapsure(String.valueOf(globalClass.getMediaPlayer().getCurrentPosition()));

                eg.eklesil(k,getApplication(),globalClass.getMediaPlayer(),sart);
                globalClass.getMediaPlayer().stop();

                globalClass.setMediaPlayer(null);
                btn_play.setVisibility(View.GONE);
                kitapadii.setVisibility(View.GONE);
                btn_stop.setVisibility(View.GONE);

            }
        });








        final FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        kategori_fragment kategori= new kategori_fragment();
        Ana_menu sayfa=new Ana_menu();
        transaction.replace(R.id.framelayout,sayfa).commit();

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        navigationView= findViewById(R.id.design_navigation_view);

        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toogle= new ActionBarDrawerToggle(this,drawer,toolbar,
               R.string.app_name,R.string.app_name);
        drawer.addDrawerListener(toogle);
        toogle.syncState();

        reference = FirebaseDatabase.getInstance().getReference("Global");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                   showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        reference2=FirebaseDatabase.getInstance().getReference("Yazarlar");

        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showDataYazar(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference3= FirebaseDatabase.getInstance().getReference("Seslendirmenler");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              showDataSeslendirmen(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(globalClass.getMediaPlayer()!=null)
        {
            if(globalClass.getMediaPlayer().isPlaying())
            {
                btn_play.setImageResource(R.drawable.ic_pausee);
            }
        }



         btn_play.setOnClickListener(new View.OnClickListener() {
       @Override
        public void onClick(View v) {
           Log.e("tıklama","tıklama yapıldı...");
           ekleguncellesill eg = new ekleguncellesill();

           if(globalClass.getMediaPlayer().isPlaying()){
               kitaplar k = new kitaplar();
               globalClass.getMediaPlayer().pause();
               String sart=globalClass.getPlayBook();
               k.setKitapismi(globalClass.getPlayBook());
               k.setKitapresmi(globalClass.getResim());
               k.setKitaptamsure(String.valueOf(globalClass.getMediaPlayer().getDuration()));
               k.setKitapsure(String.valueOf(globalClass.getMediaPlayer().getCurrentPosition()));

               eg.eklesil(k,getApplication(),globalClass.getMediaPlayer(),sart);
               Log.e("veritabanı","drawer menüde kayıt işlemi başarılı bir şekilde yapıldı.");
               btn_play.setImageResource(R.drawable.ic_playy);
           }

           else{globalClass.getMediaPlayer().start();
               btn_play.setImageResource(R.drawable.ic_pausee);
           }

            }
          });

    }

    private void showData(DataSnapshot dataSnapshot)
    {
        GlobalClass globalClass= (GlobalClass) getApplicationContext();
        Firebase fb= new Firebase();
        ArrayList<String> ekleme=new ArrayList<>();
        ArrayList<String> resimekleme=new ArrayList<>();
        ArrayList<String> kitapisimekleme=new ArrayList<>();
        ArrayList<String> kitapsesekleme=new ArrayList<>();
        ArrayList<String> kitapozetekleme=new ArrayList<>();
        ArrayList<String> kitapseslendirmeniekleme=new ArrayList<>();
        ArrayList<String> kitapsure=new ArrayList<>();
        ArrayList<String> kitapkategori=new ArrayList<>();
        for (DataSnapshot ds:dataSnapshot.getChildren())
        {
            fb.setKitapyazarı(ds.getValue(Firebase.class).getKitapyazarı());
            fb.setResim(ds.getValue(Firebase.class).getResim());
            fb.setKitapismi(ds.getValue(Firebase.class).getKitapismi());
            fb.setKitapseslendirmeni(ds.getValue(Firebase.class).getKitapseslendirmeni());
            fb.setOzet(ds.getValue(Firebase.class).getOzet());
            fb.setSeslink(ds.getValue(Firebase.class).getSeslink());
            fb.setKitapkategori(ds.getValue(Firebase.class).getKitapkategori());
            fb.setKitapsure(ds.getValue(Firebase.class).getKitapsure());

            ekleme.add(fb.getKitapyazarı());
            resimekleme.add(fb.getResim());
            kitapisimekleme.add(fb.getKitapismi());
            kitapsesekleme.add(fb.getSeslink());
            kitapozetekleme.add(fb.getOzet());
            kitapseslendirmeniekleme.add(fb.getKitapseslendirmeni());
            kitapsure.add(fb.getKitapsure());
            kitapkategori.add(fb.getKitapkategori());

        }


        globalClass.setYazarlar(ekleme);
        globalClass.setKitapResim(resimekleme);
        globalClass.setKitapozet(kitapozetekleme);
        globalClass.setKitapisim(kitapisimekleme);
        globalClass.setKitapseslink(kitapsesekleme);
        globalClass.setKitapseslendirmen(kitapseslendirmeniekleme);
        globalClass.setKitapkategori(kitapkategori);
        globalClass.setKitapsure(kitapsure);
    }
    private void showDataYazar(DataSnapshot dataSnapshot)
    {
        GlobalClass globalClass= (GlobalClass) getApplicationContext();
        yazarGetandSet ygt=new yazarGetandSet();
        ArrayList<String> isimekle=new ArrayList<>();
        ArrayList<String> resimekleme=new ArrayList<>();
        ArrayList<String> dtotekle=new ArrayList<>();
        ArrayList<String> biligekleme=new ArrayList<>();
        for (DataSnapshot ds:dataSnapshot.getChildren())
        {
            ygt.setYazarisim(ds.getValue(yazarGetandSet.class).getYazarisim());
            ygt.setYazarresim(ds.getValue(yazarGetandSet.class).getYazarresim());
            ygt.setDogumolum(ds.getValue(yazarGetandSet.class).getDogumolum());
            ygt.setYazarbilgi(ds.getValue(yazarGetandSet.class).getYazarbilgi());

            Log.e("veri",ygt.getDogumolum());
            dtotekle.add(ygt.getDogumolum());
            isimekle.add(ygt.getYazarisim());
            resimekleme.add(ygt.getYazarresim());
            biligekleme.add(ygt.getYazarbilgi());

        }


        globalClass.setYazarisim(isimekle);
        globalClass.setYazarresim(resimekleme);
        globalClass.setYdogumolum(dtotekle);
        globalClass.setYazarbilgi(biligekleme);
    }

    private void showDataSeslendirmen(DataSnapshot dataSnapshot)
    {
        GlobalClass globalClass=(GlobalClass) getApplicationContext();
        seslendirmenGetandSet sesget = new seslendirmenGetandSet();
        ArrayList<String> seslendirmenAdi=new ArrayList<>();
         for (DataSnapshot ds:dataSnapshot.getChildren())
         {
          sesget.setSeslendirmenAdi(ds.getValue(seslendirmenGetandSet.class).getSeslendirmenAdi());
          seslendirmenAdi.add(sesget.getSeslendirmenAdi());
          Log.e("seslendirmen adi",sesget.getSeslendirmenAdi());

         }
         globalClass.setSeslendirmenAdi(seslendirmenAdi);
    }



    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.onerimenu: getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Ana_menu())
                    .addToBackStack("ana_menu").commit();
                ;break;
            case R.id.kategorimenu: getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new kategori_fragment())
                    .addToBackStack("ana_menu").commit();
                ;break;
            case R.id.aramenu: getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new searchFragment())
                    .addToBackStack("kategori").commit()
                 ;break;
            case R.id.yazarmenu: getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new yazarFragment())
                    .addToBackStack("arama").commit()
            ;break;
            case R.id.seslendirmenmenu: getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new seslendirenFragment())
                .addToBackStack("yazar").commit()
            ;break;
            case R.id.dinlemeye_devam:getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new dinlemeye_devam_et())
                    .addToBackStack("yazar").commit()
            ;break;

        }


        return true;
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {

        GlobalClass globalClass=(GlobalClass) getApplicationContext();
        if(globalClass.getMediaPlayer()!=null)
        {
                kitaplar k = new kitaplar();
            ekleguncellesill eg=new ekleguncellesill();
            globalClass.getMediaPlayer().pause();
            String sart=globalClass.getPlayBook();
            k.setKitapismi(globalClass.getPlayBook());
            k.setKitapresmi(globalClass.getResim());
            k.setKitaptamsure(String.valueOf(globalClass.getMediaPlayer().getDuration()));
            k.setKitapsure(String.valueOf(globalClass.getMediaPlayer().getCurrentPosition()));

            eg.eklesil(k,getApplication(),globalClass.getMediaPlayer(),sart);

        }

        super.onDestroy();
    }
}
