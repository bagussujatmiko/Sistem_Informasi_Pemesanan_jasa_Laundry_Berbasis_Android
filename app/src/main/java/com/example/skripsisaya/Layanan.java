package com.example.skripsisaya;

public class Layanan {
    private int idlayanan;
    private String namalayanan;
    private String satuanjasa;
    private int hargajasa;

    public int getIdlayanan() {
        return idlayanan;
    }

    public void setIdlayanan(int idlayanan) {
        this.idlayanan = idlayanan;
    }

    public String getNamalayanan() {
        return namalayanan;
    }

    public void setNamalayanan(String namalayanan) {
        this.namalayanan = namalayanan;
    }

    public String getSatuanjasa() {
        return satuanjasa;
    }

    public void setSatuanjasa(String satuanjasa) {
        this.satuanjasa = satuanjasa;
    }

    public int getHargajasa() {
        return hargajasa;
    }

    public void setHargajasa(int hargajasa) {
        this.hargajasa = hargajasa;
    }

    public Layanan(int idlayanan, String namalayanan, String satuanjasa, int hargajasa) {
        this.idlayanan = idlayanan;
        this.namalayanan = namalayanan;
        this.satuanjasa = satuanjasa;
        this.hargajasa = hargajasa;


    }
}
