package com.example.asus.lesgo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.model.ModelKomentar;
import com.example.asus.lesgo.model.ModelTransaksi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Komentar extends AppCompatActivity {

    TextView txtNamaGuru, txtNama;
    EditText edtKomentar;
    Button btnKirimKomentar;
    private DatabaseReference komentar;

    FirebaseDatabase database;
    DatabaseReference guru;
    DatabaseReference id;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_komentar );

        getSupportActionBar().setTitle( "Komentar" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        guru = database.getReference("Guru");

        komentar =FirebaseDatabase.getInstance().getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        txtNamaGuru = (TextView) findViewById( R.id.namaGuru );
        Intent intent = getIntent();
        String namaGuru = intent.getStringExtra( "namaGuru" );
        txtNamaGuru.setText( namaGuru );

        txtNama = (TextView) findViewById( R.id.nama );
        Intent nama = getIntent();
        String namaMurid = nama.getStringExtra( "nama" );
        txtNama.setText( namaMurid );


        edtKomentar = (EditText)findViewById( R.id.komentar );
        btnKirimKomentar = (Button)findViewById( R.id.kirimKomentar );

        btnKirimKomentar.setOnClickListener( new View.OnClickListener() {
            //String guruLes = edtNamaGuru.getText().toString();
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog( Komentar.this );

                if (!isEmpty( edtKomentar.getText().toString() ))
                {
                    submitModelKomentar( new ModelKomentar( txtNamaGuru.getText().toString(), txtNama.getText().toString(), edtKomentar.getText().toString()) );
                    Toast.makeText( Komentar.this, "Berhasil Mengirimkan Komentar", Toast.LENGTH_SHORT ).show();
                    finish();
                }else {
                    Toast.makeText( Komentar.this, "Data Harus Diisi", Toast.LENGTH_SHORT ).show();
                }

            }
            InputMethodManager imm = (InputMethodManager)
                    getSystemService( Context.INPUT_METHOD_SERVICE );
        } );
    }

    private boolean isEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }

    public void submitModelKomentar(ModelKomentar modelKomentar) {
        komentar.child("Komentar").push().setValue(modelKomentar).addOnSuccessListener(this, new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {
                txtNamaGuru.setText("");
                txtNama.setText("");
                edtKomentar.setText("");
            }
        });
    }
}

