package com.example.priad.usdaku.javafiles;

/**
 * Created by priad on 31/03/2017.
 */

public class User {

    private int id_user;
    private String namadepan_user;
    private String namabelakang_user;
    private String email_user;
    private int nim;
    private String password_user;

    public User() {
    }

    public User(int id_user, String namadepan_user, String namabelakang_user, String email_user, int nim, String password_user) {
        this.id_user = id_user;
        this.namadepan_user = namadepan_user;
        this.namabelakang_user = namabelakang_user;
        this.email_user = email_user;
        this.nim = nim;
        this.password_user = password_user;
    }

    public User(String namadepan_user, String namabelakang_user, String email_user, int nim, String password_user) {
        this.namadepan_user = namadepan_user;
        this.namabelakang_user = namabelakang_user;
        this.email_user = email_user;
        this.nim = nim;
        this.password_user = password_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNamadepan_user() {
        return namadepan_user;
    }

    public void setNamadepan_user(String namadepan_user) {
        this.namadepan_user = namadepan_user;
    }

    public String getNamabelakang_user() {
        return namabelakang_user;
    }

    public void setNamabelakang_user(String namabelakang_user) {
        this.namabelakang_user = namabelakang_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
