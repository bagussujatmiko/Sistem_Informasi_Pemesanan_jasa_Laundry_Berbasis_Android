package com.example.skripsisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CardView cardView, cardViewlaporan,cardtransaksi,cardtentang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardtransaksi = findViewById(R.id.card_transaksi);
        cardViewlaporan = findViewById(R.id.cardlaporan);
        cardView = findViewById(R.id.cardmaster);
        cardtentang = findViewById(R.id.cardtentang);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MasterActivity.class);
                startActivity(intent);
            }
        });
        cardViewlaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Menu_Laporan.class);
                startActivity(intent);
            }
        });
        cardtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MaterTransaksi.class);
                startActivity(intent);
            }
        });
        cardtentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,infoaplikasi.class);
                startActivity(intent);
            }
        });
    }
}