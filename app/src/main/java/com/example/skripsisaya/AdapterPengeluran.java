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

public class AdapterPengeluran extends RecyclerView.Adapter<AdapterPengeluran.ViewHolderPengeluaran> {
    private Context con;
    private ArrayList arrIdpengeluran, arrNamabarang , arrJumlah , arrHargabarang , arrtanggal;
    public AdapterPengeluran(Context con, ArrayList arrIdpengeluran, ArrayList arrNamabarang, ArrayList arrJumlah, ArrayList arrHargabarang, ArrayList arrtanggal) {
        this.con = con;
        this.arrIdpengeluran = arrIdpengeluran;
        this.arrNamabarang = arrNamabarang;
        this.arrJumlah = arrJumlah;
        this.arrHargabarang = arrHargabarang;
        this.arrtanggal = arrtanggal;
    }

    @NonNull
    @Override
    public ViewHolderPengeluaran onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterPengeluran = LayoutInflater.from(con);
        View muncul = inflaterPengeluran.inflate(R.layout.list_item_pengeluaran, parent ,false);
        return new AdapterPengeluran.ViewHolderPengeluaran(muncul);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPengeluaran holder, int position) {

        holder.tvIdpengeluaran.setText(arrIdpengeluran.get(position).toString());
        holder.tvNamabarang.setText(arrNamabarang.get(position).toString());
        holder.tvjumlah.setText(arrJumlah.get(position).toString());
        holder.tvhargabarang.setText(arrHargabarang.get(position).toString());
        holder.tvtanggal.setText(arrtanggal.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNamabarang.size();
    }

    public class ViewHolderPengeluaran extends RecyclerView.ViewHolder{

        private TextView tvIdpengeluaran ,tvNamabarang ,tvjumlah ,tvhargabarang , tvtanggal;
      public ViewHolderPengeluaran(@NonNull View itemView) {
          super(itemView);
          tvIdpengeluaran =itemView.findViewById(R.id.tv_idpengeluaran);
          tvNamabarang = itemView.findViewById(R.id.tv_namapengeluaran);
          tvjumlah =itemView.findViewById(R.id.tv_jumlahpengeluaran);
          tvhargabarang =itemView.findViewById(R.id.tv_hargapengeluaran);
          tvtanggal = itemView.findViewById(R.id.tv_tanggalpengeluaran);

          itemView.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {
                  AlertDialog.Builder pesan = new AlertDialog.Builder(con);
                  pesan.setTitle("Perhatian");
                  pesan.setMessage("Anda Memilih "+ tvNamabarang.getText().toString() +"Pilih Perintah Yang Anda Inginkan ");
                  pesan.setCancelable(true);

                  pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {

                          MyDatabaseHelper myDB = new MyDatabaseHelper(con);
                          long eksekusi = myDB.hapuspengeluaran (tvIdpengeluaran.getText().toString());


                          if (eksekusi == -1){
                              Toast.makeText(con, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                          }else {
                              Toast.makeText(con, "Sukses Menghapus Data", Toast.LENGTH_SHORT).show();
                              dialog.dismiss();
                              ((MaterPengeluaran)con).onResume();
                          }
                      }
                  });

                  pesan.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          Intent intent = new Intent(con, UbahDataPengeluaran.class);
                          intent.putExtra("vId",tvIdpengeluaran.getText().toString());
                          intent.putExtra("vNamapengeluaran",tvNamabarang.getText().toString());
                          intent.putExtra("vjumlahpengeluaran",tvjumlah.getText().toString());
                          intent.putExtra("vHargapengaluran",tvhargabarang.getText().toString());
                          intent.putExtra("vtanggalpengeluaran",tvtanggal.getText().toString());
                          con.startActivity(intent);

                      }
                  });
                  pesan.show();
                  return false;
              }
          });
      }
  }
}
