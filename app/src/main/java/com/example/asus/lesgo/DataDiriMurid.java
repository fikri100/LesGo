package com.example.asus.lesgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonDataDiri;
import com.example.asus.lesgo.model.Guru;
import com.example.asus.lesgo.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class DataDiriMurid extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference user;
    private FirebaseAuth auth;
    DataSnapshot dataSnapshot;
    String username;

    EditText edtNama, edtUsername, edtPassword, edtEmail;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data_diri_murid );

        getSupportActionBar().setTitle( "Data Diri" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = database.getReference("User");

        //User user = dataSnapshot.child( edtUsername.getText().toString() ).getValue( User.class );

        edtNama = (EditText)findViewById( R.id.nama );
        edtNama.setText(Common.currentUser.getNama());
        edtUsername = (EditText)findViewById( R.id.musername );
        edtUsername.setText(Common.currentUser.getMusername());
        edtPassword = (EditText)findViewById( R.id.password );
        edtPassword.setText(Common.currentUser.getPassword());
        edtEmail = (EditText)findViewById( R.id.email );
        edtEmail.setText(Common.currentUser.getEmail());
        btnEdit = (Button)findViewById( R.id.edit );

        btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User use = new User(edtNama.getText().toString(), edtUsername.getText().toString(), edtEmail.getText().toString(),
                        edtPassword.getText().toString());
                Map<String, Object> u = use.toMap();
                user.child( edtUsername.getText().toString() ).updateChildren( u);
            }
        } );
    }
}
