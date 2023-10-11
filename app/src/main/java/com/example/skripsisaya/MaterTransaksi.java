package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
public class MaterTransaksi extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvtransaksi;
    private MyDatabaseHelper myDB;
    private adaperListPengambilan adaperListPengambilan;
    private ArrayList <String> arrIdtransaksi, arrNamapelanggantransaksi , arrNoWapelanggan , arrAlamatpelanggantransaksi ,arrLayananlaundry,
            arrHarga , arrBerat , arrBiaya, arrBayar ,arrkembalian, arrtanggalTransaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mater_transaksi);

        myDB = new MyDatabaseHelper(MaterTransaksi.this);

        fabTambah = findViewById(R.id.fab_tambahtransaksi);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MaterTransaksi.this, ListJasa.class));
            }
        });
    }
    protected void onResume() {
        super.onResume();
        tampilTransaksi();
    }
    private void SQliteToArrayList(){
        Cursor cursor = myDB.tampilTransaksi();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){
                arrIdtransaksi.add(cursor.getString(0));
                arrNamapelanggantransaksi.add(cursor.getString(1));
                arrNoWapelanggan.add(cursor.getString(2));
                arrAlamatpelanggantransaksi.add(cursor.getString(3));
                arrtanggalTransaksi.add(cursor.getString(4));
                arrLayananlaundry.add(cursor.getString(5));
                arrHarga.add(cursor.getString(6));
                arrBerat.add(cursor.getString(7));
                arrBiaya.add(cursor.getString(8));
                arrBayar.add(cursor.getString(9));
                arrkembalian.add(cursor.getString(10));
            }
        }
    }
    private void tampilTransaksi(){
        arrIdtransaksi = new ArrayList<>();
        arrNamapelanggantransaksi = new ArrayList<>();
        arrNoWapelanggan = new ArrayList<>();
        arrAlamatpelanggantransaksi = new ArrayList<>();
        arrtanggalTransaksi = new ArrayList<>();
        arrLayananlaundry = new ArrayList<>();
        arrHarga = new ArrayList<>();
        arrBerat = new ArrayList<>();
        arrBiaya = new ArrayList<>();
        arrBayar = new ArrayList<>();
        arrkembalian = new ArrayList<>();
        SQliteToArrayList();
        rvtransaksi=findViewById(R.id.rvtransaksi);
        adaperListPengambilan = new adaperListPengambilan(MaterTransaksi.this,arrIdtransaksi, arrNamapelanggantransaksi , arrNoWapelanggan , arrAlamatpelanggantransaksi , arrtanggalTransaksi,
                arrLayananlaundry, arrHarga , arrBerat , arrBiaya, arrBayar ,arrkembalian);
        rvtransaksi.setAdapter(adaperListPengambilan);
        rvtransaksi.setLayoutManager(new LinearLayoutManager(MaterTransaksi.this));
        }
}