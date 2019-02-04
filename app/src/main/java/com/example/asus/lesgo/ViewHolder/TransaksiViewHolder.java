package com.example.asus.lesgo.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.R;


public class TransaksiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNama, txtJam, txtTanggal, txtAlamat, txtNoHp, txtKeterangan;

    private ItemClickListener itemClickListener;

    public TransaksiViewHolder(@NonNull View itemView) {
        super( itemView );

        txtNama = (TextView)itemView.findViewById( R.id.nama );
        txtJam = (TextView)itemView.findViewById( R.id.jam );
        txtTanggal = (TextView)itemView.findViewById( R.id.tanggal );
        txtAlamat = (TextView)itemView.findViewById( R.id.alamat );
        txtNoHp = (TextView)itemView.findViewById( R.id.noHp );
        txtKeterangan = (TextView)itemView.findViewById( R.id.keterangan );

        itemView.setOnClickListener( this );
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick( v, getAdapterPosition(), false);
    }
}
