package com.fatihturker.seslikitap.ClassFolder;

/**
 * Created by Fatih on 10.10.2019.
 */

public class ListClass {
    private String yazarAdi;
    private String yazarFoto;

    public ListClass(String yazarAdi,String yazarFoto)
    {
        this.yazarAdi=yazarAdi;
        this.yazarFoto=yazarFoto;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getYazarFoto() {
        return yazarFoto;
    }

    public void setYazarFoto(String yazarFoto) {
        this.yazarFoto = yazarFoto;
    }
    public ListClass()
    {

    }
}
