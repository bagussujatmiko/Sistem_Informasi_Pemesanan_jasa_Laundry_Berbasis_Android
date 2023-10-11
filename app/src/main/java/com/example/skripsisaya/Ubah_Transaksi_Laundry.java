package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ubah_Transaksi_Laundry extends AppCompatActivity {

    private EditText etnamapelanggan , etnowhatsapp ,etalamat,ettanggal, etnmlayanan ,ethargalayanan ,etberat , etbiaya , etbayar ,etkembalian;
    private Button btnubahtransaksi;
    private MyDatabaseHelper myDB;
    private String id , namapelanggan , wapelanggan , layananlaundry, harga, berat,biaya,bayar,alamatpelanggan,kembalian, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_transaksi_laundry);
        myDB = new MyDatabaseHelper(Ubah_Transaksi_Laundry.this);
        etnamapelanggan = findViewById(R.id.et_nama_Pelanggan);
        etnowhatsapp = findViewById(R.id.et_nowhatsapp);
        etalamat = findViewById(R.id.et_nmAlamatPelanggan);
        ettanggal = findViewById(R.id.et_tanggalpesan);
        etnmlayanan = findViewById(R.id.et_nmlayanan);
        ethargalayanan = findViewById(R.id.et_harga_layanan);
        etberat = findViewById(R.id.et_berat);
        etbiaya = findViewById(R.id.et_biaya);
        etbayar = findViewById(R.id.et_bayar);
        etkembalian = findViewById(R.id.et_kembalian);
        btnubahtransaksi = findViewById(R.id.btn_ubahtransaksi);
        // get data
        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        namapelanggan = intent.getStringExtra("vNama");
        wapelanggan = intent.getStringExtra("vTelepon");
        alamatpelanggan = intent.getStringExtra("vAlamat");
        layananlaundry = intent.getStringExtra("vNama_layanan");
        harga = intent.getStringExtra("vHarga");
        berat = intent.getStringExtra("vBerat");
        biaya = intent.getStringExtra("vBiaya");
        bayar = intent.getStringExtra("vBayar");
        kembalian = intent.getStringExtra("vKembalian");
        tanggal = intent.getStringExtra("vTanggal");


        etnamapelanggan.setText(namapelanggan);
        etnowhatsapp.setText(wapelanggan);
        etnmlayanan.setText(layananlaundry);
        ethargalayanan.setText(harga);
        etberat.setText(berat);
        etbiaya.setText(biaya);
        etbayar.setText(bayar);
        etalamat.setText(alamatpelanggan);
        etkembalian.setText(kembalian);
        ettanggal.setText(tanggal);


        btnubahtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NamaPelangganTransaksi = etnamapelanggan.getText().toString();
                String WhatsappPelanggan = etnowhatsapp.getText().toString();
                String AlamatPelangganTransaksi = etalamat.getText().toString();
                String TanggalTransaksi = ettanggal.getText().toString().trim();
                String NamaLayananTransaksi = etnmlayanan.getText().toString().trim();
                String HargalayananTransaksi = ethargalayanan.getText().toString().trim();
                String BeratPakaian = etberat.getText().toString().trim();
                String BiayaTransaksi = etbiaya.getText().toString().trim();
                String BayarTransaksi = etbayar.getText().toString().trim();
                String KembalianTransaksi = etkembalian.getText().toString().trim();

                if (NamaPelangganTransaksi.trim().equals("")) {
                    etnamapelanggan.setError("tidak boleh kosong");
                } else if (WhatsappPelanggan.trim().equals("")) {
                    etnowhatsapp.setError("tidak boleh kosong");
                } else if (AlamatPelangganTransaksi.trim().equals("")) {
                    etalamat.setError(" Tidak boleh kosong");
                } else if (TanggalTransaksi.trim().equals("")) {
                    ettanggal.setError("Tidak boleh kosong ");
                } else if (NamaLayananTransaksi.trim().equals("")) {
                    etnmlayanan.setError("tidak boleh kosong");
                } else if (HargalayananTransaksi.trim().equals("")) {
                    ethargalayanan.setError(" Tidak boleh kosong");
                } else if (BeratPakaian.trim().equals("")) {
                    etberat.setError("Tidak boleh kosong ");
                } else if (BiayaTransaksi.trim().equals("")) {
                    etbiaya.setError("tidak boleh kosong");
                } else if (BayarTransaksi.trim().equals("")) {
                    etbayar.setError(" Tidak boleh kosong");
                } else if (KembalianTransaksi.trim().equals("")) {
                    etkembalian.setError("Tidak boleh kosong ");
                } else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(Ubah_Transaksi_Laundry.this);
                    long eksekusi = mydb.ubahtransaksi(id,NamaPelangganTransaksi, Integer.parseInt(WhatsappPelanggan), AlamatPelangganTransaksi, TanggalTransaksi, NamaLayananTransaksi, Integer.parseInt(HargalayananTransaksi),
                            Integer.parseInt(BeratPakaian), Integer.parseInt(BiayaTransaksi), Integer.parseInt(BayarTransaksi), Integer.parseInt(KembalianTransaksi));
                    if (eksekusi == -1 ) {
                        Toast.makeText(Ubah_Transaksi_Laundry.this, "GAGAL UBAH DATA TRANSAKSI", Toast.LENGTH_SHORT).show();
                        etnamapelanggan.requestFocus();
                    } else {
                        Toast.makeText(Ubah_Transaksi_Laundry.this, "BERHASIL UBAH DATA TRANSAKSI", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }
}