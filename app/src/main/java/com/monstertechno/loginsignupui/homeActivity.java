package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class homeActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private expandablelist listdataadapter;
    Button showlocation;
    String tasktext,traveltext;
    TextView textView,textView1,tickettx,tickettx1,status;
    private  static  final int requestloation =1;
    LocationManager locationManager;
    String lattitude,longitude;
    int boolb1=0, boolb2=0;
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
    private  final long Min_time =1000;
    private final long Min_distance=5;
    public  String DET,contact,add,homesat;
    public  String key;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    public int currentimages;
    public Button yes,no;
    public DatabaseReference myRef,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15;
    public FirebaseDatabase database;
    public  int variable_no,variable_yes;
    int[] images={R.drawable.beforetravel,R.drawable.duringtravelliing,R.drawable.beforetask,R.drawable.duringtask};
    Button starttravel,starttask;
    Fragment f;
    ImageView starttravelimge,starttaskimage;
    public static FragmentManager fm;
//    FragmentTransaction ft;
public static  FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        ActivityCompat.requestPermissions(homeActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},requestloation);

        status=findViewById(R.id.status);
        tickettx=findViewById(R.id.tx);
        textView=findViewById(R.id.tx1);
        textView1=findViewById(R.id.tx2);
        Intent i=getIntent();
        String ticketno1= i.getStringExtra("ticket no");

         DET= i.getStringExtra("details");
         Log.v("ali","det " +DET);
        contact= i.getStringExtra("contact");
        Log.v("ali","contact " +contact);
        add= i.getStringExtra("address");
        Log.v("ali","contact " +add);
        tickettx.setText(ticketno1);
        key= i.getStringExtra("sitekey");
        Log.v("ali","det " +key);
        homesat= i.getStringExtra("sat");
        Log.v("ali status","homesat " +homesat);
        status.setText(homesat);
//        a1= FirebaseDatabase.getInstance().getReference();
//        a2=a1.child("tasks");
//        Query query=a2.orderByChild("ticket_no").equalTo(ticketno1);




        listView =  findViewById(R.id.exp);
        init();
        listdataadapter= new expandablelist(this,listDataHeader,listHash);
        listView.setAdapter(listdataadapter);
        onstarttravelclick();
        onstarttaskclick();
        ///////////////////////////////////
        Button upload= findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeActivity.this,"button is clicked",Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(homeActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.uploadxml);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                    lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialog.getWindow().setAttributes(lp);
//        yes = dialog.findViewById(R.id.buttonChoose);
//        no = dialog.findViewById(R.id.finishno);
                storageReference = FirebaseStorage.getInstance().getReference();
                //getting views from layout
                buttonChoose =  dialog.findViewById(R.id.buttonChoose);
                buttonUpload =  dialog.findViewById(R.id.buttonUpload);
                buttonpdfupload =  dialog.findViewById(R.id.pdfbutton);

                imageView =  dialog.findViewById(R.id.imageView);

                //attaching listener
                buttonChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


                    }
                });
