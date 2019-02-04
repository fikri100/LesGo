package com.example.asus.lesgo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.asus.lesgo.model.Guru;
import com.example.asus.lesgo.model.ModelTransaksi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class Transaksi extends AppCompatActivity {

    Button btnPesan;
    TextView edtNamaGuru, edtMapel, edtEmail, edtHarga, edtKeterangan, edtNama, edtNoHp, edtTransaksiId;
    EditText  edtJam, edtTanggal, edtAlamat;
    ImageView image;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference transaksi;
    long idt=0;
    DatabaseReference reff;
    Guru currentGuru;
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_transaksi );

        getSupportActionBar().setTitle( "Transaksi" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        btnPesan = (Button)findViewById( R.id.pesan );

        image = (ImageView)findViewById( R.id.imageProfil );
        Intent imageIntent = getIntent();
        String gettingImage = imageIntent.getStringExtra( "imageUri" );
        Picasso.get().load( gettingImage ).into( image );

        edtNamaGuru = (TextView) findViewById( R.id.namaGuru );
        Intent intent = getIntent();
        String namaGuru = intent.getStringExtra( "nama" );
        edtNamaGuru.setText( namaGuru );
        edtMapel = (TextView) findViewById( R.id.namaMapel );
        Intent mapel = getIntent();
        String namaMapel = mapel.getStringExtra( "mapel" );
        edtMapel.setText( namaMapel );
        edtEmail = (TextView) findViewById( R.id.namaEmail );
        Intent email = getIntent();
        String namaEmail = email.getStringExtra( "email" );
        edtEmail.setText( namaEmail );
        edtHarga = (TextView) findViewById( R.id.namaHarga );
        Intent harga = getIntent();
        String hargaSesi = harga.getStringExtra( "harga" );
        edtHarga.setText( hargaSesi );
        edtNama = (TextView) findViewById( R.id.nama );
        Intent murid = getIntent();
        String nama = murid.getStringExtra( "namaMurid" );
        edtNama.setText( nama );
        edtAlamat = (EditText)findViewById( R.id.alamat );
        edtNoHp = (TextView) findViewById( R.id.noHp );
        Intent nohp = getIntent();
        String no = nohp.getStringExtra( "noHp" );
        edtNoHp.setText( no );
        edtKeterangan = (TextView) findViewById( R.id.keterangan );
        edtTransaksiId = (TextView) findViewById( R.id.transaksiId );
        edtJam = (EditText) findViewById( R.id.jam );
        edtJam.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Transaksi.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edtJam.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        } );

        edtTanggal = (EditText)findViewById( R.id.tanggal );
        edtTanggal.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Transaksi.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edtTanggal.setText(year + "-"
                                        + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        } );

        transaksi =FirebaseDatabase.getInstance().getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        reff =FirebaseDatabase.getInstance().getReference().child( "Transaksi" );
        reff.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    idt = (dataSnapshot.getChildrenCount());
                    edtTransaksiId.setText( String.valueOf( idt+1 ) );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        btnPesan.setOnClickListener( new View.OnClickListener() {
            //String guruLes = edtNamaGuru.getText().toString();
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog( Transaksi.this );

                if (!isEmpty( edtNama.getText().toString() ) &&!isEmpty( edtJam.getText().toString() )
                        &&!isEmpty( edtTanggal.getText().toString() ) &&!isEmpty( edtAlamat.getText().toString() ))
                {
                    submitModelTransaksi( new ModelTransaksi( edtTransaksiId.getText().toString() , edtNamaGuru.getText().toString(), edtMapel.getText().toString(),
                            edtEmail.getText().toString(), edtHarga.getText().toString(), edtNama.getText().toString(), edtJam.getText().toString(),
                            edtTanggal.getText().toString(), edtAlamat.getText().toString(), edtNoHp.getText().toString(), edtKeterangan.getText().toString() ) );
                    Toast.makeText( Transaksi.this, "Transaksi Berhasil", Toast.LENGTH_SHORT ).show();
                    finish();
                }else {
                    Toast.makeText( Transaksi.this, "Data Harus Diisi", Toast.LENGTH_SHORT ).show();
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

    public void submitModelTransaksi(ModelTransaksi modelTransaksi) {
        reff.child(String.valueOf( idt+1 )).setValue(modelTransaksi).addOnSuccessListener(this, new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {
                edtNama.setText("");
                edtJam.setText("");
                edtTanggal.setText("");
                edtAlamat.setText("");
                edtNoHp.setText("");
                edtKeterangan.setText( "" );
            }
        });
    }
}
