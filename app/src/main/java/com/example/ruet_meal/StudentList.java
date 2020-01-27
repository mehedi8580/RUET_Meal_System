package com.example.ruet_meal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentList extends AppCompatActivity {

  ListView listview;
private List<tokenStore>meallist;
private  CoustomeAdapter adeptare;
  DatabaseReference ref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String yesterdayAsString = dateFormat.format(calendar.getTime());

        ref = FirebaseDatabase.getInstance().getReference(yesterdayAsString);
        meallist = new ArrayList<>();
        adeptare = new CoustomeAdapter(StudentList.this,meallist);


        listview = findViewById(R.id.list);

    }

    @Override
    protected void onStart() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                meallist.clear();
                for(DataSnapshot snap: dataSnapshot.getChildren()){

                   tokenStore token=snap.getValue(tokenStore.class);
                   meallist.add(token);

                }

                listview.setAdapter(adeptare);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
