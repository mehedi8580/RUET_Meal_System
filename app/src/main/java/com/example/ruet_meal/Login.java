package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText em,pass;
    ProgressBar prog;
    private FirebaseAuth mAuth;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        em=findViewById(R.id.txtemail);
        pass=findViewById(R.id.txtpwd);
        prog=findViewById(R.id.progress);
        tv=findViewById(R.id.notice);
    }

    public  void gotoreg(View view){


        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public  void gotoprofile(View view){

        String email=em.getText().toString().trim();
        String password=pass.getText().toString().trim();

        if(email.isEmpty()){
            em.setError("Please Enter Email");
            em.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            em.setError("Please Valid Email");
            em.requestFocus();
            return;

        }
        if(password.isEmpty()){
            pass.setError("Please Enter password");
            pass.requestFocus();
            return;

        }
        if(password.length()<6){
            pass.setError("Password must be greater than 6 character");
            pass.requestFocus();
            return;

        }

        prog.setVisibility(View.VISIBLE);

         mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {


               if (task.isSuccessful()) {

                   Toast.makeText(Login.this,"LogIn Successfully",Toast.LENGTH_SHORT).show();
                   finish();
                   Intent intent=new Intent(Login.this,Uprofile.class);
                   startActivity(intent);
               } else {


                   Toast.makeText(Login.this,"Ivalid Email And Password!",Toast.LENGTH_SHORT).show();

               }


             }
         })  ;



    }



}
