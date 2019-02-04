package com.example.asus.lesgo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.lesgo.model.Guru;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterGuru extends AppCompatActivity {

    EditText edtNama, edtUsername, edtEmail, edtPassword, edtMapel, edtHarga, edtAlamat;
    Button btnRegister;
    String imageUri = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_guru );

        getSupportActionBar().setTitle( "Register Guru" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        edtNama = (EditText)findViewById( R.id.nama );
        edtUsername = (EditText)findViewById( R.id.username );
        edtEmail = (EditText)findViewById( R.id.email );
        edtPassword = (EditText)findViewById( R.id.password );
        edtMapel = (EditText)findViewById( R.id.mapel );
        edtHarga = (EditText)findViewById( R.id.harga );
        edtAlamat = (EditText)findViewById( R.id.alamat );

        btnRegister = (Button)findViewById( R.id.registerGuru );

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference akun_guru = database.getReference("Guru");

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog( RegisterGuru.this );
                mdialog.setMessage( "Menunggu.." );
                mdialog.show();

                akun_guru.addValueEventListener( new ValueEventListener() {
                    String username =  edtUsername.getText().toString();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child( username ).toString() == username) {
                            mdialog.dismiss();
                            Toast.makeText( RegisterGuru.this, "Registrasi Gagal", Toast.LENGTH_SHORT ).show();
                        }
                        else {
                            mdialog.dismiss();
                            Guru guru = new Guru(imageUri , edtNama.getText().toString(), edtUsername.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), edtMapel.getText().toString(), edtHarga.getText().toString(), edtAlamat.getText().toString() );
                            akun_guru.child( edtUsername.getText().toString() ).setValue( guru );
                            Intent intent = new Intent( RegisterGuru.this,LoginGuru.class );
                            startActivity( intent );
                            Toast.makeText(RegisterGuru.this,"Registrasi sukses", Toast.LENGTH_SHORT  ).show();
                            finish();
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
