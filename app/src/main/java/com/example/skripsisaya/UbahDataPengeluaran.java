package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahDataPengeluaran extends AppCompatActivity {

    private EditText etnamapengeluaran , etjumlahpengeluaran, ethargapengeluaran , ettanggal;
    private Button btnUbahpengeluaran;

    private String id , nama , jumlah , harga , tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data_pengeluaran);


        etnamapengeluaran = findViewById(R.id.et_namabarang);
        etjumlahpengeluaran = findViewById(R.id.et_jumlah);
        ethargapengeluaran = findViewById(R.id.et_hargapembelian);
        ettanggal =findViewById(R.id.et_tanggalpengeluaran);

        btnUbahpengeluaran =findViewById(R.id.btn_ubahpengeluaran);


        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNamapengeluaran");
        jumlah = intent.getStringExtra("vjumlahpengeluaran");
        harga = intent.getStringExtra("vHargapengaluran");
        tanggal = intent.getStringExtra("vtanggalpengeluaran");


        etnamapengeluaran.setText(nama);
        etjumlahpengeluaran.setText(jumlah);
        ethargapengeluaran.setText(harga);
        ettanggal.setText(tanggal);

        btnUbahpengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempnama = etnamapengeluaran.getText().toString();
                String temptelepon =etjumlahpengeluaran.getText().toString();
                String tempalamat = ethargapengeluaran.getText().toString();
                String temptanggal = ettanggal.getText().toString();

                if (tempnama.trim().equals("")){
                    etnamapengeluaran.setError("tidak boleh kosong");
                }else if(temptelepon.trim().equals("")){
                    etjumlahpengeluaran.setError("tidak boleh kosong");
                }else if (tempalamat.trim().equals("")){
                    ethargapengeluaran.setError(" Tidak boleh kosong");
                }else if (temptanggal.trim().equals("")){
                    ettanggal.setError(" Tidak boleh kosong");
                } else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(UbahDataPengeluaran.this);
                    long eksekusi = mydb.ubahpengeluaran(id , tempnama,temptelepon,tempalamat, temptanggal );

                    if(eksekusi == -1 ){
                        Toast.makeText(UbahDataPengeluaran.this, "GAGAL UBAH DATA", Toast.LENGTH_SHORT).show();
                        etnamapengeluaran.requestFocus();
                    }else {
                        Toast.makeText(UbahDataPengeluaran.this, "Berhasil UBAH data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });


    }
}