package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText emailid,password;
    FloatingActionButton btnsignin;
    TextView tvsignup;
    public static  String email,pass;
    DatabaseReference b1, b2, b3, b4;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);
        tvsignup=findViewById(R.id.textView1);
        btnsignin=findViewById(R.id.button2);

//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            FirebaseUser mFirebaseuser= mFirebaseAuth.getCurrentUser();
//
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if((mFirebaseuser)!=null){
//                    Toast.makeText(MainActivity.this,"you are logged in" ,Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(MainActivity.this,recyclierview.class));
//
//
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this,"please log in" ,Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        };
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailid.getText().toString();
                pass=password.getText().toString();

                b1= FirebaseDatabase.getInstance().getReference();
                b2=b1.child("tasks");

//                b2.orderByChild("email").equalTo(MainActivity.email).addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////                for(DataSnapshot child:dataSnapshot.getChildren()) {
////                       final    List<String> key;
////                    ArrayList<model> modl1=new ArrayList<>();
//                        model m = new model();
////                    model    m1 = dataSnapshot.getValue(model.class);
//                        final String  key  =dataSnapshot.getKey();
//                        m.setKey(key);
//                        Log.v("ALI", "family key " +key);
//                        String location = dataSnapshot.child("location").getValue().toString();
//                        String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
//                        String details = dataSnapshot.child("details").getValue().toString();
//                        String contactperson = dataSnapshot.child("contact_person").getValue().toString();
//                        String address = dataSnapshot.child("address").getValue().toString();
//                        Log.v("ALI", "family value " +location);
//                        Log.v("ALI", "family value " + tickeno);
//                        m.setAddress(address);
//                        Log.v("ALI", "address " +m.getAddress());
//                        m.setContact_person(contactperson);
//                        Log.v("ALI", "contact " +m.getContact_person());
//                        m.setDetails(details);
//                        Log.v("ALI", "details " +m.getDetails());
//                        m.setLocation(location);
//                        Log.v("ALI", "location " +m.getLocation());
//                        m.setTicket_no(tickeno);
////                        startActivity(new Intent(MainActivity.this,homeActivity.class));
//                        Intent intent = new Intent(MainActivity.this, recyclierview.class);
//
////                        intent.putExtra("ticket no" ,MainActivity.pass);
////                        intent.putExtra("contact",m.getContact_person());
////                        intent.putExtra("address",m.getAddress());
////                        intent.putExtra("details",m.getDetails());
////                        intent.putExtra("sitekey",m.getKey());
//                        startActivity(intent);
////                modl1.add(m);
////                        adapter.addItem(m);
//
//                    }
//
//                    //
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });





































   if (!(pass.isEmpty() )) {

       b2.orderByChild("ticket_no").equalTo(MainActivity.pass).addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for(DataSnapshot child:dataSnapshot.getChildren()) {
//                       final    List<String> key;
//                    ArrayList<model> modl1=new ArrayList<>();
               model m = new model();
//                    model    m1 = dataSnapshot.getValue(model.class);
               final String key = dataSnapshot.getKey();
               m.setKey(key);
               Log.v("ALI", "family key " + key);
               String location = dataSnapshot.child("location").getValue().toString();
               String tickeno = dataSnapshot.child("ticket_no").getValue().toString();
               String details = dataSnapshot.child("details").getValue().toString();
               String contactperson = dataSnapshot.child("contact_person").getValue().toString();
               String address = dataSnapshot.child("address").getValue().toString();
               String mstatus = dataSnapshot.child("status").getValue().toString();
               Log.v("ALI", "status " + mstatus);
               m.setStatus(mstatus);
               Log.v("ALI", "family value " + location);
               Log.v("ALI", "family value " + tickeno);
               m.setAddress(address);
               Log.v("ALI", "address " + m.getAddress());
               m.setContact_person(contactperson);
               Log.v("ALI", "contact " + m.getContact_person());
               m.setDetails(details);
               Log.v("ALI", "details " + m.getDetails());
               m.setLocation(location);
               Log.v("ALI", "location " + m.getLocation());
               m.setTicket_no(tickeno);
//                        startActivity(new Intent(MainActivity.this,homeActivity.class));
               Intent intent = new Intent(MainActivity.this, homeActivity.class);

               intent.putExtra("ticket no", MainActivity.pass);
               intent.putExtra("contact", m.getContact_person());
               intent.putExtra("address", m.getAddress());
               intent.putExtra("details", m.getDetails());
               intent.putExtra("sitekey", m.getKey());
               intent.putExtra("sat", m.getStatus());
               startActivity(intent);
//                modl1.add(m);
//                        adapter.addItem(m);

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

//                if(email.isEmpty())
//                {
//                    emailid.setError("enter email");
//                    emailid.requestFocus();
//
//
//                }
//                else if (pass.isEmpty())
//                {
//                    password.setError("enter password");
//                    password.requestFocus();
//                }
//                else if (email.isEmpty() && pass.isEmpty())
//                {
//                    Toast.makeText(MainActivity.this,"field are empty" ,Toast.LENGTH_SHORT).show();
//                }
//}
   }
                else if (!(email.isEmpty() ))
                {
                    startActivity(new Intent(MainActivity.this,recyclierview.class));
//                    Toast.makeText(MainActivity.this,"login error pls login again" ,Toast.LENGTH_SHORT).show();;
                }

//                else if (!(email.isEmpty() && pass.isEmpty()))
//                {
//                    mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(!task.isSuccessful()){
//
//                                Toast.makeText(MainActivity.this,"login error pls login again" ,Toast.LENGTH_SHORT).show();;
//                            }
//                            else {
//                                startActivity(new Intent(MainActivity.this,recyclierview.class));
//                            }
//                        }
//                    });
//
//                }
                else
                {
                    Toast.makeText(MainActivity.this,"error occured" ,Toast.LENGTH_SHORT).show();
                }


            }
        });
//        tvsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intsignup = new Intent(MainActivity.this,SignupActivity.class);
//                startActivity(intsignup);
//            }
//        });
    }


}
