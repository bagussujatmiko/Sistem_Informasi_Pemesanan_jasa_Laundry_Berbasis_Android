package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
    public class TambahDataLayanan extends AppCompatActivity {
    private EditText NamaLayanan, SatuanLayanan, HargaLayanan;
    private Button btntambahlayanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_layanan);
        NamaLayanan = findViewById(R.id.et_namalayanan);
        SatuanLayanan = findViewById(R.id.et_satuanlayanan);
        HargaLayanan = findViewById(R.id.et_hargalayanan);
        btntambahlayanan = findViewById(R.id.btn_tambahlayanan);
        btntambahlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namalayanan = NamaLayanan.getText().toString();
                String satuan =SatuanLayanan.getText().toString();
                String hargalayanan = HargaLayanan.getText().toString();

                if (namalayanan.trim().equals("")){
                    NamaLayanan.setError("tidak boleh kosong");
                }else if(satuan.trim().equals("")){
                    SatuanLayanan.setError("tidak boleh kosong");
                }else if (hargalayanan.trim().equals("")){
                    HargaLayanan.setError(" Tidak boleh kosong");
                }else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(TambahDataLayanan.this);
                    long eksekusi = mydb.tambahlayanan(namalayanan, satuan, Integer.parseInt(hargalayanan));

                    if (eksekusi == -1) {
                        Toast.makeText(TambahDataLayanan.this, "GAGAL MENAMBAH DATA", Toast.LENGTH_SHORT).show();
                        NamaLayanan.requestFocus();
                    } else {
                        Toast.makeText(TambahDataLayanan.this, "Berhasil nambah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });

    }
}