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

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.ViewHolderTransaksi> {
    private Context con;
    private ArrayList<String> arrIdtransaksi, arrNamapelanggan , arrNoWa , arrAlamat ,  arrtanggalTransaksi ,arrLayanan, arrHarga , arrBerat , arrBiaya, arrBayar ,arrkembalian;

    public AdapterTransaksi(Context con, ArrayList<String> arrIdtransaksi, ArrayList<String> arrNamapelanggan, ArrayList<String> arrNoWa, ArrayList<String> arrAlamat, ArrayList<String> arrtanggalTransaksi, ArrayList<String> arrLayanan, ArrayList<String> arrHarga, ArrayList<String> arrBerat, ArrayList<String> arrBiaya, ArrayList<String> arrBayar, ArrayList<String> arrkembalian) {
        this.con = con;
        this.arrIdtransaksi = arrIdtransaksi;
        this.arrNamapelanggan = arrNamapelanggan;
        this.arrNoWa = arrNoWa;
        this.arrAlamat = arrAlamat;
        this.arrtanggalTransaksi = arrtanggalTransaksi;
        this.arrLayanan = arrLayanan;
        this.arrHarga = arrHarga;
        this.arrBerat = arrBerat;
        this.arrBiaya = arrBiaya;
        this.arrBayar = arrBayar;
        this.arrkembalian = arrkembalian;
    }


    @NonNull
    @Override
    public ViewHolderTransaksi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterTransaksi = LayoutInflater.from(con);
        View muncul = inflaterTransaksi.inflate(R.layout.list_item_transaksi, parent ,false);
        return new ViewHolderTransaksi(muncul);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTransaksi holder, int position) {
        holder.tvIdtransaksi.setText(arrIdtransaksi.get(position).toString());
        holder.tvnm_pelanggan.setText(arrNamapelanggan.get(position).toString());
        holder.tvno_whatsapp.setText(arrNoWa.get(position).toString());
        holder.tvalamat.setText(arrAlamat.get(position).toString());
        holder.tvtanggaltransksi.setText(arrtanggalTransaksi.get(position).toString());
        holder.tvnm_layanan.setText(arrLayanan.get(position).toString());
        holder.tvharga_jasa.setText(arrHarga.get(position).toString());
        holder.tvberat.setText(arrBerat.get(position).toString());
        holder.tvbiaya_layanan.setText(arrBiaya.get(position).toString());
        holder.tvbayar.setText(arrBayar.get(position).toString());
        holder.tvkembalian.setText(arrkembalian.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNamapelanggan.size();
    }

    public class ViewHolderTransaksi extends RecyclerView.ViewHolder {


        private TextView tvIdtransaksi , tvtanggaltransksi,tvnm_pelanggan ,tvno_whatsapp ,tvalamat , tvnm_layanan, tvharga_jasa ,tvberat ,tvbiaya_layanan ,tvbayar , tvkembalian;
        public ViewHolderTransaksi(@NonNull View itemView) {
            super(itemView);
            tvIdtransaksi =itemView.findViewById(R.id.tv_idtransaksi);
            tvnm_pelanggan =itemView.findViewById(R.id.tv_nm_pelanggan);
            tvno_whatsapp =itemView.findViewById(R.id.tv_no_whatsapp);
            tvalamat = itemView.findViewById(R.id.tv_alamat);
            tvtanggaltransksi = itemView.findViewById(R.id.tv_tanggal);
            tvnm_layanan =itemView.findViewById(R.id.tv_nm_layanan);
            tvharga_jasa = itemView.findViewById(R.id.tv_harga_jasa);
            tvberat =itemView.findViewById(R.id.tv_berat);
            tvbiaya_layanan =itemView.findViewById(R.id.tv_biaya_layanan);
            tvbayar = itemView.findViewById(R.id.tv_bayar);
            tvkembalian = itemView.findViewById(R.id.tv_kembalian);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(con);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih " +  tvnm_pelanggan.getText().toString() + "Pilih Perintah Yang Anda Inginkan ");
                    pesan.setCancelable(true);
                    pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(con);
                            long eksekusi = myDB.hapustransaksi (tvIdtransaksi.getText().toString());

                            if (eksekusi == -1){
                                Toast.makeText(con, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(con, "Sukses Menghapus Data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MaterTransaksi) con).onResume();
                            }

                        }

                    });

                    pesan.setPositiveButton("PENGAMBILAN ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(con, Pengembalian_Pakaian.class);
                            intent.putExtra("vIdtransaksi",tvIdtransaksi.getText().toString());
                            intent.putExtra("vNamaPelanggan", tvnm_pelanggan.getText().toString());
                            intent.putExtra("vNowaPelanggan", tvno_whatsapp.getText().toString());
                            intent.putExtra("vBeratpakaian", tvberat.getText().toString());
                            intent.putExtra("vBeratpakaian",  tvbiaya_layanan.getText().toString());
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
