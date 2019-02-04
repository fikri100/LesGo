package com.example.asus.lesgo.model;

public class ModelDataDiriMurid {
    private String Nama;
    private String Username;
    private String Email;
    private String Password;

    public ModelDataDiriMurid(){

    }

    public ModelDataDiriMurid(String nama, String username, String email, String password) {
        Nama = nama;
        Username = username;
        Email = email;
        Password = password;
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
}
