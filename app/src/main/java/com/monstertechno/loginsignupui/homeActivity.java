package com.monstertechno.loginsignupui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class homeActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private expandablelist listdataadapter;
    Button showlocation;
    String tasktext,traveltext;
    TextView textView,textView1,tickettx,tickettx1;
    private  static  final int requestloation =1;
    LocationManager locationManager;
    String lattitude,longitude;
    int boolb1=0, boolb2=0;

    private  final long Min_time =1000;
    private final long Min_distance=5;
    public  String DET;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    public int currentimages;
    public Button yes,no;
    public DatabaseReference myRef,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11;
    public FirebaseDatabase database;
    public  int variable_no,variable_yes;
    int[] images={R.drawable.beforetravel,R.drawable.duringtravelliing,R.drawable.beforetask,R.drawable.duringtask};
    Button starttravel,starttask;
    ImageView starttravelimge,starttaskimage;
    public static FragmentManager fm;
//    FragmentTransaction ft;
public static  FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        starttaskfrag m11 = new starttaskfrag();
//        fm=getSupportFragmentManager();
//        ft=fm.beginTransaction();
//        ft.add(R.id.fragment_container,m11);
//
//        ft.commit();
        ActivityCompat.requestPermissions(homeActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},requestloation);

        ////////////////////////////////////
        tickettx=findViewById(R.id.tx);
        tickettx1=findViewById(R.id.de);
        textView=findViewById(R.id.tx1);
        textView1=findViewById(R.id.tx2);
        Intent i=getIntent();
        String ticketno1= i.getStringExtra("ticket no");
//        String ticketno1= i.getStringExtra("ticket no");
         DET= i.getStringExtra("details");
         Log.v("ali","det " +DET);
        tickettx.setText(ticketno1);
        tickettx1.setText(DET);
        listView =  findViewById(R.id.exp);
        init();
        listdataadapter= new expandablelist(this,listDataHeader,listHash);
        listView.setAdapter(listdataadapter);
        onstarttravelclick();
        onstarttaskclick();

//        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
//        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,PackageManager.PERMISSION_GRANTED);

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
        androidstudio.add("Muhammad Fasial" );
        androidstudio.add("Director Customer Support");
        androidstudio.add("muhammad.faisal@abc.com");
        androidstudio.add("+92-300-745-4342");
        List<String> xamarin =new ArrayList<>();
        xamarin.add("EME NUST ");
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
//        listHash.put(listDataHeader.get(3),ump);
        /////////////////


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


                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                starttaskfrag m23 = new starttaskfrag();
//currentimages=currentimages%images.length;
                // starttravelimge.setImageResource(images[currentimages]);
                starttravelimge.setImageResource(images[1]);
                traveltext= starttravel.getText().toString();
                if(traveltext.compareTo("Start Travel")==0 ) {

                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    a1 = myRef.child("start longITUDE");
                    a1.setValue(longitude);
                    a2 = myRef.child("start lattitude");
                    a2.setValue(lattitude);
                    String name = "starttravel";
//                    FragmentTransaction ft;
//                    fm = getSupportFragmentManager();
//                    ft = fm.beginTransaction();
                    Bundle b1 = new Bundle();
//                    starttaskfrag m23 = new starttaskfrag();
                    b1.putString("name", name);
//                    b1.putString("pwd",pwd);
                    m23.setArguments(b1);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.add(R.id.fragment_container, m23);
                    ft.commit();

                    //////////////////////
//                fm=getSupportFragmentManager();
////
//                ft=fm.beginTransaction();
//                starttaskfrag m13 = new starttaskfrag();
//                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.HomeFragment));
//                starttaskfra.t1.setText("you are travelling");
//                textView1.setBackgroundColor("@co");
//                Bundle b1 = new Bundle();
//                starttaskfrag m13= new starttaskfrag();
//                b1.putString("name",name1);
//                m13.setArguments(b1);
//                ft.add(R.id.fragment_container,m13);
////
//                ft.commit();
//                starttaskfrag starttaskfra = new starttaskfrag();
//                fm=getSupportFragmentManager();
//
//                ft=fm.beginTransaction();
////                starttaskfra.t1.setText("your are travelling");
//                ft.add(R.id.fragment_container,starttaskfra);

//                ft.commit();
                    starttravel.setText("end travel");
                    boolb1=1;

//                starttravel.setVisibility(v.GONE);
                }
                else if (traveltext.compareTo("end travel")==0 )
                {
//
//                    Fragment trans = SupportFragmentManager.BeginTransaction();
//                    trans.remove(m23);
//                    trans.AddToBackStack(null);
//                    trans.Commit();
//                    popBackStack(getSupportFragmentManager());
//                {  getFragmentManager().beginTransaction().remove(m23).commit();
//                    getSupportFragmentManager().popBackStack();
//                    findNavController().popBackStack()
//                    ft.remove(m23);
//                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                    ft.commit();
                    locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                    if (!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER))) {

                        ongps();
                    } else {
                        getlocation();
                    }
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference();
                    a8 = myRef.child("end longITUDE");
                    a8.setValue(longitude);
                    a10 = myRef.child(" end lattitude");
                    a10.setValue(lattitude);
