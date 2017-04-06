package com.example.priad.usdaku.provider;

/**
 * Created by priad on 2/5/2017.
 */
//FIX
public class Riwayat {

    private String nama_barang_riwayat;
    private int harga_barang_riwayat;

    public Riwayat() {
    }

    public Riwayat(String nama_barang_riwayat, int harga_barang_riwayat) {
        this.nama_barang_riwayat = nama_barang_riwayat;
        this.harga_barang_riwayat = harga_barang_riwayat;
    }

    public String getNama_barang_riwayat() {
        return nama_barang_riwayat;
    }

    public void setNama_barang_riwayat(String nama_barang_riwayat) {
        this.nama_barang_riwayat = nama_barang_riwayat;
    }

    public int getHarga_barang_riwayat() {
        return harga_barang_riwayat;
    }

    public void setHarga_barang_riwayat(int harga_barang_riwayat) {
        this.harga_barang_riwayat = harga_barang_riwayat;
    }
}
