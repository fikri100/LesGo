package com.example.asus.lesgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lesgo.Common.CommonDataDiri;
import com.example.asus.lesgo.Common.CommonDataDiriGuru;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.Common.CommonKomentar;
import com.example.asus.lesgo.Common.CommonTransaksi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class KonfirmasiTransaksi extends AppCompatActivity {

    TextView edtNama, edtJam, edtTanggal, edtAlamat, edtNohp, edtKeterangan;

    Button btnTerima, btnTolak;

    long idt=0;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_konfirmasi_transaksi );

        getSupportActionBar().setTitle( "Konfirmasi Transaksi" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        edtNama = (TextView)findViewById( R.id.nama );
        Intent intent = getIntent();
        final String nama = intent.getStringExtra( "nama" );
        edtNama.setText( nama );
        edtJam = (TextView) findViewById( R.id.jam );
        Intent jamIntent = getIntent();
        String jam = jamIntent.getStringExtra( "jam" );
        edtJam.setText( jam );
        edtTanggal = (TextView) findViewById( R.id.tanggal );
        Intent tanggalIntent = getIntent();
        String tanggal = tanggalIntent.getStringExtra( "tanggal" );
        edtTanggal.setText( tanggal );
        edtAlamat = (TextView) findViewById( R.id.alamat );
        Intent alamatIntent = getIntent();
        String alamat = alamatIntent.getStringExtra( "alamat" );
        edtAlamat.setText( alamat );
        edtNohp = (TextView) findViewById( R.id.noHp);
        Intent hpIntent = getIntent();
        String noHp = hpIntent.getStringExtra( "noHp" );
        edtNohp.setText( noHp );
        edtKeterangan = (TextView) findViewById( R.id.keterangan );
        Intent keteranganIntent = getIntent();
        String keterangan = keteranganIntent.getStringExtra( "keterangan" );
        edtKeterangan.setText( keterangan );

        btnTerima = (Button)findViewById( R.id.terima );
        btnTolak = (Button)findViewById( R.id.tolak );




        btnTerima.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference leadersRef = FirebaseDatabase.getInstance().getReference("Transaksi");
                final Query query = leadersRef.orderByChild( "namaGuru" ).equalTo( CommonGuru.currentGuru.getNama()  );

                query.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                String postkey = child.getRef().getKey();
                                leadersRef.child( postkey ).child( "keterangan" ).setValue( "Diterima" );
                                Toast.makeText( KonfirmasiTransaksi.this, "Terima Murid", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( KonfirmasiTransaksi.this, MainActivityGuru.class );
                                startActivity( intent );
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );

        btnTolak.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference leadersRef = FirebaseDatabase.getInstance().getReference("Transaksi");
                final Query query = leadersRef.orderByChild( "namaGuru" ).equalTo( CommonGuru.currentGuru.getNama() );

                query.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot child: dataSnapshot.getChildren()){
                                String postkey = child.getRef().getKey();
                                leadersRef.child( postkey ).child( "keterangan" ).setValue( "Ditolak" );
                                Toast.makeText( KonfirmasiTransaksi.this, "Tolak Murid", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( KonfirmasiTransaksi.this , MainActivityGuru.class );
                                startActivity( intent );
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );


    }
}
