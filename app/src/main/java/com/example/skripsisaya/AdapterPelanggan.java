package com.example.skripsisaya;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterPelanggan extends RecyclerView.Adapter<AdapterPelanggan.ViewHolderPelanggan> {
    private Context context;
    private ArrayList arrIdpelanggan, arrNamapelanggan , arrTeleponpelanggan , arrAlamatpelanggan;

    public AdapterPelanggan(Context context, ArrayList arrIdpelanggan, ArrayList arrNamapelanggan, ArrayList arrTeleponpelanggan, ArrayList arrAlamatpelanggan) {
        this.context = context;
        this.arrIdpelanggan = arrIdpelanggan;
        this.arrNamapelanggan = arrNamapelanggan;
        this.arrTeleponpelanggan = arrTeleponpelanggan;
        this.arrAlamatpelanggan = arrAlamatpelanggan;
    }

    @NonNull
    @Override
    public ViewHolderPelanggan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterPelanggan = LayoutInflater.from(context);
       View tampil = inflaterPelanggan.inflate(R.layout.list_item_pelanggan, parent ,false);
        return new ViewHolderPelanggan(tampil);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPelanggan holder, int position) {
        holder.tvIdpelanggan.setText(arrIdpelanggan.get(position).toString());
        holder.tvNamaPelanggan.setText(arrNamapelanggan.get(position).toString());
        holder.tvTeleponPelanggan.setText(arrTeleponpelanggan.get(position).toString());
        holder.tvAlamatPelanggan.setText(arrAlamatpelanggan.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNamapelanggan.size();
    }

    public class ViewHolderPelanggan extends RecyclerView.ViewHolder{
        private TextView tvIdpelanggan ,tvNamaPelanggan ,tvTeleponPelanggan ,tvAlamatPelanggan;
        public ViewHolderPelanggan(@NonNull View itemView) {
            super(itemView);
            tvIdpelanggan =itemView.findViewById(R.id.tv_idpelanggan);
            tvNamaPelanggan = itemView.findViewById(R.id.tv_namapelanggan);
            tvTeleponPelanggan =itemView.findViewById(R.id.tv_nopelanggan);
            tvAlamatPelanggan =itemView.findViewById(R.id.tv_alamatpelanggan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder pesan = new AlertDialog.Builder(context);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih "+ tvNamaPelanggan.getText().toString() +"Pilih Perintah Yang Anda Inginkan ");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            MyDatabaseHelper myDB = new MyDatabaseHelper(context);
                            long eksekusi = myDB.hapusPelanggan (tvIdpelanggan.getText().toString());


                            if (eksekusi == -1){
                                Toast.makeText(context, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Sukses Menghapus Data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MasterPelanggan)context).onResume();
                            }
                        }
                    });

                    pesan.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(context, UbahDatapelanggan.class);
                            intent.putExtra("vId",tvIdpelanggan.getText().toString());
                            intent.putExtra("vNama",tvNamaPelanggan.getText().toString());
                            intent.putExtra("vTelepon",tvTeleponPelanggan.getText().toString());
                            intent.putExtra("vAlamat",tvAlamatPelanggan.getText().toString());
                            context.startActivity(intent);

                        }
                    });

                    pesan.show();
                    return false;
                }
            });

        }
    }
}
