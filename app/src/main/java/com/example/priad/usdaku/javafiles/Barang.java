package com.example.priad.usdaku.javafiles;

/**
 * Created by priad on 2/4/2017.
 */
public class Barang {

    private String name;
    private String brand;
    private int harga;

    public Barang(String name, String brand, int harga) {
        setName(name);
        setBrand(brand);
        setHarga(harga);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}