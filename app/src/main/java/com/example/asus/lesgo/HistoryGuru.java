package com.example.asus.lesgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.ViewHolder.HistoryGuruViewHolder;
import com.example.asus.lesgo.ViewHolder.HistoryMuridViewHolder;
import com.example.asus.lesgo.model.ModelHistoryGuru;
import com.example.asus.lesgo.model.ModelHistoryMurid;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class HistoryGuru extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference history;

    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ModelHistoryGuru, HistoryGuruViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history_guru );

        getSupportActionBar().setTitle( "History" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        history = database.getReference("Transaksi");

        recyclerMenu = (RecyclerView)findViewById(R.id.recycleViewHistoryGuru);
        recyclerMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerMenu.setLayoutManager(layoutManager);
        loadMenu(CommonGuru.currentGuru.getNama());
    }

    private void loadMenu(String nama) {
        adapter = new FirebaseRecyclerAdapter<ModelHistoryGuru, HistoryGuruViewHolder>(ModelHistoryGuru.class, R.layout.history_guru_item, HistoryGuruViewHolder.class, history.orderByChild( "namaGuru" ).equalTo( nama )) {
            @Override
            protected void populateViewHolder(HistoryGuruViewHolder viewHolder, ModelHistoryGuru model, int position) {
                viewHolder.txtNama.setText( model.getNama() );
                viewHolder.txtJam.setText( model.getJam() );
                viewHolder.txtTanggal.setText( model.getTanggal() );
                viewHolder.txtAlamat.setText( model.getAlamat() );
                viewHolder.txtNoHp.setText( model.getNohp() );

            }
        };

        recyclerMenu.setAdapter( adapter );
    }
}
