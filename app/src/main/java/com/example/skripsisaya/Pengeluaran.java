package com.example.skripsisaya;

public class Pengeluaran {
    private int idpengeluaran;
    private String namabarang;
    private String jumlah;
    private int harga;
    private String tanggal;

    public Pengeluaran(int idpengeluaran, String namabarang, String jumlah, int harga,String tanggal) {
        this.idpengeluaran = idpengeluaran;
        this.namabarang = namabarang;
        this.jumlah = jumlah;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public int getIdpengeluaran() {
        return idpengeluaran;
    }

    public void setIdpengeluaran(int idpengeluaran) {
        this.idpengeluaran = idpengeluaran;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
