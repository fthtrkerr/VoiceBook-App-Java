package com.fatihturker.seslikitap.AdapterFile;

/**
 * Created by Fatih on 26.06.2019.
 */

public class Firebase {
    private String kitapismi;
    private String resim;
    private String kitapseslendirmeni;

    public Firebase(String kitapismi, String resim, String kitapseslendirmeni, String ozet, String seslink, String kitapyazarı, String kitapsure, String kitapkategori) {
        this.kitapismi = kitapismi;
        this.resim = resim;
        this.kitapseslendirmeni = kitapseslendirmeni;
        this.ozet = ozet;
        this.seslink = seslink;
        this.kitapyazarı = kitapyazarı;
        this.kitapsure = kitapsure;
        this.kitapkategori = kitapkategori;
    }

    private String ozet;
    private String seslink;
    private String kitapyazarı;



    private String kitapsure;

    public String getKitapsure() {
        return kitapsure;
    }

    public void setKitapsure(String kitapsure) {
        this.kitapsure = kitapsure;
    }

    public String getKitapkategori() {
        return kitapkategori;
    }

    public void setKitapkategori(String kitapkategori) {
        this.kitapkategori = kitapkategori;
    }

    private String kitapkategori;

    public String getKitapseslendirmeni() {
        return kitapseslendirmeni;
    }

    public void setKitapseslendirmeni(String kitapseslendirmeni) {
        this.kitapseslendirmeni = kitapseslendirmeni;
    }

    public String getOzet() {
        return ozet;
    }

    public void setOzet(String ozet) {
        this.ozet = ozet;
    }

    public String getSeslink() {
        return seslink;
    }

    public void setSeslink(String seslink) {
        this.seslink = seslink;
    }

    public String getKitapyazarı() {
        return kitapyazarı;
    }

    public void setKitapyazarı(String kitapyazarı) {
        this.kitapyazarı = kitapyazarı;
    }


    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getKitapismi() {
        return kitapismi;
    }

    public void setKitapismi(String kitapismi) {
        this.kitapismi = kitapismi;
    }
    public Firebase()
    {

    }
}
