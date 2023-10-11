package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
public class MasterPelanggan extends AppCompatActivity {

    private FloatingActionButton fabTambah;
    private RecyclerView rvpelanggan;
    private MyDatabaseHelper myDB;
    private AdapterPelanggan adapterPelanggan;
    private ArrayList<String> arrId, arrNamaPelanggan, arrTeleponPelanggan, arrAlamatPelanggan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_pelanggan);

        myDB = new MyDatabaseHelper(MasterPelanggan.this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        tampilPelanggan();
    }
    private void SQliteToArrayList(){
        Cursor cursor = myDB.bacaDataPelanggan();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                arrId.add(cursor.getString(0));
                arrNamaPelanggan.add(cursor.getString(1));
                arrTeleponPelanggan.add(cursor.getString(2));
                arrAlamatPelanggan.add(cursor.getString(3));
            }
        }
    }
    private void tampilPelanggan(){
        arrId = new ArrayList<>();
        arrNamaPelanggan = new ArrayList<>();
        arrTeleponPelanggan = new ArrayList<>();
        arrAlamatPelanggan = new ArrayList<>();
        SQliteToArrayList();
        rvpelanggan=findViewById(R.id.rvpelanggan);
        adapterPelanggan = new AdapterPelanggan(MasterPelanggan.this,arrId,arrNamaPelanggan,arrTeleponPelanggan,arrAlamatPelanggan);
        rvpelanggan.setAdapter(adapterPelanggan);
        rvpelanggan.setLayoutManager(new LinearLayoutManager(MasterPelanggan.this));
    }
}