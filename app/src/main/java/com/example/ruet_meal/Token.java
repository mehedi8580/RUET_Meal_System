package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Token extends AppCompatActivity {

    EditText em,pas,rol;
    private FirebaseAuth mAuth;
    Spinner ltok,dtok,hal;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        em = findViewById(R.id.temail);
        pas= findViewById(R.id.tpass);
        ltok=findViewById(R.id.tlaunch);
        dtok=findViewById(R.id.tdinner);
        rol=findViewById(R.id.troll);
        hal=findViewById(R.id.halid);
        mAuth = FirebaseAuth.getInstance();
    }

    public  void settoken(View view){

        String email=em.getText().toString().trim();
        String password=pas.getText().toString().trim();
        final String launch=ltok.getSelectedItem().toString();
        final String dinner = dtok.getSelectedItem().toString();
        final String roll = rol.getText().toString();
        final  String hall=hal.getSelectedItem().toString();



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String formattedDate = df.format(c);


                    Calendar rightNow = Calendar.getInstance();
                    int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)

                    if(currentHourIn24Format<=24)
                    {

                        DatabaseReference myRef = database.getReference(formattedDate);

                        tokenStore stor=new tokenStore(roll,dinner,launch,hall);

                        String key=myRef.push().getKey();
                        myRef.child(key).setValue(stor);

                        em.setText("");
                        pas.setText("");
                        rol.setText("");




                        Toast.makeText(Token.this, "Token Submit Successfully", Toast.LENGTH_SHORT).show();


                    }


                } else {
                    Toast.makeText(getApplicationContext(),"Invalid email and Password or not registered",Toast.LENGTH_SHORT).show();


                }

            }
        })  ;





    }

}
