package com.example.asus.lesgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.ViewHolder.HistoryMuridViewHolder;
import com.example.asus.lesgo.model.ModelHistoryMurid;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryMurid extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference history;

    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ModelHistoryMurid, HistoryMuridViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history_murid );

        getSupportActionBar().setTitle( "History" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        history = database.getReference("Transaksi");

        recyclerMenu = (RecyclerView)findViewById(R.id.recycleViewHistoryMurid);
        recyclerMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerMenu.setLayoutManager(layoutManager);
        loadMenu(Common.currentUser.getNama());

    }

    private void loadMenu(String nama) {
        adapter = new FirebaseRecyclerAdapter<ModelHistoryMurid, HistoryMuridViewHolder>(ModelHistoryMurid.class, R.layout.history_murid_item, HistoryMuridViewHolder.class, history.orderByChild( "nama" ).equalTo( nama )) {
            @Override
            protected void populateViewHolder(HistoryMuridViewHolder viewHolder, final ModelHistoryMurid model, int position) {
                viewHolder.txtNamaGuru.setText( model.getNamaGuru() );
                viewHolder.txtEmail.setText( model.getEmail() );
                viewHolder.txtMapel.setText( model.getMapel() );
                viewHolder.txtJam.setText( model.getJam() );
                viewHolder.txtTanggal.setText( model.getTanggal() );
                viewHolder.txtHarga.setText( model.getHarga() );
                viewHolder.btnKomentar.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent komentar = new Intent( HistoryMurid.this , Komentar.class );
                        komentar.putExtra( "namaGuru", model.getNamaGuru() );
                        komentar.putExtra( "nama", Common.currentUser.getNama() );
                        startActivity( komentar );
                    }
                } );

            }
        };
        recyclerMenu.setAdapter( adapter );

    }
}
