package com.example.asus.lesgo.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ModelDataDiriGuru {
    private String Nama;
    private String Username;
    private String Musername;
    private String Email;
    private String Password;
    private String Mapel;
    private String Harga;
    private String Alamat;
    private String ImageUri;

    public ModelDataDiriGuru(){

    }

    public ModelDataDiriGuru(String imageUri, String nama, String username, String musername, String email, String password, String mapel, String harga, String alamat) {
        ImageUri = imageUri;
        Nama = nama;
        Username = username;
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put( "imageUri", getImageUri() );
        result.put("alamat", getAlamat());
        result.put("email", getEmail());
        result.put("harga", getHarga());
        result.put("mapel", getMapel());
        result.put("musername", getUsername());
        result.put("nama", getNama());
        result.put("password", getPassword());

        return result;
    }
}
