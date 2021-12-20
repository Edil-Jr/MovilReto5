package com.example.reto2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class splash extends AppCompatActivity implements Runnable{
    Thread h1;
    Handler p1;
    ProgressBar barraprogreso;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView comidasplash = (ImageView) findViewById(R.id.Imgsplash);
        comidasplash.setBackgroundResource(R.drawable.comidas);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        /**Barra de progreso de carga**/

        text1= findViewById(R.id.text);
        barraprogreso =(ProgressBar) findViewById(R.id.progressBar);

        Handler p1= new Handler();
        Handler finalP = p1;
        h1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for(int i=0;i<20;i++){
                        Thread.sleep(1000);
                        finalP.sendMessage(finalP.obtainMessage(i));
                        /*
                         */
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        });
        h1.start();

        p1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                text1.setText(String.valueOf(msg.what));
                barraprogreso.setProgress(msg.what);
                return false;
            }
        });



        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 100);
        }catch (Exception e){
            Log.e("TAG_ERROR", "Error EX: " + e.getMessage());
            e.printStackTrace();
        }




    }

    @Override
    public void run() {

    }
}