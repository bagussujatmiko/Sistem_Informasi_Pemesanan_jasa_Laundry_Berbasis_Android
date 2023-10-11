package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListJasa extends AppCompatActivity {
    private RecyclerView rvlayanan;
    private MyDatabaseHelper myDB;

    private AdapterListJasa adapterListJasaLayanan;

    private ArrayList<String> arridlayanan, arrnamalayanan, arrsatuanjasa, arrhargajasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jasa);
        myDB = new MyDatabaseHelper(ListJasa.this);
        TampilLayanan();
    }

    private void SQLiteToArrayListLayanan() {
        Cursor cursor = myDB.tampilLayanan();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "tidak ada data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arridlayanan.add(cursor.getString(0));
                arrnamalayanan.add(cursor.getString(1));
                arrsatuanjasa.add(cursor.getString(2));
                arrhargajasa.add(cursor.getString(3));
            }
        }
    }

    public void TampilLayanan() {
        arridlayanan = new ArrayList<>();
        arrnamalayanan = new ArrayList<>();
         arrsatuanjasa = new ArrayList<>();
        arrhargajasa = new ArrayList<>();

        SQLiteToArrayListLayanan();

        rvlayanan = findViewById(R.id.rvlayanan);
        adapterListJasaLayanan = new AdapterListJasa(ListJasa.this, arridlayanan, arrnamalayanan, arrsatuanjasa, arrhargajasa);
        rvlayanan.setAdapter(adapterListJasaLayanan);
        rvlayanan.setLayoutManager(new LinearLayoutManager(ListJasa.this));

    }
}