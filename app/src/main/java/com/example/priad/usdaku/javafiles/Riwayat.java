package com.example.priad.usdaku.javafiles;

/**
 * Created by priad on 2/5/2017.
 */

public class Riwayat {

    private String name;
    private int harga;

    public Riwayat(String name, int harga) {
        setName(name);
        setHarga(harga);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

}
