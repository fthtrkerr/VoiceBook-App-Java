package com.fatihturker.seslikitap.database;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ekleguncellesill {

    public void eklesil(kitaplar k, Context ctx, MediaPlayer getMediaPlayer,String sart)

    {

        verikaynagi vk= new verikaynagi(ctx);
        vk.ac();
        List<kitaplar> kitap= new ArrayList<>();
        kitap=vk.listele();

        if(getMediaPlayer.getDuration()-getMediaPlayer.getCurrentPosition()>100000)
        {
            if(kitap.size()==0)
            {
                vk.kitapEkle(k);
            }
            else
            {


                ArrayList<String> kitaplar = new ArrayList<>();

                for (int i=0;kitap.size()>i;i++)
                {
                    kitaplar.add(kitap.get(i).getKitapismi());
                }
                boolean deger=false;
                for (int i=0;kitaplar.size()>i;i++)
                {
                    if (kitaplar.get(i).equalsIgnoreCase(sart))
                    {
                        deger=true;
                        break;


                    }
                    else
                    {
                        deger=false;

                    }
                }
                if(deger)
                {
                    vk.veriguncelle(k);
                }
                else
                {
                    vk.kitapEkle(k);
                }
            }
        }
        else
        {
            vk.kitapSil(k);

        }
        vk.kapat();
    }

}
