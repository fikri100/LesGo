package com.example.asus.lesgo.model;

public class ModelKomentar {
    private String NamaGuru , Nama, Komentar ;

    public ModelKomentar(){

    }

    public ModelKomentar(String namaGuru,String nama, String komentar ) {
        NamaGuru = namaGuru;
        Nama = nama;
        Komentar = komentar;
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

    public String getKomentar() {
        return Komentar;
    }

    public void setKomentar(String komentar) {
        Komentar = komentar;
    }
}
