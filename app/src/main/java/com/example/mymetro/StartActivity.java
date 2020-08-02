package com.example.mymetro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class StartActivity extends AppCompatActivity {
    ProgressBar pgr,pgr1;
    int progress = 0;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

            pgr = (ProgressBar)findViewById(R.id.progressBar3);
            pgr1 = (ProgressBar) findViewById(R.id.progressBar4);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0; i<5; i++)
                    {
                        progress+=20;
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                pgr.setProgress(progress);
                                pgr1.setProgress(progress);
                                if(progress==pgr.getMax() || progress==pgr1.getMax())
                                {
                                    Intent in = new Intent("com.example.mymetro.MainActivity");
                                    startActivity(in);
                                    finish();
                                }
                            }
                        });
                        try
                        {
                            Thread.sleep(500);
                        }
                        catch(InterruptedException e){}
                    }

                }

            }).start();
        }
    }

