package com.example.priad.usdaku.provider;

/**
 * Created by priad on 03/04/2017.
 */
//FIX
public class Transaksi {

    private int id_transaksi;
    private String namabarang_transaksi;
    private int hargabarang_transaksi;
    private int jumlahbarang_transaksi;

    public Transaksi() {
    }



    public Transaksi(int id_transaksi, int jumlahbarang_transaksi) {
        this.id_transaksi = id_transaksi;
        this.jumlahbarang_transaksi = jumlahbarang_transaksi;
    }

    public Transaksi(String namabarang_transaksi, int hargabarang_transaksi, int jumlahbarang_transaksi) {
        this.namabarang_transaksi = namabarang_transaksi;
        this.hargabarang_transaksi = hargabarang_transaksi;
        this.jumlahbarang_transaksi = jumlahbarang_transaksi;
    }

    public Transaksi(int id_transaksi, String namabarang_transaksi, int hargabarang_transaksi, int jumlahbarang_transaksi) {
        this.id_transaksi = id_transaksi;
        this.namabarang_transaksi = namabarang_transaksi;
        this.hargabarang_transaksi = hargabarang_transaksi;
        this.jumlahbarang_transaksi = jumlahbarang_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNamabarang_transaksi() {
        return namabarang_transaksi;
    }

    public void setNamabarang_transaksi(String namabarang_transaksi) {
        this.namabarang_transaksi = namabarang_transaksi;
    }

    public int getHargabarang_transaksi() {
        return hargabarang_transaksi;
    }

    public void setHargabarang_transaksi(int hargabarang_transaksi) {
        this.hargabarang_transaksi = hargabarang_transaksi;
    }

    public int getJumlahbarang_transaksi() {
        return jumlahbarang_transaksi;
    }

    public void setJumlahbarang_transaksi(int jumlahbarang_transaksi) {
        this.jumlahbarang_transaksi = jumlahbarang_transaksi;
    }
}
