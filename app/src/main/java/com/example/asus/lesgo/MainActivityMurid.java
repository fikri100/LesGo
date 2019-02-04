package com.example.asus.lesgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonDataDiri;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.Interface.ItemClickListener;
import com.example.asus.lesgo.ViewHolder.MenuViewHolder;
import com.example.asus.lesgo.model.Guru;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivityMurid extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference guru;
    private FirebaseAuth auth;

    String GuruId;
    TextView txtUsername, txtNamaGuru;
    RecyclerView recycleMenu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Guru, MenuViewHolder> adapter;
    private List<Guru> mGuru = new ArrayList<>();

    FirebaseRecyclerAdapter<Guru, MenuViewHolder> search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.murid_activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );


        txtNamaGuru = (TextView) findViewById( R.id.namaGuru );

        setSupportActionBar( toolbar );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        guru = database.getReference("Guru");

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        txtUsername = (TextView)headerView.findViewById(R.id.username);
        txtUsername.setText(com.example.asus.lesgo.Common.Common.currentUser.getNama());


        recycleMenu = (RecyclerView)findViewById(R.id.recycleViewMurid);
        recycleMenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycleMenu.setLayoutManager(layoutManager);
        loadMenu();


    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Guru, MenuViewHolder>(Guru.class, R.layout.menu_item, MenuViewHolder.class , guru) {
            @Override
            protected void populateViewHolder(final MenuViewHolder viewHolder, Guru model, int position) {

                Picasso.get().load( model.getImageUri() ).into( viewHolder.imgProfil );
                viewHolder.txtNama.setText( model.getNama() );
                viewHolder.txtEmail.setText( model.getEmail() );
                viewHolder.txtMapel.setText( model.getMapel() );
                viewHolder.txtHarga.setText( model.getHarga() );
                viewHolder.txtAlamat.setText( model.getAlamat() );

                final Guru clickItem = model;

                viewHolder.setItemClickListener( new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Toast.makeText( MainActivityMurid.this, "" + clickItem.getNama(), Toast.LENGTH_SHORT ).show();
                        Intent transaksi = new Intent( MainActivityMurid.this, Transaksi.class );
                        transaksi.putExtra( "imageUri", clickItem.getImageUri() );
                        transaksi.putExtra( "nama", clickItem.getNama() );
                        transaksi.putExtra( "mapel", clickItem.getMapel() );
                        transaksi.putExtra( "email", clickItem.getEmail() );
                        transaksi.putExtra( "harga", clickItem.getHarga() );
                        transaksi.putExtra( "namaMurid", Common.currentUser.getNama() );
                        transaksi.putExtra( "noHp", Common.currentUser.getEmail() );

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
            Intent intent = new Intent( MainActivityMurid.this, HistoryMurid.class);
            startActivity(intent);

        } else if (id == R.id.nav_dataDiri) {
            Intent dataDiri = new Intent( MainActivityMurid.this, DataDiriMurid.class);
            startActivity(dataDiri);

        } else if (id == R.id.logout) {
            Intent login = new Intent( MainActivityMurid.this, Home.class );
            login.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity( login );
        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
