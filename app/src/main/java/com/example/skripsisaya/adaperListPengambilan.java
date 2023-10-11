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

public class adaperListPengambilan extends RecyclerView.Adapter<adaperListPengambilan.ViewHolderListTransaksi> {

    private Context coxt;


    private ArrayList  arrIdtransaksi, arrNamapelanggan , arrNoWa , arrAlamat ,  arrtanggalTransaksi ,
            arrLayanan,arrHarga,arrBerat, arrBayar,arrBiaya, arrkembalian;

    public adaperListPengambilan(Context coxt, ArrayList arrIdtransaksi, ArrayList arrNamapelanggan, ArrayList arrNoWa, ArrayList arrAlamat, ArrayList arrtanggalTransaksi, ArrayList arrLayanan, ArrayList arrHarga, ArrayList arrBerat, ArrayList arrBayar, ArrayList arrBiaya, ArrayList arrkembalian) {
        this.coxt = coxt;
        this.arrIdtransaksi = arrIdtransaksi;
        this.arrNamapelanggan = arrNamapelanggan;
        this.arrNoWa = arrNoWa;
        this.arrAlamat = arrAlamat;
        this.arrtanggalTransaksi = arrtanggalTransaksi;
        this.arrLayanan = arrLayanan;
        this.arrHarga = arrHarga;
        this.arrBerat = arrBerat;
        this.arrBayar = arrBayar;
        this.arrBiaya = arrBiaya;
        this.arrkembalian = arrkembalian;
    }
    @NonNull
    @Override
    public ViewHolderListTransaksi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterTransaksi = LayoutInflater.from(coxt);
        View tampil = inflaterTransaksi.inflate(R.layout.list_item_transaksi, parent ,false);
        return new adaperListPengambilan.ViewHolderListTransaksi(tampil);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListTransaksi holder, int position) {

        holder.tvIdtransaksiPelanggan.setText(arrIdtransaksi.get(position).toString());
        holder.tvnm_pelangganTransaksi.setText(arrNamapelanggan.get(position).toString());
        holder.tvno_whatsapp.setText(arrNoWa.get(position).toString());
        holder.tvalamat.setText(arrAlamat.get(position).toString());
        holder.tvtanggaltransaksipelanggan.setText(arrtanggalTransaksi.get(position).toString());
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

    public class ViewHolderListTransaksi extends RecyclerView.ViewHolder {

        private TextView tvIdtransaksiPelanggan , tvtanggaltransaksipelanggan  ,tvnm_pelangganTransaksi ,
                tvno_whatsapp ,tvalamat , tvnm_layanan,  tvharga_jasa, tvberat,  tvbiaya_layanan,  tvbayar,  tvkembalian;
        public ViewHolderListTransaksi(@NonNull View itemViewTransaksi) {
            super(itemViewTransaksi);
            tvIdtransaksiPelanggan = itemViewTransaksi.findViewById(R.id.tv_idtransaksi);
            tvnm_pelangganTransaksi = itemViewTransaksi.findViewById(R.id.tv_nm_pelanggan);
            tvno_whatsapp = itemViewTransaksi.findViewById(R.id.tv_no_whatsapp);
            tvalamat = itemViewTransaksi.findViewById(R.id.tv_alamat);
            tvtanggaltransaksipelanggan = itemViewTransaksi.findViewById(R.id.tv_tanggal);
            tvnm_layanan =itemView.findViewById(R.id.tv_nm_layanan);
            tvharga_jasa = itemView.findViewById(R.id.tv_harga_jasa);
            tvberat =itemView.findViewById(R.id.tv_berat);
            tvbiaya_layanan =itemView.findViewById(R.id.tv_biaya_layanan);
            tvbayar = itemView.findViewById(R.id.tv_bayar);
            tvkembalian = itemView.findViewById(R.id.tv_kembalian);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(coxt);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih " +  tvnm_pelangganTransaksi.getText().toString() + " Pilih Perintah Yang Anda Inginkan ");
                    pesan.setCancelable(true);


                    pesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(coxt);
                            long eksekusi = myDB.hapustransaksi (tvIdtransaksiPelanggan.getText().toString());

                            if (eksekusi == -1){
                                Toast.makeText(coxt, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(coxt, "Sukses Menghapus Data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MaterTransaksi) coxt).onResume();
                            }

                        }



                    });

                    pesan.setPositiveButton("PENGAMBILAN ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(coxt, Pengembalian_Pakaian.class);
                            intent.putExtra("vIdtransaksi",tvIdtransaksiPelanggan.getText().toString());
                            intent.putExtra("vNamaPelanggan", tvnm_pelangganTransaksi.getText().toString());
                            intent.putExtra("vNowaPelanggan", tvbiaya_layanan.getText().toString());
                            intent.putExtra("vNamalayanan", tvtanggaltransaksipelanggan.getText().toString());
                            intent.putExtra("vBeratpakaian", tvalamat.getText().toString());
                            intent.putExtra("vBiayapakaian",  tvbayar.getText().toString());
                            coxt.startActivity(intent);


                        }
                    });
                    pesan.show();
                    return false;
                }
            });

        }
    }
}
