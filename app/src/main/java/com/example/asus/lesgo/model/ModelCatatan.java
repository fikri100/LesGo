package com.example.asus.lesgo.model;

public class ModelCatatan {
    private String NamaGuru , Nama, Catatan  ;

    public ModelCatatan(){

    }

    public ModelCatatan(String namaGuru, String nama, String catatan) {
        NamaGuru = namaGuru;
        Nama = nama;
        Catatan = catatan;
    }

    public String getNamaGuru() {
        return NamaGuru;
    }

    public void setNamaGuru(String namaGuru) {
        NamaGuru = namaGuru;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getCatatan() {
        return Catatan;
    }

    public void setCatatan(String catatan) {
        Catatan = catatan;
    }

}
