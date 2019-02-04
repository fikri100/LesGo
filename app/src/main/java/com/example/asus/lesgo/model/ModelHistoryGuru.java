package com.example.asus.lesgo.model;

public class ModelHistoryGuru {
    private String Nama, Jam, Tanggal, Alamat ,Nohp;

    public  ModelHistoryGuru(){

    }

    public ModelHistoryGuru(String nama, String jam, String tanggal, String alamat, String nohp) {
        Nama = nama;
        Jam = jam;
        Tanggal = tanggal;
        Alamat = alamat;
        Nohp = nohp;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
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

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getNohp() {
        return Nohp;
    }

    public void setNohp(String nohp) {
        Nohp = nohp;
    }
}
