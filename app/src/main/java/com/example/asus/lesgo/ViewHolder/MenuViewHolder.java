package com.example.asus.lesgo.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNama, txtEmail, txtMapel, txtHarga, txtAlamat;
    public ImageView imgProfil;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super( itemView );

        imgProfil = (ImageView)itemView.findViewById( R.id.imageProfil );
        txtNama = (TextView)itemView.findViewById( R.id.nama );
        txtEmail = (TextView)itemView.findViewById( R.id.email );
        txtMapel = (TextView)itemView.findViewById( R.id.mapel );
        txtHarga = (TextView)itemView.findViewById( R.id.harga );
        txtAlamat = (TextView)itemView.findViewById( R.id.alamat );

        itemView.setOnClickListener( this );
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick( v, getAdapterPosition(), false  );
    }
}
