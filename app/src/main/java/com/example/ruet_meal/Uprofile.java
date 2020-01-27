package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Uprofile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    Spinner Launch, Dinner;
    DatabaseReference reference;
    TextView tname, thall, temail, tphone, tdept, troll;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_profile);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        Launch = findViewById(R.id.spinner);
        Dinner = findViewById(R.id.spinner2);
        tname = findViewById(R.id.name);
        thall = findViewById(R.id.hallName);
        temail = findViewById(R.id.email);
        tphone = findViewById(R.id.phone);
        tdept = findViewById(R.id.dept);
        troll = findViewById(R.id.roll);


        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String hallname = dataSnapshot.child("hall").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String dept = dataSnapshot.child("department").getValue().toString();
                String roll = dataSnapshot.child("roll").getValue().toString();

                tname.setText(name);
                thall.setText(hallname);
                temail.setText(email);
                tphone.setText(phone);
                tdept.setText(dept);
                troll.setText(roll);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    //menu creation
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.sout) {

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);


    }
}
