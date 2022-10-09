package com.fatihturker.seslikitap.ClassFolder;

/**
 * Created by Fatih on 10.10.2019.
 */

public class yazarGetandSet {
    private String yazarbilgi;
    private String dogumolum;
    private String yazarisim;
    private String yazarresim;
    public yazarGetandSet(String yazarbilgi,String dogumolum,String yazarisim,String yazarresim)
    {
        this.dogumolum=dogumolum;
        this.yazarbilgi=yazarbilgi;
        this.yazarisim=yazarisim;
        this.yazarresim=yazarresim;
    }

    public String getYazarbilgi() {
        return yazarbilgi;
    }

    public void setYazarbilgi(String yazarbilgi) {
        this.yazarbilgi = yazarbilgi;
    }

    public String getDogumolum() {
        return dogumolum;
    }

    public void setDogumolum(String dogumolum) {
        this.dogumolum = dogumolum;
    }

    public String getYazarisim() {
        return yazarisim;
    }

    public void setYazarisim(String yazarisim) {
        this.yazarisim = yazarisim;
    }

    public String getYazarresim() {
        return yazarresim;
    }

    public void setYazarresim(String yazarresim) {
        this.yazarresim = yazarresim;
    }
    public yazarGetandSet()
    {

    }
}
