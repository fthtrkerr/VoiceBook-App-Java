package com.fatihturker.seslikitap.ClassFolder;

/**
 * Created by Fatih on 10.10.2019.
 */

public class ListClass2 {
    private String kitapAdi;
    private String kitapKapak;

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getKitapKapak() {
        return kitapKapak;
    }

    public void setKitapKapak(String kitapKapak) {
        this.kitapKapak = kitapKapak;
    }

    public void ListClass2 (String kitapAdi, String kitapKapak)
    {
        this.kitapAdi= kitapAdi;
        this.kitapKapak= kitapKapak;

    }


    public ListClass2()
    {

    }
}
