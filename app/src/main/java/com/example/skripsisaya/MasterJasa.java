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
public class MasterJasa extends AppCompatActivity {

    private FloatingActionButton fabTambahlayanan;
    private RecyclerView rvlayanan;
    private MyDatabaseHelper myDB;
    private AdapterLayanan adapterLayanan;
    private ArrayList<String> arridlayanan, arrnamalayanan, arrsatuanjasa, arrhargajasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_jasa);
        myDB = new MyDatabaseHelper(MasterJasa.this);
        fabTambahlayanan = findViewById(R.id.fab_tambahlayanan);
        fabTambahlayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasterJasa.this, TambahDataLayanan.class));
            }
        });
    }

    protected void onResume() {
        super.onResume();
        TampilLayanan();
    }

    private void SQLiteToArrayListLayanan(){
        Cursor cursor = myDB.tampilLayanan();
        if(cursor.getCount()== 0){
            Toast.makeText(this, "tidak ada data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                arridlayanan.add(cursor.getString(0));
                arrnamalayanan.add(cursor.getString(1));
                arrsatuanjasa.add(cursor.getString(2));
                arrhargajasa.add(cursor.getString(3));
            }
        }
    }

    private void TampilLayanan(){
        arridlayanan = new ArrayList<>();
        arrnamalayanan = new ArrayList<>();
        arrsatuanjasa = new ArrayList<>();
        arrhargajasa = new ArrayList<>();
        SQLiteToArrayListLayanan();
        rvlayanan=findViewById(R.id.rvlayanan);
        adapterLayanan = new AdapterLayanan(MasterJasa.this,arridlayanan,arrnamalayanan,arrsatuanjasa,arrhargajasa);
        rvlayanan.setAdapter(adapterLayanan);
        rvlayanan.setLayoutManager(new LinearLayoutManager(MasterJasa.this));

    }
}