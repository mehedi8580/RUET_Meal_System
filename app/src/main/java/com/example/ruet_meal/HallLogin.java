package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HallLogin extends AppCompatActivity {

   EditText em,pass;
   Spinner hal;
    private FirebaseAuth mAuth;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_login);

        mAuth = FirebaseAuth.getInstance();
        em=findViewById(R.id.txtemail);
        pass= findViewById(R.id.txtpwd);
        hal = findViewById(R.id.spinner);

    }

    public  void gotolist(View view){

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


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"LogIn Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent=new Intent(getApplicationContext(),StudentList.class);
                    startActivity(intent);
                } else {


                    Toast.makeText(getApplicationContext(),"Ivalid Email And Password!",Toast.LENGTH_SHORT).show();

                }


            }
        })  ;


    }
}
