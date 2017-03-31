package com.example.priad.usdaku.provider;

/**
 * Created by priad on 2/4/2017.
 */
//FIX
public class Barang {

    private int id_barang;
    private String nama_barang;
    private String harga_barang;
    private String keterangan_barang;
    private int jumlah_barang;
    private String status_barang;
    private String url_gambarbarang;

    public Barang() {
    }

    public Barang(int id_barang, String nama_barang, String harga_barang, String keterangan_barang, int jumlah_barang, String status_barang, String url_gambarbarang) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.keterangan_barang = keterangan_barang;
        this.jumlah_barang = jumlah_barang;
        this.status_barang = status_barang;
        this.url_gambarbarang = url_gambarbarang;
    }

    public Barang(String nama_barang, String harga_barang, String keterangan_barang, int jumlah_barang, String status_barang, String url_gambarbarang) {
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.keterangan_barang = keterangan_barang;
        this.jumlah_barang = jumlah_barang;
        this.status_barang = status_barang;
        this.url_gambarbarang = url_gambarbarang;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getKeterangan_barang() {
        return keterangan_barang;
    }

    public void setKeterangan_barang(String keterangan_barang) {
        this.keterangan_barang = keterangan_barang;
    }

    public int getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(int jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public String getStatus_barang() {
        return status_barang;
    }

    public void setStatus_barang(String status_barang) {
        this.status_barang = status_barang;
    }

    public String getUrl_gambarbarang() {
        return url_gambarbarang;
    }

    public void setUrl_gambarbarang(String url_gambarbarang) {
        this.url_gambarbarang = url_gambarbarang;
    }
}