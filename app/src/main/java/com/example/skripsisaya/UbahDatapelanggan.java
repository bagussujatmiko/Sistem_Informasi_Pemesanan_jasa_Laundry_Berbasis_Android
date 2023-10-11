package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahDatapelanggan extends AppCompatActivity {

    private EditText etnamapelanggan , ettelponpelanggan, etalamatlaudry;
    private Button btnUbah;
    private String id , nama , nomor , alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_datapelanggan);

        etnamapelanggan = findViewById(R.id.et_namapelanggan);
        ettelponpelanggan = findViewById(R.id.et_nomorteleponpelanggan);
        etalamatlaudry = findViewById(R.id.et_alamatpelanggan);
        btnUbah = findViewById(R.id.btn_ubahpelanggan);

        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNama");
        nomor = intent.getStringExtra("vTelepon");
        alamat = intent.getStringExtra("vAlamat");


        etnamapelanggan.setText(nama);
        ettelponpelanggan.setText(nomor);
        etalamatlaudry.setText(alamat);


        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempnama = etnamapelanggan.getText().toString();
                String temptelepon =ettelponpelanggan.getText().toString();
                String tempalamat = etalamatlaudry.getText().toString();

                if (tempnama.trim().equals("")){
                    etnamapelanggan.setError("tidak boleh kosong");
                }else if(temptelepon.trim().equals("")){
                    ettelponpelanggan.setError("tidak boleh kosong");
                }else if (tempalamat.trim().equals("")){
                    etalamatlaudry.setError(" Tidak boleh kosong");
                }else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(UbahDatapelanggan.this);
                    long eksekusi = mydb.ubahPelanggan(id , tempnama,temptelepon,tempalamat );

                    if(eksekusi == -1 ){
                        Toast.makeText(UbahDatapelanggan.this, "GAGAL UBAH DATA", Toast.LENGTH_SHORT).show();
                        etnamapelanggan.requestFocus();
                    }else {
                        Toast.makeText(UbahDatapelanggan.this, "Berhasil UBAH data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

        });


    }
}