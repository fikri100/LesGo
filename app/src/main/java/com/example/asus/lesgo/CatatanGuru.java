package com.example.asus.lesgo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asus.lesgo.Common.CommonCatatan;
import com.example.asus.lesgo.Common.CommonDataDiriGuru;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.ViewHolder.CatatanViewHolder;
import com.example.asus.lesgo.ViewHolder.KomentarViewHolder;
import com.example.asus.lesgo.model.ModelCatatan;
import com.example.asus.lesgo.model.ModelKomentar;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CatatanGuru extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference catatan;


    RecyclerView recyclerMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ModelCatatan, CatatanViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_catatan_guru );

        getSupportActionBar().setTitle( "Catatan" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        catatan = database.getReference("Catatan");

        recyclerMenu = (RecyclerView)findViewById(R.id.recycleViewCatatan);
        recyclerMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerMenu.setLayoutManager(layoutManager);
        loadMenu(CommonGuru.currentGuru.getNama() );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( CatatanGuru.this, TambahCatatan.class);
                intent.putExtra( "namaGuru", CommonGuru.currentGuru.getNama() );
                startActivity( intent );
            }
        } );
    }

    private void loadMenu(String nama) {
        adapter = new FirebaseRecyclerAdapter<ModelCatatan, CatatanViewHolder>(ModelCatatan.class, R.layout.catatan_item, CatatanViewHolder.class, catatan.orderByChild( "namaGuru" ).equalTo( nama )) {
            @Override
            protected void populateViewHolder(CatatanViewHolder viewHolder, ModelCatatan model, int position) {
                viewHolder.txtNama.setText( model.getNama() );
                viewHolder.txtCatatan.setText( model.getCatatan() );
            }
        };

        recyclerMenu.setAdapter( adapter );
    }
}
