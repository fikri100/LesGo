package com.example.asus.lesgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.ViewHolder.HistoryGuruViewHolder;
import com.example.asus.lesgo.ViewHolder.HistoryMuridViewHolder;
import com.example.asus.lesgo.ViewHolder.KomentarViewHolder;
import com.example.asus.lesgo.model.ModelHistoryGuru;
import com.example.asus.lesgo.model.ModelHistoryMurid;
import com.example.asus.lesgo.model.ModelKomentar;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListKomentar extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference komentar;

    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ModelKomentar, KomentarViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_komentar );

        getSupportActionBar().setTitle( "Komentar" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        komentar = database.getReference("Komentar");

        recyclerMenu = (RecyclerView)findViewById(R.id.recycleViewKomentar);
        recyclerMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerMenu.setLayoutManager(layoutManager);
        loadMenu(CommonGuru.currentGuru.getNama());

    }

    private void loadMenu(String nama) {
        adapter = new FirebaseRecyclerAdapter<ModelKomentar, KomentarViewHolder>(ModelKomentar.class, R.layout.komentar_item, KomentarViewHolder.class, komentar.orderByChild( "namaGuru" ).equalTo( nama )) {
            @Override
            protected void populateViewHolder(KomentarViewHolder viewHolder, ModelKomentar model, int position) {
                viewHolder.txtNama.setText( model.getNama() );
                viewHolder.txtKomentar.setText( model.getKomentar() );

            }
        };

        recyclerMenu.setAdapter( adapter );
    }
}
