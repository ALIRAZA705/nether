package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    EditText emailid,password;
    FloatingActionButton btnsignin;
    TextView tvsignup;
   public static  String email,pass;
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
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseuser= mFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if((mFirebaseuser)!=null){
                    Toast.makeText(MainActivity.this,"you are logged in" ,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,recyclierview.class));


                }
                else
                {
                    Toast.makeText(MainActivity.this,"please log in" ,Toast.LENGTH_SHORT).show();
                }

            }
        };
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email = emailid.getText().toString();
                 pass=password.getText().toString();
                if(email.isEmpty())
                {
                    emailid.setError("enter email");
                    emailid.requestFocus();


                }
                else if (pass.isEmpty())
                {
                    password.setError("enter password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"field are empty" ,Toast.LENGTH_SHORT).show();
                }

                else if (!(email.isEmpty() && pass.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){

                                Toast.makeText(MainActivity.this,"login error pls login again" ,Toast.LENGTH_SHORT).show();;
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,recyclierview.class));
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this,"error occured" ,Toast.LENGTH_SHORT).show();
                }


            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsignup = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intsignup);
            }
        });
    }

//    public void OpenSignupPage(View view) {
//        startActivity(new Intent(MainActivity.this,SignupActivity.class));
//    }
}
