package com.example.asus.lesgo.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.R;


public class HistoryMuridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtNamaGuru, txtEmail, txtMapel, txtJam, txtTanggal, txtHarga;
    public Button btnKomentar;
    private ItemClickListener itemClickListener;

    public HistoryMuridViewHolder(@NonNull View itemView) {
        super( itemView );

        txtNamaGuru = (TextView)itemView.findViewById( R.id.namaGuru );
        txtEmail = (TextView)itemView.findViewById( R.id.email );
        txtMapel = (TextView)itemView.findViewById( R.id.mapel );
        txtJam = (TextView)itemView.findViewById( R.id.jam );
        txtTanggal = (TextView)itemView.findViewById( R.id.tanggal );
        txtHarga = (TextView)itemView.findViewById( R.id.harga );

        btnKomentar = (Button)itemView.findViewById( R.id.komentar );

        itemView.setOnClickListener( this );
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }
}
