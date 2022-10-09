package com.fatihturker.seslikitap.fragments;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatihturker.seslikitap.AdapterFile.CustomListViewAdapter4_dinleme;
import com.fatihturker.seslikitap.AdapterFile.adapterY;
import com.fatihturker.seslikitap.ClassFolder.GetterSetterClass;
import com.fatihturker.seslikitap.ClassFolder.GlobalClass;
import com.fatihturker.seslikitap.R;
import com.fatihturker.seslikitap.database.kitaplar;
import com.fatihturker.seslikitap.database.verikaynagi;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by Fatih on 26.06.2019.
 */

public class Ana_menu extends android.support.v4.app.Fragment {
    private RecyclerView mBlogView,dinlemeDevamRecy;
    private DatabaseReference mDatabase;
    private Context context;
    public String a="fatih";
    public ArrayAdapter<String> Yazarlar;

    verikaynagi vk;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.ana_menu_fragment, container, false);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),4);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Global");
        ListView dinleme_list;
        List<kitaplar> list;
        List<kitaplar> list2;
        mBlogView= view.findViewById(R.id.myrecylerview);
        vk= new verikaynagi(getContext());
        vk.ac();
        android.support.v7.widget.Toolbar toolbar =getActivity().findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.app_name);

        //vk.verisil();






        mBlogView.setHasFixedSize(true);
        mBlogView.setLayoutManager(layoutManager);
        TextView txt;








        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<GetterSetterClass,BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<GetterSetterClass, BlogViewHolder>
                (GetterSetterClass.class,R.layout.cardview,BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, GetterSetterClass model, int position) {

                viewHolder.setkitapismi(model.getKitapismi());
                viewHolder.setResim(model.getResim(),getContext());
                viewHolder.setKİtapozeti(model.getOzet());
                viewHolder.setKitapyazari(model.getKitapyazarı());
                viewHolder.setSeslendirmen(model.getKitapseslendirmeni());
                viewHolder.setSeslink(model.getSeslink());
                viewHolder.click(getContext());
            }
        };

      mBlogView.setAdapter(firebaseRecyclerAdapter);


    }

     public static class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        String resimurl;

        public BlogViewHolder(final View itemView)
        {
            super(itemView);
            mView=itemView;




        }


       public void click (Context context)
       {

           final Context ctx= context;
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   FragmentManager manager = ((AppCompatActivity)ctx).getSupportFragmentManager();
                    kitap_bilgi gosterme= new kitap_bilgi();
                   Bundle args = new Bundle();
                  TextView yazarismi= itemView.findViewById(R.id.yazarismi);
                  TextView resimurl= itemView.findViewById(R.id.resim_id);
                  TextView seslendirmen= itemView.findViewById(R.id.seslendirmenismi);
                  TextView ozet= itemView.findViewById(R.id.kitapozet);
                  TextView kitapismi= itemView.findViewById(R.id.kitapismi);
                  TextView sesurl= itemView.findViewById(R.id.sesurl);


                   args.putString("yazarismi", yazarismi.getText().toString());
                   args.putString("resimurl", resimurl.getText().toString());
                   args.putString("seslendirmen", seslendirmen.getText().toString());
                   args.putString("ozet", ozet.getText().toString());
                   args.putString("kitapismi", kitapismi.getText().toString());
                   args.putString("sesurl", sesurl.getText().toString());
                   FragmentTransaction transaction= manager.beginTransaction();
                   gosterme.setArguments(args);
                   Log.e("resimurl",resimurl.getText().toString());
                   transaction.replace(R.id.framelayout,gosterme).addToBackStack("ana_menu").commit();
               }
           });

       }
         public  void setkitapismi(String kitapismi){
            TextView kitapismii= mView.findViewById(R.id.kitapismi);
            kitapismii.setText(kitapismi);
        }
        public void setSeslendirmen (String kitapseslendirmeni){
             TextView seslendirmen= mView.findViewById(R.id.seslendirmenismi);
             seslendirmen.setText(kitapseslendirmeni);
            Log.e("seslendirmen",kitapseslendirmeni.toString());
        }
         public void setSeslink (String seslink){
             TextView seslendirmen= mView.findViewById(R.id.sesurl);

             Log.e("SesLink",seslink.toString());
             seslendirmen.setText(seslink);
         }
         public void setKİtapozeti (String kitapozeti){
             TextView seslendirmen= mView.findViewById(R.id.kitapozet);
             Log.e("Kİtap Özeti",kitapozeti.toString());
             seslendirmen.setText(kitapozeti);
         }
         public void setKitapyazari(String kitapyazari){
             TextView seslendirmen= mView.findViewById(R.id.yazarismi);
             Log.e("kitapyazari",kitapyazari.toString());
             seslendirmen.setText(kitapyazari);
         }
        public void setResim(String resim,Context ctx)
        {
            ImageView resimm=mView.findViewById(R.id.book_ımage);
            TextView resimurl=mView.findViewById(R.id.resim_id);
            resimurl.setText(resim);

            Picasso.with(ctx).load(Uri.parse(resim)).into(resimm);
            Log.e("resim","ımageview oluşturuldu");
          //  Picasso.with(ctx).load(resim).into(resimm);
            Log.e("resim","resim yüklendi");



        }

    }

    @Override
    public void onResume() {
        vk.ac();
        super.onResume();
    }
}
