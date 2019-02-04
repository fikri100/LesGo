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

import com.example.asus.lesgo.model.ModelCatatan;
import com.example.asus.lesgo.model.ModelKomentar;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahCatatan extends AppCompatActivity {

    TextView txtNamaGuru;
    EditText edtNama, edtCatatan;
    Button btnKirimCatatan;
    private DatabaseReference catatan;

    FirebaseDatabase database;
    DatabaseReference guru;
    DatabaseReference id;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tambah_catatan );

        getSupportActionBar().setTitle( "Catatan" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        guru = database.getReference("Guru");

        catatan =FirebaseDatabase.getInstance().getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        txtNamaGuru = (TextView) findViewById( R.id.namaGuru );
        Intent intent = getIntent();
        String namaGuru = intent.getStringExtra( "namaGuru" );
        txtNamaGuru.setText( namaGuru );

        edtNama = (EditText)findViewById( R.id.nama );
        edtCatatan = (EditText)findViewById( R.id.catatan );

        btnKirimCatatan = (Button)findViewById( R.id.kirimCatatan );

        btnKirimCatatan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog( TambahCatatan.this );

                if (!isEmpty( txtNamaGuru.getText().toString() )&& !isEmpty( edtNama.getText().toString() )&& !isEmpty( edtCatatan.getText().toString() ))
                {
                    submitModelCatatan( new ModelCatatan( txtNamaGuru.getText().toString(), edtNama.getText().toString(), edtCatatan.getText().toString()) );
                    Toast.makeText( TambahCatatan.this, "Berhasil Menambah Catatan", Toast.LENGTH_SHORT ).show();
                    finish();
                }else {
                    Toast.makeText( TambahCatatan.this, "Data Harus Diisi", Toast.LENGTH_SHORT ).show();
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

    public void submitModelCatatan(ModelCatatan modelCatatan) {
        catatan.child("Catatan").push().setValue(modelCatatan).addOnSuccessListener(this, new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {
                txtNamaGuru.setText("");
                edtNama.setText("");
                edtCatatan.setText("");
            }
        });
    }
}

