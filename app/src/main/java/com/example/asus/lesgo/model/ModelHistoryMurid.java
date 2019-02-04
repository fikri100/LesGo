package com.example.asus.lesgo.model;

public class ModelHistoryMurid {
    private String NamaGuru, Email, Mapel, Jam, Tanggal, Harga ;

    public  ModelHistoryMurid(){

    }

    public ModelHistoryMurid(String namaGuru, String email, String mapel, String jam, String tanggal, String harga) {
        NamaGuru = namaGuru;
        Email = email;
        Mapel = mapel;
        Jam = jam;
        Tanggal = tanggal;
        Harga = harga;
    }

    public String getNamaGuru() {
        return NamaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        NamaGuru = namaGuru;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMapel() {
        return Mapel;
    }

    public void setMapel(String mapel) {
        Mapel = mapel;
    }

    public String getJam() {
        return Jam;
    }

    public void setJam(String jam) {
        Jam = jam;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }
}
