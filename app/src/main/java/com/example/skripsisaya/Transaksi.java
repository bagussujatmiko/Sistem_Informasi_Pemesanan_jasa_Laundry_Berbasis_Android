package com.example.skripsisaya;

public class Transaksi {
    private int id_transaksi;
    private String nama_pelanggan_transaksi;
    private String whatapp_pelanggan_transaksi;
    private String alamat_pelanggan_transaksi;
    private String layanan_transaksi;
    private int    harga_transaksi;
    private int    berat_pakaian;
    private int    biaya_transaksi;
    private int    bayar_transaksi;
    private int    kembalian_transaksi;
    private String tanggal_transaksi;

    public Transaksi(int id_transaksi, String nama_pelanggan_transaksi, String whatapp_pelanggan_transaksi, String alamat_pelanggan_transaksi, String layanan_transaksi, int harga_transaksi, int berat_pakaian, int biaya_transaksi, int bayar_transaksi, int kembalian_transaksi, String tanggal_transaksi) {
        this.id_transaksi = id_transaksi;
        this.nama_pelanggan_transaksi = nama_pelanggan_transaksi;
        this.whatapp_pelanggan_transaksi = whatapp_pelanggan_transaksi;
        this.alamat_pelanggan_transaksi = alamat_pelanggan_transaksi;
        this.layanan_transaksi = layanan_transaksi;
        this.harga_transaksi = harga_transaksi;
        this.berat_pakaian = berat_pakaian;
        this.biaya_transaksi = biaya_transaksi;
        this.bayar_transaksi = bayar_transaksi;
        this.kembalian_transaksi = kembalian_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
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

    public String getAlamat_pelanggan_transaksi() {
        return alamat_pelanggan_transaksi;
    }

    public void setAlamat_pelanggan_transaksi(String alamat_pelanggan_transaksi) {
        this.alamat_pelanggan_transaksi = alamat_pelanggan_transaksi;
    }

    public String getLayanan_transaksi() {
        return layanan_transaksi;
    }

    public void setLayanan_transaksi(String layanan_transaksi) {
        this.layanan_transaksi = layanan_transaksi;
    }

    public int getHarga_transaksi() {
        return harga_transaksi;
    }

    public void setHarga_transaksi(int harga_transaksi) {
        this.harga_transaksi = harga_transaksi;
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

    public int getBayar_transaksi() {
        return bayar_transaksi;
    }

    public void setBayar_transaksi(int bayar_transaksi) {
        this.bayar_transaksi = bayar_transaksi;
    }

    public int getKembalian_transaksi() {
        return kembalian_transaksi;
    }

    public void setKembalian_transaksi(int kembalian_transaksi) {
        this.kembalian_transaksi = kembalian_transaksi;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public void setTanggal_transaksi(String tanggal_transaksi) {
        this.tanggal_transaksi = tanggal_transaksi;
    }
}
