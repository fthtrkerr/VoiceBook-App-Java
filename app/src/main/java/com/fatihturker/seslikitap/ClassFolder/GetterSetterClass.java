package com.fatihturker.seslikitap.ClassFolder;

/**
 * Created by Fatih on 26.06.2019.
 */

public class GetterSetterClass {
    private String kitapismi;
    private String resim;
    private String kitapseslendirmeni;
    private String ozet;
    private String seslink;
    private String kitapyazarı;

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

    public GetterSetterClass(String kitapismi, String resim, String kitapseslendirmeni, String kitapyazarı,String seslink,String ozet) {
        this.kitapismi = kitapismi;
        this.resim = resim;
        this.kitapyazarı=kitapyazarı;
        this.ozet=ozet;
        this.seslink=seslink;
        this.kitapseslendirmeni=kitapseslendirmeni;
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
    public GetterSetterClass()
    {

    }
}
