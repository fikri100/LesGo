package com.example.asus.lesgo.model;

public class ModelTransaksi {
    private String TransaksiId, NamaGuru, Mapel, Email, Harga, Nama, Jam, Tanggal, Alamat ,Nohp, Keterangan;

    public ModelTransaksi() {

    }

    public ModelTransaksi(String transaksiId, String namaGuru, String mapel, String email, String harga, String nama, String jam, String tanggal, String alamat, String nohp, String keterangan) {
        TransaksiId = transaksiId;
        NamaGuru = namaGuru;
        Mapel = mapel;
        Email = email;
        Harga = harga;
        Nama = nama;
        Jam = jam;
        Tanggal = tanggal;
        Alamat = alamat;
        Nohp = nohp;
        Keterangan = keterangan;
    }

    public String getTransaksiId() {
        return TransaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        TransaksiId = transaksiId;
    }

    public String getNamaGuru() {
        return NamaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        NamaGuru = namaGuru;
    }

    public String getMapel() {
        return Mapel;
    }

    public void setMapel(String mapel) {
        Mapel = mapel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
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
}
