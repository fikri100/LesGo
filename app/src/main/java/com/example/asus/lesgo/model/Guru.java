package com.example.asus.lesgo.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Guru {

    private String ImageUri;
    private String Nama;
    private String Musername;
    private String Email;
    private String Password;
    private String Mapel;
    private String Harga;
    private String Alamat;

    public Guru() {

    }

    public Guru(String imageUri, String nama, String musername, String email, String password, String mapel, String harga, String alamat) {
        ImageUri = imageUri;
        Nama = nama;
        Musername = musername;
        Email = email;
        Password = password;
        Mapel = mapel;
        Harga = harga;
        Alamat = alamat;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getMusername() {
        return Musername;
    }

    public void setMusername(String musername) {
        Musername = musername;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMapel() {
        return Mapel;
    }

    public void setMapel(String mapel) {
        Mapel = mapel;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }


}
