package com.fatihturker.seslikitap.ClassFolder;

import android.app.Application;
import android.media.MediaPlayer;

import java.util.ArrayList;

/**
 * Created by Fatih on 8.10.2019.
 */

public class GlobalClass extends Application{
    private ArrayList<String> yazarlar;
    private ArrayList<String> yazarisim;
    private ArrayList<String> ydogumolum;
    private ArrayList<String> yazarbilgi;
    private ArrayList<String> yazarresim;
    private ArrayList<String> kitapozet;
    private ArrayList<String> kitapsure;

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    private String resim;

    public int getKitapsayac() {
        return kitapsayac;
    }

    public void setKitapsayac(int kitapsayac) {
        this.kitapsayac = kitapsayac;
    }

    private int kitapsayac;

    public ArrayList<String> getKitapsure() {
        return kitapsure;
    }

    public void setKitapsure(ArrayList<String> kitapsure) {
        this.kitapsure = kitapsure;
    }

    public ArrayList<String> getKitapkategori() {
        return kitapkategori;
    }

    public void setKitapkategori(ArrayList<String> kitapkategori) {
        this.kitapkategori = kitapkategori;
    }

    private ArrayList<String> kitapkategori;
    private MediaPlayer mediaPlayer;

    public String getPlayBook() {
        return playBook;
    }

    public void setPlayBook(String playBook) {
        this.playBook = playBook;
    }

    private String playBook;
    private ArrayList<String> kitapseslendirmen;
    private ArrayList<String> kitapisim;
    private ArrayList<String> kitapseslink;

    public ArrayList<String> getSeslendirmenAdi() {
        return seslendirmenAdi;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void setSeslendirmenAdi(ArrayList<String> seslendirmenAdi) {
        this.seslendirmenAdi = seslendirmenAdi;
    }

    private ArrayList<String> seslendirmenAdi;

    public ArrayList<String> getKitapozet() {
        return kitapozet;
    }

    public void setKitapozet(ArrayList<String> kitapozet) {
        this.kitapozet = kitapozet;
    }

    public ArrayList<String> getKitapseslendirmen() {
        return kitapseslendirmen;
    }

    public void setKitapseslendirmen(ArrayList<String> kitapseslendirmen) {
        this.kitapseslendirmen = kitapseslendirmen;
    }

    public ArrayList<String> getKitapisim() {
        return kitapisim;
    }

    public void setKitapisim(ArrayList<String> kitapisim) {
        this.kitapisim = kitapisim;
    }

    public ArrayList<String> getKitapseslink() {
        return kitapseslink;
    }

    public void setKitapseslink(ArrayList<String> kitapseslink) {
        this.kitapseslink = kitapseslink;
    }

    public ArrayList<String> getKitapResim() {
        return kitapResim;
    }

    public void setKitapResim(ArrayList<String> kitapResim) {
        this.kitapResim = kitapResim;
    }

    private ArrayList<String> kitapResim;

    public ArrayList<String> getYazarisim() {
        return yazarisim;
    }

    public void setYazarisim(ArrayList<String> yazarisim) {
        this.yazarisim = yazarisim;
    }

    public ArrayList<String> getYdogumolum() {
        return ydogumolum;
    }

    public void setYdogumolum(ArrayList<String> ydogumolum) {
        this.ydogumolum = ydogumolum;
    }

    public ArrayList<String> getYazarbilgi() {
        return yazarbilgi;
    }

    public void setYazarbilgi(ArrayList<String> yazarbilgi) {
        this.yazarbilgi = yazarbilgi;
    }

    public ArrayList<String> getYazarresim() {
        return yazarresim;
    }

    public void setYazarresim(ArrayList<String> yazarresim) {
        this.yazarresim = yazarresim;
    }

    public ArrayList<String> getYazarlar() {
        return yazarlar;
    }

    public void setYazarlar(ArrayList<String> yazarlar) {
        this.yazarlar = yazarlar;
    }
}
