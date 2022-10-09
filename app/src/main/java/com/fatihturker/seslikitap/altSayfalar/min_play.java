package com.fatihturker.seslikitap.altSayfalar;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.fragments.kitap_gosterme;

public class min_play extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.min_player,container,false);

        return view;
    }
}
