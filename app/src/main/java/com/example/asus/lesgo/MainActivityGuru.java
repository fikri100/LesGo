package com.example.asus.lesgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.ViewHolder.MenuViewHolder;
import com.example.asus.lesgo.ViewHolder.TransaksiViewHolder;
import com.example.asus.lesgo.model.Guru;
import com.example.asus.lesgo.model.TransaksiGuru;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivityGuru extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference transaksi;
    private FirebaseAuth auth;

    TextView txtUsername, txtNama;
    RecyclerView recycleMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<TransaksiGuru, TransaksiViewHolder> adapter;
    private List<TransaksiGuru> mTransaksi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_guru );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        txtNama = (TextView)findViewById( R.id.nama );



        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        transaksi = database.getReference("Transaksi");


        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        View headerView = navigationView.getHeaderView(0);
        txtUsername = (TextView)headerView.findViewById(R.id.username);
        txtUsername.setText(CommonGuru.currentGuru.getNama());

        recycleMenu = (RecyclerView)findViewById(R.id.recycleViewGuru);
        recycleMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager( this );
        recycleMenu.setLayoutManager( layoutManager );
        loadMenu(CommonGuru.currentGuru.getNama());

    }

    private void loadMenu(String nama) {
        adapter = new FirebaseRecyclerAdapter<TransaksiGuru, TransaksiViewHolder>(TransaksiGuru.class, R.layout.transaksi_item, TransaksiViewHolder.class, transaksi.orderByChild( "namaGuru" ).equalTo( nama )) {
            @Override
            protected void populateViewHolder(TransaksiViewHolder viewHolder, TransaksiGuru model, int position) {
                viewHolder.txtNama.setText( model.getNama() );
                viewHolder.txtJam.setText( model.getJam() );
                viewHolder.txtTanggal.setText( model.getTanggal() );
                viewHolder.txtAlamat.setText( model.getAlamat() );
                viewHolder.txtNoHp.setText( model.getNohp() );
                viewHolder.txtKeterangan.setText( model.getKeterangan() );


                final TransaksiGuru clickItem = model;

                viewHolder.setItemClickListener( new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Toast.makeText( MainActivityGuru.this, "" + clickItem.getNama(), Toast.LENGTH_SHORT ).show();
                        Intent transaksi = new Intent( MainActivityGuru.this, KonfirmasiTransaksi.class );
                        transaksi.putExtra( "nama", clickItem.getNama() );
                        transaksi.putExtra( "jam", clickItem.getJam() );
                        transaksi.putExtra( "tanggal", clickItem.getTanggal() );
                        transaksi.putExtra( "alamat", clickItem.getAlamat() );
                        transaksi.putExtra( "noHp", clickItem.getNohp() );
                        transaksi.putExtra( "keterangan", clickItem.getKeterangan() );
                        startActivity( transaksi );
                    }
                } );
            }
        };
        recycleMenu.setAdapter( adapter );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main_activity_guru, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent( MainActivityGuru.this, HistoryGuru.class);
            startActivity(intent);
        } else if (id == R.id.nav_dataDiri) {
            Intent intent = new Intent( MainActivityGuru.this, DataDiriGuru.class);
            startActivity(intent);
        } else if (id == R.id.nav_ulasan) {
            Intent intent = new Intent( MainActivityGuru.this, ListKomentar.class);
            startActivity(intent);
        } else if (id == R.id.nav_catatan) {
            Intent intent = new Intent( MainActivityGuru.this, CatatanGuru.class);
            startActivity(intent);
        } else if (id == R.id.logout) {
            Intent login = new Intent( MainActivityGuru.this, Home.class );
            login.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( login );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
