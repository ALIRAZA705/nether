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
//                    ArrayList<model> modl1=new ArrayList<>();
                model m = new model();
//                    model    m1 = dataSnapshot.getValue(model.class);
                String location = dataSnapshot.child("location").getValue().toString();
                String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
                String details = dataSnapshot.child("details").getValue().toString();
                String contactperson = dataSnapshot.child("contact_person").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
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
//                Log.v("ALI", "family value " +modl1.get(0).getTicket_no());
//                firebasecallback.oncallback(modl1);
//                firebasecallback.oncallback(m);
//                readdata(new firebasecallback() {
//                    public void oncallback(model m) {
//                modl1.add(m);
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
//      readdata(new firebasecallback() {
//          @Override
//          public void oncallback(ArrayList<model> list) {
//              Log.v("aliraza" , "value oncalback" + list.get(0).getTicket_no());
////              programinglist.setAdapter(new myadapter(this,  list));
//          }
//      });
//        programinglist.setAdapter(new myadapter(this,  list));

//        b1= FirebaseDatabase.getInstance().getReference();
//        b2=b1.child("tasks");
//        b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////                for(DataSnapshot child:dataSnapshot.getChildren()) {
////                    ArrayList<model> modl1=new ArrayList<>();
//                model m = new model();
////                    model    m1 = dataSnapshot.getValue(model.class);
//                String location = dataSnapshot.child("location").getValue().toString();
//                String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
//                Log.v("ALI", "family value " +location);
//                Log.v("ALI", "family value " + tickeno);
//                m.setLocation(location);
//                Log.v("ALI", "location " +m.getLocation());
//                m.setTicket_no(tickeno);
//                Log.v("ALI", "ticketno " +m.getLocation());
//                modl1.add(m);
////modl1.getClass();
//                Log.v("ALI", "array list ith inisafter fun " +modl1.size());
////                        programinglist.setAdapter(new myadapter(this, modl1));
//
////                }
//            }
//
//            //
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
////                                programinglist.setAdapter(new myadapter(this, modl1));
//        Log.v("ALI", "family value " +modl1.toString());


////        adapter=new myadapter(this, modl1);
//        programinglist.setAdapter(new myadapter(this, modl1));
//        Log.v("ALI", "array list isafter fun " +modl1);
//        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        String userid=user.getUid();
//        Log.v("family","family value " + riused);
//        b2.child("userid").setValue(userid);

//                (new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot child: dataSnapshot.getChildren()) {
//                    model value = dataSnapshot.getValue(model.class);
//                    System.out.println(child.getKey());
//                    Log.v("ali", "family value " + value.getTicket_no());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        {
//            @Override
//
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot datas: dataSnapshot.getChildren()) {
////                    for(DataSnapshot data:dataSnapshot.getChildren())
////                    String familyname=dataSnapshot.child("location").getValue().toString();
//
//                    model familyname = dataSnapshot.getValue(model.class);
//
//
//                    Log.v("ali", "family value " + familyname.getTicket_no());
//                    Log.v("ali1", "family value " + MainActivity.email);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//Query query=b2.orderByChild("email").equalTo(MainActivity.email);
//query.addValueEventListener(new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        for(DataSnapshot child: dataSnapshot.getChildren()) {
//            ArrayList<model> modl1 = new ArrayList<>();
////            model m = new model();
//
//            model m1 = dataSnapshot.getValue(model.class);
//
//            modl1.add(m1);
//        }
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//    }
//});


//b2.addValueEventListener(new ValueEventListener() {
//    @Override
//    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        String post = dataSnapshot.getValue(String.class);
//        Log.v("aliraza" , "value is" + post);
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
////        Log.v("aliraza" , "value is" + post);
//    }
//});
//    }
    }
//
//    private ArrayList<model> getMYlist() {
////        ArrayList<model> modl1=new ArrayList<>();
//        b1= FirebaseDatabase.getInstance().getReference();
//        b2=b1.child("tasks");
//        b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////                for(DataSnapshot child:dataSnapshot.getChildren()) {
////                    ArrayList<model> modl1=new ArrayList<>();
//                    model m = new model();
////                    model    m1 = dataSnapshot.getValue(model.class);
//                    String location = dataSnapshot.child("location").getValue().toString();
//                    String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
//                    Log.v("ALI", "family value " +location);
//                    Log.v("ALI", "family value " + tickeno);
//                    m.setLocation(location);
//                    m.setTicket_no(tickeno);
//                    modl1.add(m);
////                }
//           }
////
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        model m=new model();
//        m.setLocation("location");
//        m.setTicket_no("ticket #2358");
//        modl1.add(m);
////
//        m=new model();
//        m.setLocation("netherland2");
//        m.setTicket_no("ticket #2359");
//        modl1.add(m);
////
////        m=new model();
////        m.setLocation("netherland3");
////        m.setTicket_no("ticket #2360");
////        modl1.add(m);
//        m=new model();
//        m.setLocation("netherland4");
//        m.setTicket_no("ticket #2361");
//        modl1.add(m);
////        m=new model();
////
////        m.setLocation("netherland5");
////        m.setTicket_no("ticket #2362");
////        modl1.add(m);
//        return modl1;










//        model m = new model();
//        b1 = FirebaseDatabase.getInstance().getReference();
//        b2 = b1.child("tasks");
//        final  ArrayList<model>  modl1 = new ArrayList<>();
//       b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
//           @Override
//           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//               model    m = dataSnapshot.getValue(model.class);
//               Log.v("ali", "value " + m.getLocation());
//                    Log.v("ali ticke", "value  ticket " + m.getTicket_no());
//                    m.setLocation(m.getLocation());
//                    m.setTicket_no( m.getTicket_no());
//                    modl1.add(m);
//
//           }
//
//           @Override
//           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//           }
//
//           @Override
//           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//           }
//
//           @Override
//           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//           }
//       });
//        Log.v("ali", "value " + MainActivity.email);
//        Log.v("ali5", "value " +query);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot child : dataSnapshot.getChildren()) {
//
////            model m = new model();
//
//                    model    m = dataSnapshot.getValue(model.class);
//                    Log.v("ali", "value " + m.getLocation());
//                    Log.v("ali ticke", "value  ticket " + m.getTicket_no());
//                    m.setLocation(m.getLocation());
//                    m.setTicket_no( m.getTicket_no());
//                    modl1.add(m);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        return modl1;

//    }
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
