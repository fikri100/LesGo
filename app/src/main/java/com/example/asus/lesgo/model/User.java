package com.example.asus.lesgo.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String Nama;
    private String Musername;
    private String Email;
    private String Password;

    public User(){

    }

    public User(String nama, String musername, String email, String password){
        Nama = nama;
        Musername = musername;
        Email = email;
        Password = password;
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", getNama() );
        result.put("musername", getMusername());
        result.put("email", getEmail());
        result.put("password", getPassword());

        return result;
    }
}
