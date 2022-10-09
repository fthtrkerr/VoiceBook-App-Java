package com.fatihturker.seslikitap.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Printer;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.database.ekleguncellesill;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;


/**
 * Created by Fatih on 26.06.2019.
 */

public class kitap_gosterme extends Fragment implements MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener {
    ImageButton btn_play_pause;
    private SeekBar seekbar;
    ImageView kitapresim;
    MediaPlayer mediaPlayer,getMediaPlayer;
    private int mediaFileLenght,realtimeLenght;
    private TextView textView,minKitapadi;
    ImageButton minBtnPly_Pause,minBtnStop;
    final Handler handler = new Handler();
    NavigationView navigationView;
    private AdView mAdView;
    ImageButton ic_ileri,ic_geri;


    static int sayac=0;
    verikaynagi vk;
    //sql işlemleri
    List<kitaplar> list;






    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //sql işlemleri


        // TODO: 3.12.2019 veri tabanı kayıt işlemi tamamlandı





        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kitap_gosterme, container, false);
        AdView adView = new AdView(getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-1118359777089249/8051481652");

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        btn_play_pause = view.findViewById(R.id.btn_play_pause);
        navigationView = getActivity().findViewById(R.id.design_navigation_view);
        final GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        seekbar = view.findViewById(R.id.seekBar2);
        ic_ileri=view.findViewById(R.id.ic_ileri);
        ic_geri=view.findViewById(R.id.ic_geri);
        kitapresim=view.findViewById(R.id.kitapresim);
        Picasso.with(getContext()).load(getArguments().getString("resimurl")).into(kitapresim);

        View headerView = navigationView.getHeaderView(0);
        textView = view.findViewById(R.id.textTimer);
        textView.setText(getArguments().getString("kitapsuresi"));
        minBtnPly_Pause = headerView.findViewById(R.id.minBtnPlay);
        minKitapadi=headerView.findViewById(R.id.kitapadi);

        minBtnStop = headerView.findViewById(R.id.minBtnStop);
        minBtnPly_Pause.setVisibility(View.GONE);
        minKitapadi.setVisibility(View.GONE);
        minBtnStop.setVisibility(View.GONE);

        vk = new verikaynagi(getContext());



        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(getArguments().getString("kitapismi"));


        ic_ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null)
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
                }
            }
        });
        ic_geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null) {
                    if(mediaPlayer.getCurrentPosition()>10000) {
                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
                    }
                }
            }
        });


             if(getArguments().getString("online").equalsIgnoreCase("online"))
             {
                 seekbar.setMax(globalClass.getMediaPlayer().getDuration());
                 seekbar.setProgress(globalClass.getMediaPlayer().getCurrentPosition());
                 mediaPlayer=new MediaPlayer();
                 mediaPlayer=globalClass.getMediaPlayer();

                 mediaPlayer.setOnBufferingUpdateListener(this);
                 mediaPlayer.setOnCompletionListener(this);
                 realtimeLenght=mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition();
                 textView.setText(String.format("%d:%d:%d",TimeUnit.MILLISECONDS.toHours(realtimeLenght),
                         (TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)-(TimeUnit.MILLISECONDS.toHours(realtimeLenght)*60)),
                         (TimeUnit.MILLISECONDS.toSeconds(realtimeLenght)-TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)*60)
                               ));
                 if(globalClass.getMediaPlayer().isPlaying())
                 {btn_play_pause.setImageResource(R.drawable.ic_pausee);}
                 else
                 {
                     btn_play_pause.setImageResource(R.drawable.ic_playy);
                 }
                 seekbar.setOnTouchListener(new View.OnTouchListener() {
                     @Override
                     public boolean onTouch(View v, MotionEvent motionEvent) {
                         if (mediaPlayer.isPlaying()) {

                             SeekBar seeBbar = (SeekBar) v;
                             int progress = (seeBbar.getProgress());
                             seekbar.setProgress(progress);
                             mediaPlayer.seekTo(progress);
                         }
                         return false;
                     }
                 });


                 btn_play_pause.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if(globalClass.getMediaPlayer().isPlaying())
                         {
                             globalClass.getMediaPlayer().pause();
                             kitaplar k = new kitaplar();
                             ekleguncellesill eg=new ekleguncellesill();
                             k.setKitapsure(String.valueOf(mediaPlayer.getCurrentPosition()));
                             k.setKitaptamsure(String.valueOf(mediaPlayer.getDuration()));
                             k.setKitapismi(getArguments().getString("kitapismi"));
                             k.setKitapresmi(getArguments().getString("resimurl"));
                             eg.eklesil(k,getContext(),mediaPlayer,getArguments().getString("kitapismi"));
                             btn_play_pause.setImageResource(R.drawable.ic_playy);
                             minBtnPly_Pause.setImageResource(R.drawable.ic_playy);
                         }
                         else
                         {
                             globalClass.getMediaPlayer().start();
                             btn_play_pause.setImageResource(R.drawable.ic_pausee);
                             minBtnPly_Pause.setImageResource(R.drawable.ic_pausee);

                             updateSeekBar2();
                         }
                     }
                 });
                 updateSeekBar2();
             }
             else

                 {
                     vk.ac();
                     list = new ArrayList<>();
                     list=vk.listele();
                 //Seekbar ileri geri sardırma ayarı.
                 seekbar.setOnTouchListener(new View.OnTouchListener() {
                     @Override
                     public boolean onTouch(View v, MotionEvent event) {
                         if (mediaPlayer.isPlaying()) {

                             SeekBar seeBbar = (SeekBar) v;
                             int progress = (seeBbar.getProgress());
                             seekbar.setProgress(progress);
                             mediaPlayer.seekTo(progress);
                         }
                         return false;
                     }

                 });

                 btn_play_pause.setOnClickListener(new View.OnClickListener() {
                     ProgressDialog mDiaog = new ProgressDialog(getActivity());

                     @SuppressLint("StaticFieldLeak")
                     @Override
                     public void onClick(View v) {
                         @SuppressLint("StaticFieldLeak") final AsyncTask<String, String, String> mp3Play = new AsyncTask<String, String, String>() {
                             //MediaPlayer'ın yüklenme süreci ve kullanıcıyı bilgilendirme
                             @Override
                             protected void onPreExecute() {
                                 mDiaog.setMessage("Kitap Yükleniyor...");
                                 mDiaog.show();
                             }

                             //MediaPlayere Ses Yolunun Atanma İşlemi
                             @Override
                             protected String doInBackground(String... strings) {
                                 try {
                                     mediaPlayer.setDataSource(strings[0]);
                                     mediaPlayer.prepare();
                                     seekbar.setMax(mediaPlayer.getDuration());

                                 } catch (Exception e) {
                                 }
                                 return "";
                             }

                             //MediaPlayer Başlatma Ve Durdurma
                             @Override
                             protected void onPostExecute(String s) {
                                // mediaFileLenght = mediaPlayer.getDuration();

                                 if (!mediaPlayer.isPlaying()) {

                                     mediaPlayer.start();



                                     vk.ac();
                                     list= vk.listele();

                                     btn_play_pause.setImageResource(R.drawable.ic_pausee);
                                     minBtnPly_Pause.setImageResource(R.drawable.ic_pausee);
                                     minKitapadi.setText(getArguments().getString("kitapismi"));
                                     if(getArguments().getString("veritabani").equalsIgnoreCase("kayitvar"))
                                     {
                                         int a;


                                         a = Integer.parseInt(getArguments().getString("listID"));
                                         int b  = Integer.parseInt(list.get(a).getKitapsure());

                                         mediaPlayer.seekTo(b);
                                     }








                                     globalClass.setMediaPlayer(mediaPlayer);
                                     globalClass.setPlayBook(getArguments().getString("kitapismi"));
                                     globalClass.setResim(getArguments().getString("resimurl"));

                                 } else {
                                     mediaPlayer.pause();




                                     kitaplar k  =new kitaplar();
                                     k.setKitapismi(getArguments().getString("kitapismi"));
                                     k.setKitapresmi(getArguments().getString("resimurl"));
                                     k.setKitapsure(String.valueOf(mediaPlayer.getCurrentPosition()));
                                     k.setKitaptamsure(String.valueOf(mediaPlayer.getDuration()));
                                     ekleguncellesill eg=new ekleguncellesill();

                                     eg.eklesil(k,getContext(),mediaPlayer,getArguments().getString("kitapismi"));

                                     btn_play_pause.setImageResource(R.drawable.ic_playy);
                                     minBtnPly_Pause.setImageResource(R.drawable.ic_playy);
                                 }
                                 updateSeekBar();
                                 mDiaog.dismiss();
                             }

                         };

                         String youtubeLİnk = getArguments().getString("sesurl");
                        mp3Play.execute(youtubeLİnk);


                     }
                 });

                 if (getArguments().getString("online").equalsIgnoreCase("offline")) {
                     //MediaPlayer Tanımlama İşlemleri
                     mediaPlayer = new MediaPlayer();
                     mediaPlayer.setOnBufferingUpdateListener(this);
                     mediaPlayer.setOnCompletionListener(this);

                 }
             }



        return view;
    }

    //MediaPlayer Anlık Güncelleme Methodu
    private void updateSeekBar() {

        if(mediaPlayer.isPlaying())
        {
            seekbar.setProgress(mediaPlayer.getCurrentPosition());
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                    realtimeLenght=mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition();
                    textView.setText(String.format("%d:%d:%d",TimeUnit.MILLISECONDS.toHours(realtimeLenght),
                            (TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)-(TimeUnit.MILLISECONDS.toHours(realtimeLenght)*60)),
                            (TimeUnit.MILLISECONDS.toSeconds(realtimeLenght)-TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)*60)
                                    -TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(realtimeLenght)))));
                }

            };

            handler.postDelayed(updater,1000);
        }
    }

    private void updateSeekBar2() {

        if(mediaPlayer.isPlaying())
        {
            seekbar.setProgress(mediaPlayer.getCurrentPosition());
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar2();
                    realtimeLenght=mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition();
                    textView.setText(String.format("%d:%d:%d",TimeUnit.MILLISECONDS.toHours(realtimeLenght),
                            (TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)-(TimeUnit.MILLISECONDS.toHours(realtimeLenght)*60)),
                            (TimeUnit.MILLISECONDS.toSeconds(realtimeLenght)-TimeUnit.MILLISECONDS.toMinutes(realtimeLenght)*60)
                                    -TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(realtimeLenght)))));
                }

            };

            handler.postDelayed(updater,1000);
        }
    }

    //mediaplayer güncelleme fonksiyonu
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekbar.setSecondaryProgress(percent);

    }

    //mediaplayer kitap bittiğinde ne olacak.
    @Override
    public void onCompletion(MediaPlayer mp) {
        btn_play_pause.setImageResource(R.drawable.ic_playy);
        minBtnPly_Pause.setImageResource(R.drawable.ic_playy);
    }

    @Override
    public void onResume() {
        vk.ac();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        minBtnPly_Pause.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
       if(mediaPlayer!=null) {
           kitaplar k = new kitaplar();
           k.setKitapismi(getArguments().getString("kitapismi"));
           k.setKitapresmi(getArguments().getString("resimurl"));
           k.setKitapsure(String.valueOf(mediaPlayer.getCurrentPosition()));
           k.setKitaptamsure(String.valueOf(mediaPlayer.getDuration()));
           ekleguncellesill eg = new ekleguncellesill();

           eg.eklesil(k, getContext(), mediaPlayer, getArguments().getString("kitapismi"));
       }





        vk.kapat();
        super.onPause();
        minBtnPly_Pause.setVisibility(View.VISIBLE);
        minBtnStop.setVisibility(View.VISIBLE);
        minKitapadi.setVisibility(View.VISIBLE);
    }
}


