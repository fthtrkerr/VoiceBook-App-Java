package com.fatihturker.seslikitap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class verikaynagi {
  SQLiteDatabase db;
  sql_katmani bdb;
  public verikaynagi(Context c)
  {
      bdb= new sql_katmani(c);

  }
  public void ac()
  {
      db=bdb.getWritableDatabase();
  }
  public void kapat()
  {
      bdb.close();
  }
  public void kitapEkle(kitaplar k)
      {
          ContentValues cv = new ContentValues();
          cv.put("kitapismi",k.getKitapismi());
          cv.put("kitapresmi",k.getKitapresmi());
          cv.put("kitapsure",k.getKitapsure());
          cv.put("kitaptamsure",k.getKitaptamsure());
          db.insert("kitaplar",null,cv);
      }
public void verisil()
{
db.delete("kitaplar",null,null);

    Log.e("veri","tablo silindi");
}
  public List<kitaplar> listele()
  {
      String[] kolonlar = {"kitapismi","kitapresmi","kitapsure","kitaptamsure"};
      Cursor c = db.query("kitaplar",kolonlar,null,null,null,null,null);
      List<kitaplar> liste = new ArrayList<>();
      c.moveToFirst();
      int kId= c.getColumnIndex("kitapismi");
      int Rıd=c.getColumnIndex("kitapresmi");
      int Sıd=c.getColumnIndex("kitapsure");
     int kts=c.getColumnIndex("kitaptamsure");
      while(!c.isAfterLast())
      {

          String kitapismi= c.getString(kId);
          String kitapresmi= c.getString(Rıd);
          String kitapsure= c.getString(Sıd);
          String kitaptamsure = c.getString(kts);
          kitaplar k  = new kitaplar();
          k.setKitapismi(kitapismi);
          k.setKitapresmi(kitapresmi);
          k.setKitapsure(kitapsure);
          k.setKitaptamsure(kitaptamsure);
          liste.add(k);
          c.moveToNext();
      }



      return  liste;
  }
  public int veriguncelle(kitaplar k)
  {
      ContentValues cv  =new ContentValues();
      cv.put("kitapismi",k.getKitapismi());
      cv.put("kitapresmi",k.getKitapresmi());
      cv.put("kitapsure",k.getKitapsure());
      cv.put("kitaptamsure",k.getKitaptamsure());
      String yenideger=k.getKitapismi();
      String kitapismi="kitapismi";
      int i = db.update("kitaplar",cv,kitapismi+"= ?",new String[]{yenideger});
      return i;
  }
  public void kitapSil(kitaplar k)
  {
      String kitapismi="kitapismi";
      String gelendeger= k.getKitapismi();
      db.delete("kitaplar",kitapismi+" = ?",new String[]{gelendeger});
  }

}
