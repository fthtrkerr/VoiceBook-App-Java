package com.fatihturker.seslikitap.database;

public class kitaplar {
String kitapismi;
String kitapresmi;
String kitapsure;

    public String getKitaptamsure() {
        return kitaptamsure;
    }

    public void setKitaptamsure(String kitaptamsure) {
        this.kitaptamsure = kitaptamsure;
    }

    String kitaptamsure;

    public kitaplar(String kitapismi, String kitapresmi, String kitapsure) {
        this.kitapismi = kitapismi;
        this.kitapresmi = kitapresmi;
        this.kitapsure = kitapsure;
    }

    public String getKitapismi() {
        return kitapismi;
    }

    public void setKitapismi(String kitapismi) {
        this.kitapismi = kitapismi;
    }

    public String getKitapresmi() {
        return kitapresmi;
    }

    public void setKitapresmi(String kitapresmi) {
        this.kitapresmi = kitapresmi;
    }

    public String getKitapsure() {
        return kitapsure;
    }

    public void setKitapsure(String kitapsure) {
        this.kitapsure = kitapsure;
    }
    public kitaplar()
    {

    }
}
