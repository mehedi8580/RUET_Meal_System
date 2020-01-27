package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    EditText nme,phn,eml,pass,dept,rll;
    Button btn;
    Spinner sp;
    ProgressBar prb;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseuser;
    String hall,key,name,phone ,email,password,department,roll;
    DatabaseReference rootreference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        mAuth = FirebaseAuth.getInstance();
        rootreference=FirebaseDatabase.getInstance().getReference();
        nme =findViewById(R.id.etname);
        phn=findViewById(R.id.etphone);
        eml=findViewById(R.id.etemail);
        pass=findViewById(R.id.etpassword);
        dept=findViewById(R.id.etdept);
        rll=findViewById(R.id.etroll);
        prb=findViewById(R.id.progress);
        sp=findViewById(R.id.spiner);


    }

   public void GotoLogin(View view){

       Intent intent=new Intent(this,Login.class);
       startActivity(intent);

   }

    public void SendDataToDatabase(View view){

        name=nme.getText().toString();
         phone=phn.getText().toString().trim();
         email=eml.getText().toString().trim();
        password=pass.getText().toString().trim();
        department=dept.getText().toString();
        roll = rll.getText().toString().trim();
         hall=sp.getSelectedItem().toString();


                prb.setVisibility(View.VISIBLE);
        if(email.isEmpty()){
            eml.setError("Please Enter Email");
            eml.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           eml.setError("Please Valid Email");
            eml.requestFocus();
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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Storing Data on Firebase
                            firebaseuser=mAuth.getCurrentUser();
                            User user=new User(name,phone,email,department,roll,hall);
                            rootreference.child(firebaseuser.getUid()).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                finish();
                                             Toast.makeText(SignUp.this,"Regestered Sucssfully",Toast.LENGTH_SHORT).show();
                                             Intent intent=new Intent(SignUp.this,Login.class);

                                             startActivity(intent);
                                                prb.setVisibility(View.GONE);

                                            }
                                        }
                                    });
                        } else {

                        }

                        // ...
                    }
                });





    }

}
