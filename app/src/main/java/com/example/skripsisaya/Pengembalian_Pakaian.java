package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Pengembalian_Pakaian extends AppCompatActivity {

    private MyDatabaseHelper myDB;
    private String idtransaksiPelanggan , namapelanggan , WaPelanggan , beratpakaian, biayapakaian, namalayanan;
    private EditText idtransaksi , etnamapelangganpengambilan , etwhatsapp,etnamalayananpengambilan, etberatpengambilan ,etbiayapengambilan ,etstatuspengambilan , tanggalpengambilan;
    private Button btnpengambilan;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengembalian_pakaian);

        idtransaksi = findViewById(R.id.id_transaksi);
        etnamapelangganpengambilan = findViewById(R.id.et_namapelanggan_pengambilan);
        etwhatsapp = findViewById(R.id.et_Whatsapp);
        etnamalayananpengambilan = findViewById(R.id.Nama_layanan_pengambilan);
        etberatpengambilan = findViewById(R.id.et_berat_pengambilan);
        etbiayapengambilan = findViewById(R.id.et_harga_pengambilan);
        etstatuspengambilan = findViewById(R.id.et_status_pengambilan);
        tanggalpengambilan = findViewById(R.id.et_Tanggal_pengambilan);
        btnpengambilan = findViewById(R.id.btn_Pengembalian);
        // get data
        Intent intent = getIntent();
        idtransaksiPelanggan = intent.getStringExtra("vIdtransaksi");
        namapelanggan = intent.getStringExtra("vNamaPelanggan");
        WaPelanggan = intent.getStringExtra("vNowaPelanggan");
        namalayanan = intent.getStringExtra("vNamalayanan");
        beratpakaian = intent.getStringExtra("vBeratpakaian");
        biayapakaian = intent.getStringExtra("vBiayapakaian");
        // tempel data
        idtransaksi.setText(idtransaksiPelanggan);
        etnamapelangganpengambilan.setText(namapelanggan);
        etwhatsapp.setText( WaPelanggan);
        etnamalayananpengambilan.setText(namalayanan);
        etberatpengambilan.setText(beratpakaian);
        etbiayapengambilan.setText(biayapakaian);
        // tanggal
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tanggalpengambilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Pengembalian_Pakaian.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        tanggalpengambilan.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        btnpengambilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdTransaksiPengembalian = idtransaksi.getText().toString();
                String Namapengambilan = etnamapelangganpengambilan.getText().toString();
                String WhatsappPelanggan = etwhatsapp.getText().toString();
                String NamaLayananTransaksi = etnamalayananpengambilan.getText().toString().trim();
                String  Berat= etberatpengambilan.getText().toString().trim();
                String biayalayananTransaksi = etbiayapengambilan.getText().toString().trim();
                String Status = etstatuspengambilan.getText().toString().trim();
                String TanggalTransaksi = tanggalpengambilan.getText().toString().trim();
                if (IdTransaksiPengembalian.trim().equals("")) {
                    idtransaksi.setError("tidak boleh kosong");
                } else if (WhatsappPelanggan.trim().equals("")) {
                    etwhatsapp.setError("tidak boleh kosong");
                } else if (Namapengambilan.trim().equals("")) {
                    etnamapelangganpengambilan.setError(" Tidak boleh kosong");
                } else if (TanggalTransaksi.trim().equals("")) {
                    tanggalpengambilan.setError("Tidak boleh kosong ");
                } else if (NamaLayananTransaksi.trim().equals("")) {
                    etnamalayananpengambilan.setError("tidak boleh kosong");
                } else if (Berat.trim().equals("")) {
                    etberatpengambilan.setError(" Tidak boleh kosong");
                } else if (biayalayananTransaksi.trim().equals("")) {
                    etbiayapengambilan.setError("Tidak boleh kosong ");
                } else if (Status.trim().equals("")) {
                    etstatuspengambilan.setError("tidak boleh kosong");
                } else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(Pengembalian_Pakaian.this);
                    long eksekusi = mydb.tambahpengambilan(Integer.parseInt(IdTransaksiPengembalian),Namapengambilan, WhatsappPelanggan,Integer.parseInt(Berat),namalayanan,Integer.parseInt(biayalayananTransaksi),Status,TanggalTransaksi);

                    if (eksekusi == -1 ) {
                        Toast.makeText(Pengembalian_Pakaian.this, "GAGAL TAMBAH DATA Pengembalian", Toast.LENGTH_SHORT).show();
                        idtransaksi.requestFocus();
                    } else {
                        Toast.makeText(Pengembalian_Pakaian.this, "BERHASIL TAMBAH DATA PENGEMBALIAN ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }


            }
        });



    }

}
