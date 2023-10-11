package com.example.skripsisaya;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MasterActivity extends AppCompatActivity {
    CardView cardviewpelanggan , cardViewpengeluran, cardviewjasa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        cardviewpelanggan = findViewById(R.id.card_pelanggan);
        cardviewpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahpelanggan = new Intent(MasterActivity.this, MasterPelanggan.class);
                startActivity(pindahpelanggan);
            }
        });

        cardViewpengeluran = findViewById(R.id.cardpengeluaran);
        cardViewpengeluran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahpengeluaran = new Intent(MasterActivity.this,MaterPengeluaran.class);
                startActivity(pindahpengeluaran);
            }
        });

        cardviewjasa = findViewById(R.id.cardjasa);
        cardviewjasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahjasa = new Intent(MasterActivity.this, MasterJasa.class);
                startActivity(pindahjasa);
            }
        });
    }
}