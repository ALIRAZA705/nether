package com.monstertechno.loginsignupui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
///import android.support.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class uploadfiles extends AppCompatActivity implements  View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int PICK_PDF_REQUEST = 235;
    private static final int PICK_DOC_REQUEST = 236;
    //Buttons
    private Button buttonChoose;
    private Button buttonUpload,buttonpdfupload,buttondocupload;

    //ImageView
    private ImageView imageView;
    private StorageReference storageReference;
    //a Uri object to store file path
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfiles);
        storageReference = FirebaseStorage.getInstance().getReference();
        //getting views from layout

        buttonUpload =  findViewById(R.id.buttonUpload);
        buttonpdfupload =  findViewById(R.id.pdfbutton);
        buttondocupload =  findViewById(R.id.docbutton);
        imageView =  findViewById(R.id.imageView);

        //attaching listener

        buttonUpload.setOnClickListener(this);
        buttonpdfupload.setOnClickListener(this);
        buttondocupload.setOnClickListener(this);
    }

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    private void showpdfFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf/*");


        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select pdf"), PICK_PDF_REQUEST);
    }
    private void showdocFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf/*");


        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select doc"), PICK_DOC_REQUEST);
    }



    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
//
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
                uploadFile();
//try {
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            uploadFile();
        }
        else if (requestCode ==PICK_DOC_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            filePath = data.getData();
            uploadFile();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "error in onactivity result  ", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    public void onClick(View view) {
//        //if the clicked button is choose
//        if (view == buttonChoose) {
//            showFileChooser();
//        }
//        //if the clicked button is upload
//        else if (view == buttonUpload) {
//
//        }
//    }

    //    }
    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("uploads").child(filePath.getLastPathSegment());
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast

            Toast.makeText(getApplicationContext(), "error in uploadfiles  ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        //if the clicked button is choose
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {

          if  (view == buttonpdfupload)
            { showpdfFileChooser();}
            else if  (view == buttondocupload)
            { showdocFileChooser();}
            //if the clicked button is upload
            else if (view == buttonUpload) {
//                uploadFile();
                showFileChooser();
            }
        }
//        else {
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 9 && grantResults[0]== PackageManager.PERMISSION_GRANTED )
//        {
//        }
    }

