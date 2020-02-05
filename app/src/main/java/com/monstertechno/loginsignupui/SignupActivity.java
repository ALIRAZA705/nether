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

public class SignupActivity extends AppCompatActivity {
    EditText emailid,password;
    FloatingActionButton btnsignup;
    TextView tvsignin;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_signup);
        mFirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        tvsignin=findViewById(R.id.textView);
        btnsignup=findViewById(R.id.button);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pass=password.getText().toString();
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
                    Toast.makeText(SignupActivity.this,"field are empty" ,Toast.LENGTH_SHORT).show();
                }

                else if (!(email.isEmpty() && pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignupActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){

                                Toast.makeText(SignupActivity.this,"sign up unsuccessfully please try again" ,Toast.LENGTH_SHORT).show();;
                            }
                            else {
                                startActivity(new Intent(SignupActivity.this,recyclierview.class));
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(SignupActivity.this,"error occured" ,Toast.LENGTH_SHORT).show();
                }




            }
        });

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