//                    starttaskfrag m23 = new starttaskfrag();
                    boolb1=0;
                }

            }
        });

    }

    public void onstarttaskclick()
    {

//        View view;

        starttaskimage=findViewById(R.id.image_task);
        starttask=findViewById(R.id.start_task);
//         tasktext= starttask.getText().toString();
        starttask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasktext= starttask.getText().toString();
                if(tasktext.compareTo("Start Task")==0) {
                    String name = "starttime";
                    currentimages++;
//currentimages=currentimages%images.length;
                    // starttravelimge.setImageResource(images[currentimages]);
                    starttaskimage.setImageResource(images[3]);
//                    startcountingtraveltime();
// starttaskfrag       yourFragment = (starttaskfrag) getSupportFragmentManager()
//                            .findFragmentById(R.id.yourFragment);
//                    String name ="starttime"
                    FragmentTransaction ft;
                    fm=getSupportFragmentManager();
                    ft=fm.beginTransaction();
                    Bundle b1 = new Bundle();
                    starttaskfrag m13= new starttaskfrag();

                    b1.putString("name",name);
//                    b1.putString("pwd",pwd);
                    m13.setArguments(b1);
                    ft.add(R.id.fragment_container,m13);
                    ft.commit();
//
//                    Log.d("dontknowitwillowrk", yourFragment.t1
//                            .getText().toString());
//                    yourFragment.t1.setText("hello mister how do you do");
//                    ft = fm.beginTransaction();
//                starttaskfrag m13 = new starttaskfrag();
//                getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.HomeFragment));
//                starttaskfra.t1.setText("you are travelling");
//                textView1.setBackgroundColor("@co");
//                    Bundle b1 = new Bundle();
//                    starttaskfrag m13 = new starttaskfrag();
//                    b1.putString("name", name);
//                    m13.setArguments(b1);

                    starttask.setText("end task");

                }
                else if (tasktext.compareTo("end task")==0)
                {
                    final Dialog dialog = new Dialog(homeActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.activity_start_travel);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.CENTER;
                    dialog.getWindow().setAttributes(lp);
                    yes  =dialog.findViewById(R.id.finishyes);
                    no =  dialog.findViewById(R.id.finishno);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();
                            a6=myRef.child("task completed");
                            a7=a6.child("yes");
                            a7.setValue("Task is   completed");
//         variable_yes=1;
//        a4=myRef.child("yesbutton");
//        a4.setValue(variable_yes);

                            Toast.makeText(homeActivity.this,"task completed",Toast.LENGTH_SHORT).show();;

                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();
                            a6=myRef.child("task completed");
//                            a4=myRef.child("task completed");
                            a5=a6.child("No");
                            a5.setValue("Task is  not completed");
//                            dialog.variable_no=0;
//                             a3=myRef.child("Nobutton");
////                             a3.setValue(variable_no);

                            Toast.makeText(homeActivity.this,"task is not completed",Toast.LENGTH_SHORT).show();;

                        }
                    });
                    Button submit=dialog.findViewById(R.id.subbmitbutton);
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText commit=dialog.findViewById(R.id.committext);
                            String C= commit.getText().toString();
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference();
                            a3=myRef.child("committext");
                            a3.setValue(C);
