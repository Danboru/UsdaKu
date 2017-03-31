package com.example.priad.usdaku.javafiles;

/**
 * Created by priad on 31/03/2017.
 */

public class PenjualanUser {

    private int id_penjualan;
    private String nama_barang;
    private String keterangan_barang;
    private int harga_barang;

    public PenjualanUser() {
    }

    public PenjualanUser(String nama_barang, String keterangan_barang, int harga_barang) {
        this.nama_barang = nama_barang;
        this.keterangan_barang = keterangan_barang;
        this.harga_barang = harga_barang;
    }

    public PenjualanUser(int id_penjualan, String nama_barang, String keterangan_barang, int harga_barang) {
        this.id_penjualan = id_penjualan;
        this.nama_barang = nama_barang;
        this.keterangan_barang = keterangan_barang;
        this.harga_barang = harga_barang;
    }

    public int getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(int id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getKeterangan_barang() {
        return keterangan_barang;
    }

    public void setKeterangan_barang(String keterangan_barang) {
        this.keterangan_barang = keterangan_barang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }
}
