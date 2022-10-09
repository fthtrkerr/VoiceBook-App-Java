package com.fatihturker.seslikitap.ClassFolder;

/**
 * Created by Fatih on 11.10.2019.
 */

public class YazarKitapGetandSet {
    private String kitapResim;
    public YazarKitapGetandSet(String kitapResim)
    {
        this.kitapResim=kitapResim;

    }

    public String getKitapResim() {
        return kitapResim;
    }

    public void setKitapResim(String kitapResim) {
        this.kitapResim = kitapResim;
    }
    public YazarKitapGetandSet(){};
}
