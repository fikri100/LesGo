package com.example.asus.lesgo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.lesgo.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterMurid extends AppCompatActivity {

    EditText edtNama, edtUsername, edtEmail, edtPassword, edtMusername;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_murid );

        getSupportActionBar().setTitle( "Register Murid" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        edtNama = (EditText)findViewById( R.id.nama );
        edtUsername = (EditText)findViewById( R.id.username );
        edtMusername = (EditText)findViewById( R.id.username );
        edtEmail = (EditText)findViewById( R.id.email );
        edtPassword = (EditText)findViewById( R.id.password );

        btnRegister = (Button)findViewById( R.id.registerMurid );

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference akun_user = database.getReference("User");

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog( RegisterMurid.this );
                mdialog.setMessage( "Menunggu.." );
                mdialog.show();

                akun_user.addValueEventListener( new ValueEventListener() {
                    String username =  edtUsername.getText().toString();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child( username ).toString() == username) {
                            mdialog.dismiss();
                            Toast.makeText( RegisterMurid.this, "Registrasi Gagal", Toast.LENGTH_SHORT ).show();
                        }
                        else {
                            mdialog.dismiss();
                            User user = new User( edtNama.getText().toString(), edtMusername.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString());
                            akun_user.child( edtUsername.getText().toString() ).setValue( user );
                            Intent intent = new Intent( RegisterMurid.this,LoginMurid.class );
                            startActivity( intent );
                            Toast.makeText(RegisterMurid.this,"Registrasi sukses", Toast.LENGTH_SHORT  ).show();
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
