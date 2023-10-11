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

public class AdapterLayanan  extends RecyclerView.Adapter<AdapterLayanan.ViewHolderLayanan> {
    private Context ctx;
    private ArrayList arridlayanan , arrnamalayanan ,arrsatuanjasa , arrhargajasa;
    public AdapterLayanan(Context ctx, ArrayList arridlayanan, ArrayList arrnamalayanan, ArrayList arrsatuanjasa, ArrayList arrhargajasa) {
        this.ctx = ctx;
        this.arridlayanan = arridlayanan;
        this.arrnamalayanan = arrnamalayanan;
        this.arrsatuanjasa = arrsatuanjasa;
        this.arrhargajasa = arrhargajasa;
    }

    @NonNull
    @Override
    public ViewHolderLayanan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflaterLayanan = LayoutInflater.from(ctx);
        View view = inflaterLayanan.inflate(R.layout.list_item_layanan, parent, false);
        return new ViewHolderLayanan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLayanan holder, int position) {
        holder.tvid.setText(arridlayanan.get(position).toString());
        holder.tvnamalayanan.setText(arrnamalayanan.get(position).toString());
        holder.tvsatuanjasa.setText(arrsatuanjasa.get(position).toString());
        holder.tvhargajasa.setText(arrhargajasa.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrnamalayanan.size();
    }

    public class ViewHolderLayanan extends RecyclerView.ViewHolder{

        private TextView tvid , tvnamalayanan , tvsatuanjasa, tvhargajasa;
        public ViewHolderLayanan(@NonNull View itemViewlayanan) {
            super(itemViewlayanan);
            tvid = itemViewlayanan.findViewById(R.id.tv_idlayanan);
            tvnamalayanan = itemViewlayanan.findViewById(R.id.tv_namalayanan);
            tvsatuanjasa = itemViewlayanan.findViewById(R.id.tv_satuanjasa);
            tvhargajasa = itemViewlayanan.findViewById(R.id.tv_hargajasa);

            itemViewlayanan.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih " + tvnamalayanan.getText().toString() + "Pilih Perintah Yang Anda Inginkan ");
                    pesan.setCancelable(true);


                   pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                           MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                           long eksekusi = myDB.hapusLayanan (tvid.getText().toString());


                           if (eksekusi == -1){
                               Toast.makeText(ctx, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(ctx, "Sukses Menghapus Data", Toast.LENGTH_SHORT).show();
                               dialog.dismiss();
                               ((MasterJasa)ctx).onResume();
                           }
                       }

                   });

                   pesan.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                           Intent intent = new Intent(ctx, UbahDataLayanan.class);
                           intent.putExtra("vId",tvid.getText().toString());
                           intent.putExtra("vNamalayanan",tvnamalayanan.getText().toString());
                           intent.putExtra("vSatuanharga",tvsatuanjasa.getText().toString());
                           intent.putExtra("vHargajasa",tvhargajasa.getText().toString());
                           ctx.startActivity(intent);


                       }
                   });

                   pesan.show();
                    return false;
                }
            });

        }
    }

}

