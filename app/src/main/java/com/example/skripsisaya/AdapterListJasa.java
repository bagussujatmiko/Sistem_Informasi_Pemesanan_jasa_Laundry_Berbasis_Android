package com.example.skripsisaya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListJasa extends RecyclerView.Adapter<AdapterListJasa.ViewHolderListJasa>{
    private Context Con;
    private ArrayList arrIDlistLayanan, arrListNamaLayanan , arrListSatuanJasa , arrListhargaJasa;

    public AdapterListJasa(Context con, ArrayList arrIDlistLayanan, ArrayList arrListNamaLayanan, ArrayList arrListSatuanJasa, ArrayList arrListhargaJasa) {
        Con = con;
        this.arrIDlistLayanan = arrIDlistLayanan;
        this.arrListNamaLayanan = arrListNamaLayanan;
        this.arrListSatuanJasa = arrListSatuanJasa;
        this.arrListhargaJasa = arrListhargaJasa;
    }


    @NonNull
    @Override
    public ViewHolderListJasa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterLayanan = LayoutInflater.from(Con);
        View view = inflaterLayanan.inflate(R.layout.list_item_layanan, parent, false);

        return new ViewHolderListJasa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListJasa holder, int position) {
        holder.tvid.setText(arrIDlistLayanan.get(position).toString());
        holder.tvnamalayanan.setText(arrListNamaLayanan.get(position).toString());
        holder.tvsatuanjasa.setText(arrListSatuanJasa.get(position).toString());
        holder.tvhargajasa.setText(arrListhargaJasa.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrListNamaLayanan.size();
    }

    public class ViewHolderListJasa extends RecyclerView.ViewHolder {
        private TextView tvid , tvnamalayanan , tvsatuanjasa, tvhargajasa;

        public ViewHolderListJasa(@NonNull View itemViewListlayanan) {
            super(itemViewListlayanan);
            tvid = itemViewListlayanan.findViewById(R.id.tv_idlayanan);
            tvnamalayanan = itemViewListlayanan.findViewById(R.id.tv_namalayanan);
            tvsatuanjasa = itemViewListlayanan.findViewById(R.id.tv_satuanjasa);
            tvhargajasa = itemViewListlayanan.findViewById(R.id.tv_hargajasa);

            itemViewListlayanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Con, Transaksi_Laundry.class);
                    intent.putExtra("vId",tvid.getText().toString());
                    intent.putExtra("vNamalayanan",tvnamalayanan.getText().toString());
                    intent.putExtra("vSatuanharga",tvsatuanjasa.getText().toString());
                    intent.putExtra("vHargajasa",tvhargajasa.getText().toString());
                    Con.startActivity(intent);
                }
            });
        }

    }

}