//                buttonUpload.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        uploadFile();
//                    }
//                });
                buttonpdfupload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("application/pdf/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_REQUEST);
                        uploadFile();
                    }
                });
                ImageView cancel = dialog.findViewById(R.id.canceldiaglouge1);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }

        });




    }
    private  void init(){
        listDataHeader=new ArrayList<>();
        listHash=new HashMap<>();
        listDataHeader.add("Details");
        listDataHeader.add("Contact Person");
        listDataHeader.add("Address");
        //  listDataHeader.add("UMP");

        List<String> emdtdev =new ArrayList<>();

        emdtdev.add(DET);
        Log.v("ali","det " +emdtdev.get(0));

        List<String> androidstudio =new ArrayList<>();
//        androidstudio.add("Muhammad Fasial" );
//        androidstudio.add("Director Customer Support");
//        androidstudio.add("muhammad.faisal@abc.com");
//        androidstudio.add("+92-300-745-4342");
        androidstudio.add(contact);
        List<String> xamarin =new ArrayList<>();
//        xamarin.add("EME NUST ");
        xamarin.add(add);
//        xamarin.add("abc2 whatsapp");
//        xamarin.add("abc2 eme");
////        xamarin.add("abc2 eaaa");
//        List<String> ump =new ArrayList<>();
//        ump.add("abc2 expand list view");
//        ump.add("abc2 whatsapp");
//        ump.add("abc2 eme");
//        ump.add("abc2 eaaa");
//        listHash.put(listdataadapter.get(0),abc1);
//        listHash.put(listdataadapter.get(1),abc2);
        //hashMap.put(ls.get(0),abc3);
        listHash.put(listDataHeader.get(0),emdtdev);
        listHash.put(listDataHeader.get(1),androidstudio);
        listHash.put(listDataHeader.get(2),xamarin);
        /////////





    }
    private void uploadFile() {


        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = storageReference.child("images/pic.jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            String Uri =taskSnapshot.getUploadSessionUri().toString();
                            DatabaseReference a1=FirebaseDatabase.getInstance().getReference();
                            DatabaseReference a2  =   a1.child("tasks").child(key).child("doc");
//a1.setValue(Uri);
                            String uploadId = a2.push().getKey();
                            a2.child(uploadId).setValue(Uri);
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
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            uploadFile();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                imageView.setImageBitmap(bitmap);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            filePath = data.getData();
            uploadFile();
//            uploadFile();
        }
    }
    public void onstarttravelclick()
    {
        starttravelimge=findViewById(R.id.image_travel);
        starttravel=findViewById(R.id.start_travel);
        starttravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 ="You are travelling";
                currentimages++;
                starttaskfrag m23 = new starttaskfrag();


                starttravelimge.setImageResource(images[1]);
                traveltext= starttravel.getText().toString();
                if(traveltext.compareTo("Start Travel")==0 ) {
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();

                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); //start travel location

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    a1 = myRef.child("tasks").child(key).child("start travel longITUDE");
                    a1.setValue(longitude);
                    a2 = myRef.child("tasks").child(key).child("start travel  lattitude");
                    a2.setValue(lattitude);
                    String name = "starttravel";

                    Bundle b1 = new Bundle();
                    b1.putString("name", name);
                    m23.setArguments(b1);
                    ft.addToBackStack(null);
                    ft.add(R.id.fragment_container, m23);
                    ft.commit();


                    starttravel.setText("end travel");
                    boolb1=1;

                }
                else if (traveltext.compareTo("end travel")==0 )
                {                starttravelimge.setImageResource(images[0]);

                    if(f==null) {
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        fm.popBackStack();
                        ft.remove(m23).commit();
                    }
//




                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); // location end travel

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    a8 = myRef.child("tasks").child(key).child("end travel longITUDE");
                    a8.setValue(longitude);
                    a10 = myRef.child("tasks").child(key).child(" end travel lattitude");
                    a10.setValue(lattitude);
                    boolb1=0;
                    starttravel.setVisibility(v.INVISIBLE);

                }

            }
        });

    }

    public void onstarttaskclick()
    {


        starttaskimage=findViewById(R.id.image_task);
        starttask=findViewById(R.id.start_task);

        starttask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttaskfrag m13 = new starttaskfrag();
                FragmentTransaction ft;
                tasktext = starttask.getText().toString();
                if (tasktext.compareTo("Start Task") == 0) {
                    String name = "starttime";
                    currentimages++;

                    starttaskimage.setImageResource(images[3]);
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); ///location start task

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();

                    a11 = myRef.child("tasks").child(key).child("start task longITUDE");
                    a11.setValue(longitude);
                    a12 = myRef.child("tasks").child(key).child(" start task lattitude");
                    a12.setValue(lattitude);






                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    Bundle b1 = new Bundle();

                    b1.putString("name", name);
//                    b1.putString("pwd",pwd);
                    m13.setArguments(b1);
                    ft.addToBackStack(null);
                    ft.add(R.id.fragment_container, m13);
                    ft.commit();


                    starttask.setText("end task");

                } else if (tasktext.compareTo("end task") == 0) {
                    starttaskimage.setImageResource(images[2]);

                    if (f == null) {
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        fm.popBackStack();
                        ft.remove(m13).commit();
                    }
                    final Dialog dialog = new Dialog(homeActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.activity_start_travel);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.CENTER;
                    dialog.getWindow().setAttributes(lp);
                    yes = dialog.findViewById(R.id.finishyes);
                    no = dialog.findViewById(R.id.finishno);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();
                            a6 = myRef.child("tasks").child(key).child("task completed");
//                            a7 = a6.child("yes");
                            a6.setValue("Task is   completed");


                            Toast.makeText(homeActivity.this, "task completed", Toast.LENGTH_SHORT).show();
                            ;

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();
                            a6 = myRef.child("tasks").child(key).child("task completed");
//                            a5 = a6.child("No");
                            a6.setValue("Task is  not completed");


                            Toast.makeText(homeActivity.this, "task is not completed", Toast.LENGTH_SHORT).show();
                            ;

                        }
                    });
                    Button submit = dialog.findViewById(R.id.subbmitbutton);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText commit = dialog.findViewById(R.id.committext);
                            String C = commit.getText().toString();
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();

