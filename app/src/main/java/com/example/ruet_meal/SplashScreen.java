package com.example.ruet_meal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
ProgressBar prog;
int progres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prog=findViewById(R.id.progress);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Thread tread= new Thread(new Runnable() {
            @Override
            public void run() {
                work();
                acti();
            }
        });
    }

    public void work()
    {

        for(progres=20; 100 >= progres; progres+=20){

            try {
                Thread.sleep(1000);

                prog.setProgress(progres);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public  void acti(){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
