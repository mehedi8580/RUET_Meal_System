package com.example.ruet_meal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button stu,adm,hal,rue,loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//not connect dailog start

        isNetworkConnectionAvailable();
//not internet connection finished

        stu=findViewById(R.id.button1);
        adm = findViewById(R.id.button2);
        hal=findViewById(R.id.button3);
        rue=findViewById(R.id.button4);
        loc=findViewById(R.id.button5);
    }

    public void student(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }
//check internet connection function

    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");

        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");

        }
    }
//check internet connection function finished
public void token(View view) {

    Intent intent =new Intent(getApplicationContext(),Token.class);
    startActivity(intent);
}


public void adminlogin(View view){

    Intent intent =new Intent(getApplicationContext(),HallLogin.class);
    startActivity(intent);

}

    public void location(View view) {

        Intent intent =new Intent(getApplicationContext(),Location.class);
        startActivity(intent);
    }


}
