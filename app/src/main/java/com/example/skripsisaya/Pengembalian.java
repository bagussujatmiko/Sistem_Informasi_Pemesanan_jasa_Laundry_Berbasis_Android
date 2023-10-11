package com.example.skripsisaya;

public class Pengembalian {
    private int id_pengembalian;
    private int id_transaksi;
    private String nama_pelanggan_transaksi;
    private String whatapp_pelanggan_transaksi;
    private String layanan_transaksi;

    private int    berat_pakaian;
    private int    biaya_transaksi;
    private String status;
    private String tanggal_kembali;

    public Pengembalian(int id_pengembalian, int id_transaksi, String nama_pelanggan_transaksi, String whatapp_pelanggan_transaksi, String layanan_transaksi,int biaya_transaksi, int berat_pakaian, String status, String tanggal_kembali) {
        this.id_pengembalian = id_pengembalian;
        this.id_transaksi = id_transaksi;
        this.nama_pelanggan_transaksi = nama_pelanggan_transaksi;
        this.whatapp_pelanggan_transaksi = whatapp_pelanggan_transaksi;
        this.layanan_transaksi = layanan_transaksi;
        this.berat_pakaian = berat_pakaian;
        this.biaya_transaksi = biaya_transaksi;
        this.status = status;
        this.tanggal_kembali = tanggal_kembali;
    }

    public int getId_pengembalian() {
        return id_pengembalian;
    }

    public void setId_pengembalian(int id_pengembalian) {
        this.id_pengembalian = id_pengembalian;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getNama_pelanggan_transaksi() {
        return nama_pelanggan_transaksi;
    }

    public void setNama_pelanggan_transaksi(String nama_pelanggan_transaksi) {
        this.nama_pelanggan_transaksi = nama_pelanggan_transaksi;
    }

    public String getWhatapp_pelanggan_transaksi() {
        return whatapp_pelanggan_transaksi;
    }

    public void setWhatapp_pelanggan_transaksi(String whatapp_pelanggan_transaksi) {
        this.whatapp_pelanggan_transaksi = whatapp_pelanggan_transaksi;
    }

    public String getLayanan_transaksi() {
        return layanan_transaksi;
    }

    public void setLayanan_transaksi(String layanan_transaksi) {
        this.layanan_transaksi = layanan_transaksi;
    }

    public int getBerat_pakaian() {
        return berat_pakaian;
    }

    public void setBerat_pakaian(int berat_pakaian) {
        this.berat_pakaian = berat_pakaian;
    }

    public int getBiaya_transaksi() {
        return biaya_transaksi;
    }

    public void setBiaya_transaksi(int biaya_transaksi) {
        this.biaya_transaksi = biaya_transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }
}