//                            a3.removeValue();
                            commit.setText(" ");
                        }
                    });

                    ImageView cancel=dialog.findViewById(R.id.canceldiaglouge);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });


                    dialog.show();

                    Toast.makeText(homeActivity.this,"end task",Toast.LENGTH_SHORT).show();;
                }

//                FragmentTransaction ft;
//                fm=getSupportFragmentManager();
//                ft=fm.beginTransaction();
//                Bundle b1 = new Bundle();
////                starttaskfrag m13= new starttaskfrag();
////                b1.putString("name",name);
//
//                m13.setArguments(b1);
//                ft.add(R.id.fragment_container,m13);
//                ft.commit();
//                locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
//                if(!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)))
//                {
//
//                    ongps();
//                }
//                else
//                {getlocation();}


////////////////////////////////////////////////
//                locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
//                if(!(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)))
//                {
//
//                    ongps();
//                }
//                else
//                {getlocation();}

//                              locationListener=new LocationListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.DONUT)
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        try {
//                            latLng=new LatLng(location.getLatitude(),location.getLongitude());
////                            Geocoder geocoder = new Geocoder(getApplicationContext());
//                            double mylattitude=location.getLatitude();
//                            TextView lattitudetxt = findViewById(R.id.tx1);
//                            lattitudetxt.setText(("lattitude is " + mylattitude));
//                            double mylongitude = location.getLongitude();
//                            TextView longitutudetext = findViewById(R.id.tx2);
//                            longitutudetext.setText("longitude is " +mylongitude);
//                            Log.v("ali" , "value shhh " + mylattitude);
//                            Log.v("ali" , "value" + mylongitude);
//                            List<Address> addresses= geocoder.getFromLocation(mylattitude,mylongitude,1);
//           String str= addresses.get(0).getCountryName() + " ";
//           str += addresses.get(0).getLocality() + " ";
//           str += addresses.get(0).getSubLocality() + " ";
//           str += addresses.get(0).getThoroughfare() + " ";
//                            String     str = addresses.get(0).getAddressLine(0) + " ";

//                            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
//                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                // send msg
//            String phonenumber="923320903739";
//            String mylattitude=String.valueOf(location.getAltitude());
//            String mylongitude=String.valueOf(location.getLongitude());
//            String message="lattitude =" + mylattitude + "longitude = " + mylongitude;
//            SmsManager smsManager= SmsManager.getDefault();
//            smsManager.sendTextMessage(null,
//                    phonenumber,message,null,null);
//            Log.v(TAG, "index=" + mylattitude);
//            Log.d(MapsActivity.this,"lattitude" + fi.get);
                ////
//
//            mDatabase = FirebaseDatabase.getInstance().getReference();
//            siteRef = mDatabase.child("sites");
//            siteRef.setValue("a11");
//            FirebaseDatabase.getInstance().getReference().setValue(fi).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(MapsActivity.this,"location saved",Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    { Toast.makeText(MapsActivity.this,"location not saved",Toast.LENGTH_SHORT).show();}
//
//                }
//            });


            }

//
//                        catch (Exception e)
//                        {e.printStackTrace();}
//                    }
//
//                    @Override
//                    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String provider) {
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String provider) {
//
//                    }
//                };
//                locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
//                try{locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,Min_time,Min_distance,locationListener);
//                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,Min_time,Min_distance,locationListener);
//                }
//                catch (SecurityException
//                        e)
//                {e.printStackTrace();}
//
//            }










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

                //builder.finish();
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
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");
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
//            latLng=new LatLng(locationgps.getLatitude(),locationgps.getLongitude());
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
                //                latLng=new LatLng(locationpassive.getLatitude(),locationpassive.getLongitude());
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
