package com.example.priad.usdaku.provider;

/**
 * Created by priad on 06/04/2017.
 */
//FIX
public class Laporan {

    private int id_laporan;
    private String nama_pelapor;
    private String pesan_terlampir;
    private String tanggal_pelaporan;

    public Laporan() {
    }

    public Laporan(String nama_pelapor, String pesan_terlampir, String tanggal_pelaporan) {
        this.nama_pelapor = nama_pelapor;
        this.pesan_terlampir = pesan_terlampir;
        this.tanggal_pelaporan = tanggal_pelaporan;
    }

    public Laporan(int id_laporan, String nama_pelapor, String pesan_terlampir, String tanggal_pelaporan) {
        this.id_laporan = id_laporan;
        this.nama_pelapor = nama_pelapor;
        this.pesan_terlampir = pesan_terlampir;
        this.tanggal_pelaporan = tanggal_pelaporan;
    }

    public int getId_laporan() {
        return id_laporan;
    }

    public void setId_laporan(int id_laporan) {
        this.id_laporan = id_laporan;
    }

    public String getNama_pelapor() {
        return nama_pelapor;
    }

    public void setNama_pelapor(String nama_pelapor) {
        this.nama_pelapor = nama_pelapor;
    }

    public String getPesan_terlampir() {
        return pesan_terlampir;
    }

    public void setPesan_terlampir(String pesan_terlampir) {
        this.pesan_terlampir = pesan_terlampir;
    }

    public String getTanggal_pelaporan() {
        return tanggal_pelaporan;
    }

    public void setTanggal_pelaporan(String tanggal_pelaporan) {
        this.tanggal_pelaporan = tanggal_pelaporan;
    }
}
