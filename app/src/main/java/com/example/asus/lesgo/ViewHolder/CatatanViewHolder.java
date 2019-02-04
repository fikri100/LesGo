package com.example.asus.lesgo.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.R;

public class CatatanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtNama, txtCatatan;

    private ItemClickListener itemClickListener;

    public CatatanViewHolder(@NonNull View itemView) {
        super( itemView );

        txtNama = (TextView)itemView.findViewById( R.id.nama );
        txtCatatan = (TextView)itemView.findViewById( R.id.catatan );

        itemView.setOnClickListener( this );
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

    }
}

