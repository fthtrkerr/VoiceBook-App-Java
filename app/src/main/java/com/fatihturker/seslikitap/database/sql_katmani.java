package com.fatihturker.seslikitap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sql_katmani extends SQLiteOpenHelper {


    public sql_katmani(Context context) {
        super(context,"kiatplar",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "create table  kitaplar (id integer primary key autoincrement"+",kitapismi text not null"+", kitapresmi text not null"+", kitapsure text not null"+", kitaptamsure text not null)";


        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exısts kitaplar");
        onCreate(db);

    }
}