//                            a3 = myRef.child(key).child("comment");
                            myRef.child("tasks").child(key).child("commint").setValue(C);
                            commit.setText(" ");
                            myRef.child("tasks").child(key).child("status").setValue("done");
                            myRef.child("tasks").child(key).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String homstatus = dataSnapshot.child("status").getValue().toString();
                                    Log.v("ali","det " +homstatus);
                                    status.setText(homstatus);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    });

                    ImageView cancel = dialog.findViewById(R.id.canceldiaglouge);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                    dialog.show();

                    Toast.makeText(homeActivity.this, "end task", Toast.LENGTH_SHORT).show();

                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); ///location end task

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }

                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    a13 = myRef.child("tasks").child(key).child("end task longITUDE");
                    a13.setValue(longitude);
                    a14 = myRef.child("tasks").child(key).child(" end task lattitude");
                    a14.setValue(lattitude);




                    ;
                }




            }




        });

    }
    public void ongps()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("enable gps");
        builder.setMessage("turn on gps ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(homeActivity.this, "positive button", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                Toast.makeText(homeActivity.this, "negative button", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        final   AlertDialog alert = builder.create();

        alert.show();
    }
    public void getlocation()
    {
        if(ActivityCompat.checkSelfPermission(homeActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&  ActivityCompat.checkSelfPermission(homeActivity.this,Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(homeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PackageManager.PERMISSION_GRANTED);
        }
        else
        {

            Location locationgps=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location loationnetwok=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location  locationpassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if(locationgps != null){
                double longi = locationgps.getLatitude();
                double latti = locationgps.getLongitude();
                longitude = String.valueOf(longi);
                lattitude = String.valueOf(latti);

                textView.setText("longitude " + longitude);

                textView1.setText("lattitude " + lattitude);

            }
            else if (loationnetwok != null){
                double longi = loationnetwok.getLatitude();
                double latti = loationnetwok.getLongitude();
                longitude = String.valueOf(longi);
                lattitude = String.valueOf(latti);

                textView.setText("longitude " + longitude);

                textView1.setText("lattitude " + lattitude);

            }
            else if (locationpassive != null){
                double longi = locationpassive.getLatitude();
                double latti = locationpassive.getLongitude();
                longitude = String.valueOf(longi);
                lattitude = String.valueOf(latti);

                textView.setText("longitude " + longitude);

                textView1.setText("lattitude " + lattitude);

            }
            else {
                Toast.makeText(homeActivity.this, "cant acces your location ", Toast.LENGTH_SHORT).show();
            }



        }

    }
    public static void popBackStack(FragmentManager manager){
        FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
        manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
