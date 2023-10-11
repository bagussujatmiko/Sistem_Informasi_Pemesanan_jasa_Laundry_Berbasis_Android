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
public class MaterPengeluaran extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvpengeluaran;
    private MyDatabaseHelper myDB;
    private AdapterPengeluran adapterPengeluran;
    private ArrayList<String> arrIdPengeluaran, arrNamaPengeluaran, arrjumlahpengeluaran, arrhargapengeluaran , arrtanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mater_pengeluaran);
            myDB =new MyDatabaseHelper(MaterPengeluaran.this);
        fabTambah = findViewById(R.id.fab_tambahpengeluaran);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MaterPengeluaran.this , TambahDataPengeluaran.class));
            }
        });
    }
    protected void onResume() {
        super.onResume();
        tampilPengeluaran();
    }
    private void SQliteToArrayList(){
        Cursor cursor = myDB.bacaDataPengeluaran();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();

        }else {
            while (cursor.moveToNext()){
                arrIdPengeluaran.add(cursor.getString(0));
                arrNamaPengeluaran.add(cursor.getString(1));
                arrjumlahpengeluaran.add(cursor.getString(2));
                arrhargapengeluaran.add(cursor.getString(3));
                arrtanggal.add(cursor.getString(4));
            }
        }
    }

    private void tampilPengeluaran() {
        arrIdPengeluaran = new ArrayList<>();
        arrNamaPengeluaran = new ArrayList<>();
        arrjumlahpengeluaran = new ArrayList<>();
        arrhargapengeluaran= new ArrayList<>();
        arrtanggal= new ArrayList<>();
        SQliteToArrayList();
        rvpengeluaran=findViewById(R.id.rvpengeluran);
        adapterPengeluran = new AdapterPengeluran(MaterPengeluaran.this ,arrIdPengeluaran,arrNamaPengeluaran,arrjumlahpengeluaran,arrhargapengeluaran,arrtanggal);
        rvpengeluaran.setAdapter(adapterPengeluran);
        rvpengeluaran.setLayoutManager(new LinearLayoutManager(MaterPengeluaran.this));
    }
}