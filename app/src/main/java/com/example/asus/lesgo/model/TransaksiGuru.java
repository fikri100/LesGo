package com.example.asus.lesgo.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class TransaksiGuru {
    private String Nama, Jam, Tanggal, Alamat ,Nohp, Keterangan;

    public  TransaksiGuru(){

    }

    public TransaksiGuru(String nama, String jam, String tanggal, String alamat, String nohp, String keterangan) {
        Nama = nama;
        Jam = jam;
        Tanggal = tanggal;
        Alamat = alamat;
        Nohp = nohp;
        Keterangan = keterangan;
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

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", getNama());
        result.put("jam", getJam());
        result.put("tanggal", getTanggal());
        result.put("alamat", getAlamat());
        result.put("nohp", getNohp());
        result.put("keterangan", getKeterangan());

        return result;
    }
}
