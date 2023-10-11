
package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class TambahDataPelanggan extends AppCompatActivity {
    private EditText NamaPelanggan, TeleponPelanggan, AlamatPelanggan ;
    private Button btntambahpelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_pelanggan);
        NamaPelanggan = findViewById(R.id.et_namapelanggan);
        TeleponPelanggan = findViewById(R.id.et_nomorteleponpelanggan);
        AlamatPelanggan = findViewById(R.id.et_alamatpelanggan);
        btntambahpelanggan = findViewById(R.id.btn_tambahpelanggan);
        btntambahpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = NamaPelanggan.getText().toString();
                String telepon =TeleponPelanggan.getText().toString();
                String alamat = AlamatPelanggan.getText().toString();

                if (nama.trim().equals("")){
                    NamaPelanggan.setError("tidak boleh kosong");
                }else if(telepon.trim().equals("")){
                    TeleponPelanggan.setError("tidak boleh kosong");
                }else if (alamat.trim().equals("")){
                    AlamatPelanggan.setError(" Tidak boleh kosong");

                }else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(TambahDataPelanggan.this);
                    long eksekusi = mydb.tambahpelanggan(nama, telepon, alamat );

                    if(eksekusi == -1 ){
                        Toast.makeText(TambahDataPelanggan.this, "GAGAL MENAMBAH DATA", Toast.LENGTH_SHORT).show();
                        NamaPelanggan.requestFocus();
                    }else {
                        Toast.makeText(TambahDataPelanggan.this, "Berhasil nambah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}