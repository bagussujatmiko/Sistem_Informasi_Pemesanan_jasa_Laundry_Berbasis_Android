package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class TambahDataPengeluaran extends AppCompatActivity {
private EditText Namabarang , jumlahbarang, hargabarang , tanggal_pesan ;
private Button masukdata;
DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_pengeluaran2);

        Namabarang = findViewById(R.id.et_namabarang);
        jumlahbarang = findViewById(R.id.et_jumlah);
        hargabarang = findViewById(R.id.et_total);
        tanggal_pesan = findViewById(R.id.et_tanggalpengeluaran);

        masukdata = findViewById(R.id.btn_inputpengeluaran);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tanggal_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TambahDataPengeluaran.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        tanggal_pesan.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });

        masukdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_barang = Namabarang.getText().toString();
                String jumlah_barang =jumlahbarang.getText().toString();
                String harga_barang = hargabarang.getText().toString();
                String tanggalpesan = tanggal_pesan.getText().toString();


                if (nama_barang.trim().equals("")){
                    Namabarang.setError("tidak boleh kosong");
                }else if(jumlah_barang.trim().equals("")){
                    jumlahbarang.setError("tidak boleh kosong");
                }else if (harga_barang.trim().equals("")){
                    hargabarang.setError(" Tidak boleh kosong");
                }else if (tanggalpesan.trim().equals("")){
                    tanggal_pesan.setError(" Tidak boleh kosong");
                }else {
                    MyDatabaseHelper mydb = new MyDatabaseHelper(TambahDataPengeluaran.this);
                    long eksekusi = mydb.tambahpengeluaran(nama_barang , Integer.parseInt(jumlah_barang), Integer.parseInt(harga_barang), tanggalpesan);

                    if(eksekusi == -1 ){
                        Toast.makeText(TambahDataPengeluaran.this, "GAGAL MENAMBAH DATA", Toast.LENGTH_SHORT).show();
                        Namabarang.requestFocus();
                    }else {
                        Toast.makeText(TambahDataPengeluaran.this, "Berhasil nambah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

    }

}


