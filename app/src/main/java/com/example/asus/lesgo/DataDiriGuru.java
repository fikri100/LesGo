package com.example.asus.lesgo;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.model.ModelDataDiriGuru;
import com.example.asus.lesgo.model.ModelDataDiriGuru;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataDiriGuru extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference guru;
    DatabaseReference id;
    private FirebaseAuth auth;
    private StorageTask uploadTask;

    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference reference;

    ImageView imgView;
    private Uri imageUri;

    private final int PICK_IMAGE_REQUEST = 1;


    EditText edtNama, edtUsername, edtPassword, edtMapel, edtEmail,edtAlamat , edtHarga;
    Button btnUpdate;
    Button btnCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_data_diri_guru );

        getSupportActionBar().setTitle( "Data Diri" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        guru = database.getReference("Guru");


        btnCamera = (Button) findViewById( R.id.camera );

        edtNama=(EditText)findViewById( R.id.nama );
        edtNama.setText(CommonGuru.currentGuru.getNama() );
        edtUsername=(EditText)findViewById( R.id.musername);
        edtUsername.setText(CommonGuru.currentGuru.getMusername());
        edtPassword=(EditText)findViewById( R.id.password );
        edtPassword.setText(CommonGuru.currentGuru.getPassword());
        edtMapel=(EditText)findViewById( R.id.mapel );
        edtMapel.setText(CommonGuru.currentGuru.getMapel());
        edtEmail=(EditText)findViewById( R.id.email );
        edtEmail.setText(CommonGuru.currentGuru.getEmail());
        edtAlamat=(EditText)findViewById( R.id.alamat );
        edtAlamat.setText(CommonGuru.currentGuru.getAlamat());
        edtHarga=(EditText)findViewById( R.id.harga );
        edtHarga.setText(CommonGuru.currentGuru.getHarga());

        imgView = (ImageView)findViewById( R.id.imageProfil );

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnUpdate = (Button)findViewById( R.id.edit );

        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        } );

        reference = FirebaseDatabase.getInstance().getReference("Guru").child(edtUsername.getText().toString());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelDataDiriGuru guru = dataSnapshot.getValue(ModelDataDiriGuru.class);
                    Glide.with(getApplicationContext()).load(guru.getImageUri()).into(imgView);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnCamera.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        } );


    }

    private void chooseImage() {
        Intent photoPickerIntent = new Intent( Intent.ACTION_PICK  );
        photoPickerIntent.setType( "image/*" );
        startActivityForResult( photoPickerIntent, PICK_IMAGE_REQUEST );
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(){

        if (imageUri != null){

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final  StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        String mUri = downloadUri.toString();

                        reference = FirebaseDatabase.getInstance().getReference("Guru").child(edtUsername.getText().toString());
                        HashMap<String, Object> map = new HashMap<>();
                        map.put( "imageUri", ""+mUri );
                        map.put("alamat", edtAlamat.getText().toString());
                        map.put("email", edtEmail.getText().toString());
                        map.put("harga", edtHarga.getText().toString());
                        map.put("mapel", edtMapel.getText().toString());
                        map.put("musername", edtUsername.getText().toString());
                        map.put("nama", edtNama.getText().toString());
                        map.put("password", edtPassword.getText().toString());
                        reference.updateChildren(map);

                        progressDialog.setMessage("Proses Menyimpan Data");

                    } else {
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Pilih Gambar Baru", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK) {
            try {
                imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(DataDiriGuru.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(DataDiriGuru.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}

