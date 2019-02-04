package com.example.asus.lesgo.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.R;

public class KomentarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtNama, txtKomentar;

    private ItemClickListener itemClickListener;

    public KomentarViewHolder(@NonNull View itemView) {
        super( itemView );

        txtNama = (TextView)itemView.findViewById( R.id.nama );
        txtKomentar = (TextView)itemView.findViewById( R.id.komentar );

        itemView.setOnClickListener( this );
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }
}

