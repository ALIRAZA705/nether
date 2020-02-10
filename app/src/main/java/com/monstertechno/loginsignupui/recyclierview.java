package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class recyclierview extends AppCompatActivity {
//  final RecyclerView programinglist;
public  RecyclerView programinglist;
    private ExpandableListView listView;
    private expandablelist ebtn;
    DatabaseReference b1, b2, b3, b4;

    private List<String> ls;
    private HashMap<String, List<String>> hashMap;
    public ArrayList<model> modl1=new ArrayList();
    private myadapter adapter;
//    RecyclerView.Adapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclierview);
          programinglist = findViewById(R.id.my_recycler_view);
        programinglist.setLayoutManager(new LinearLayoutManager(this));
        adapter=new myadapter(this,  modl1);
        programinglist.setAdapter(adapter);
        b1= FirebaseDatabase.getInstance().getReference();
        b2=b1.child("tasks");
        b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot child:dataSnapshot.getChildren()) {
//                       final    List<String> key;
//                    ArrayList<model> modl1=new ArrayList<>();
                model m = new model();
//                    model    m1 = dataSnapshot.getValue(model.class);
              final String  key  =dataSnapshot.getKey();
                m.setKey(key);
                Log.v("ALI", "family key " +key);
                String location = dataSnapshot.child("location").getValue().toString();
                String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
                String details = dataSnapshot.child("details").getValue().toString();
//                String status = dataSnapshot.child("status").getValue().toString();
//                Log.v("ALI", "family value " +status);
//                m.setAddress(status);
                String contactperson = dataSnapshot.child("contact_person").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
                String rstatus = dataSnapshot.child("status").getValue().toString();
                Log.v("ALI rec", "status rec " +rstatus);
                m.setStatus(rstatus);
                Log.v("ALI", "family value " +location);
                Log.v("ALI", "family value " + tickeno);
                m.setAddress(address);
                Log.v("ALI", "address " +m.getAddress());
                m.setContact_person(contactperson);
                Log.v("ALI", "contact " +m.getContact_person());
                m.setDetails(details);
                Log.v("ALI", "details " +m.getDetails());
                m.setLocation(location);
                Log.v("ALI", "location " +m.getLocation());
                m.setTicket_no(tickeno);

//                modl1.add(m);
                adapter.addItem(m);

            }

            //
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private  void readdata(final firebasecallback firebasecallback)
    {
        b1= FirebaseDatabase.getInstance().getReference();
        b2=b1.child("tasks");
        b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot child:dataSnapshot.getChildren()) {
//                    ArrayList<model> modl1=new ArrayList<>();
                model m = new model();
//                    model    m1 = dataSnapshot.getValue(model.class);
                String location = dataSnapshot.child("location").getValue().toString();
                String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
                Log.v("ALI", "family value " +location);
                Log.v("ALI", "family value " + tickeno);
                m.setLocation(location);
                Log.v("ALI", "location " +m.getLocation());
                m.setTicket_no(tickeno);
//                modl1.add(m);
//                Log.v("ALI", "family value " +modl1.get(0).getTicket_no());
//                firebasecallback.oncallback(modl1);
//                firebasecallback.oncallback(m);
//                readdata(new firebasecallback() {
//                    public void oncallback(model m) {
                        modl1.add(m);
                        adapter.addItem(m);
//                        Log.v("aliraza" , "value oncalback" + list.get(0).getTicket_no());

//                    }
//                });
//                }
            }

            //
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        adapter=new myadapter(this, modl1);
        programinglist.setAdapter(new myadapter(this,  modl1));
//        Log.v("ALI", "family value " +modl1.get(0).getTicket_no());



    }
    private  interface firebasecallback
    {
        void oncallback( ArrayList<model> list);
    }

        }
