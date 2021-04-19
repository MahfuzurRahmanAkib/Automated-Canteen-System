package com.example.finalyearproject.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearproject.R;
import com.example.finalyearproject.model.ImageModel;
import com.example.finalyearproject.model.PictureModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Admin_Picture extends AppCompatActivity implements View.OnClickListener {

    private Button chooseButton, saveButton;
    private EditText imageNameET;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Uri imageUri;
    private StorageTask uploadTask;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    private static final int Image_Request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__picture);

        databaseReference = FirebaseDatabase.getInstance().getReference("UploadImage");
        storageReference = FirebaseStorage.getInstance().getReference("UploadImage");

        this.setTitle("Pictures Of Canteen");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chooseButton = findViewById(R.id.chooseImage_Id);
        saveButton = findViewById(R.id.saveImage_Id);
        progressBar = findViewById(R.id.progressbar_Id);
        imageView = findViewById(R.id.imageView_Id);
        imageNameET = findViewById(R.id.imageNameEditText);

        saveButton.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseImage_Id:
                openFileChooser();
                break;

            case R.id.saveImage_Id:
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(Admin_Picture.this, "Uploading is in progress", Toast.LENGTH_SHORT).show();
                } else {
                    saveData();
                }
                break;
        }
    }

    //select IAMge
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Image_Request);
    }

    //image load from gellary and set it to the image View
    //to get this onActivity Result press ctrl + o
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Request && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(imageView);
        }
    }

    //get extention
    public String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    private void saveData() {
        if (imageUri != null) {
            String imageName = imageNameET.getText().toString().trim();

            if (imageName.isEmpty()) {
                imageNameET.setError("Enter Picture Name");
                imageNameET.requestFocus();
                return;
            }

            // Defining the child of storageReference
            StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            reference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Admin_Picture.this, "Image Uploaded Successfully!!", Toast.LENGTH_SHORT).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            PictureModel model = new PictureModel(imageName, downloadUrl.toString());
                            //For Getting backup in DATABASE REFERENCE
                            String uploadID = databaseReference.push().getKey();
                            databaseReference.child(uploadID).setValue(model);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Admin_Picture.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}



