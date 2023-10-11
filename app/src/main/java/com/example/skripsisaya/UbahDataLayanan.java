package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahDataLayanan extends AppCompatActivity {
    private EditText etnamalayanan , etsatuanlayanan, ethargalayanan;
    private Button btnUbahlayanan;
    private String id , nama , satuan , harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data_layanan);

        etnamalayanan = findViewById (R.id.et_namalayanan);
        etsatuanlayanan = findViewById (R.id.et_satuanlayanan);
        ethargalayanan = findViewById (R.id.et_hargalayanan);
        btnUbahlayanan = findViewById (R.id.btn_Ubahlayanan);

        // ambil data ubah
        Intent intent = getIntent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNamalayanan");
        satuan = intent.getStringExtra("vSatuanharga");
        harga = intent.getStringExtra("vHargajasa");


        // masukin data ke dalam edit text
        etnamalayanan.setText(nama);
        etsatuanlayanan.setText(satuan);
        ethargalayanan.setText(harga);

        btnUbahlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempnamalayanan = etnamalayanan.getText().toString();
                String tempsatuanlayan =etsatuanlayanan.getText().toString();
                String tempharga = ethargalayanan.getText().toString();

                if (tempnamalayanan.trim().equals("")){
                    etnamalayanan.setError("tidak boleh kosong");
                }else if(tempsatuanlayan.trim().equals("")){
                    etsatuanlayanan.setError("tidak boleh kosong");
                }else if (tempharga.trim().equals("")){
                    ethargalayanan.setError(" Tidak boleh kosong");
                }else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(UbahDataLayanan.this);
                    long eksekusi = mydb.ubahLayanan(id , tempnamalayanan,tempsatuanlayan,tempharga );

                    if(eksekusi == -1 ){
                        Toast.makeText(UbahDataLayanan.this, "GAGAL UBAH DATA", Toast.LENGTH_SHORT).show();
                        etnamalayanan.requestFocus();
                    }else {
                        Toast.makeText(UbahDataLayanan.this, "Berhasil UBAH data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

        });

    }
}