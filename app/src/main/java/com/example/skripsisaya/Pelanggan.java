package com.example.skripsisaya;

public class Pelanggan {
    private int idpelanggan;
    private String namapelanggan;
    private String teleponpelanggan;
    private String alamatpelanggan;


    public Pelanggan(int idpelanggan, String namapelanggan, String teleponpelanggan, String alamatpelanggan) {
        this.idpelanggan = idpelanggan;
        this.namapelanggan = namapelanggan;
        this.teleponpelanggan = teleponpelanggan;
        this.alamatpelanggan = alamatpelanggan;
    }

    public int getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(int idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public String getNamapelanggan() {
        return namapelanggan;
    }

    public void setNamapelanggan(String namapelanggan) {
        this.namapelanggan = namapelanggan;
    }

    public String getTeleponpelanggan() {
        return teleponpelanggan;
    }

    public void setTeleponpelanggan(String teleponpelanggan) {
        this.teleponpelanggan = teleponpelanggan;
    }

    public String getAlamatpelanggan() {
        return alamatpelanggan;
    }

    public void setAlamatpelanggan(String alamatpelanggan) {
        this.alamatpelanggan = alamatpelanggan;
    }
}
