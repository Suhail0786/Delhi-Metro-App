package com.example.mymetro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout d1;
    private ActionBarDrawerToggle abdt;
    ViewFlipper flipper ;
    private Context context;


    private static Button im1;
    private static Button im2;
    private static Button im3;
    private static Button im4;
    private static Button im5;
    private static Button im6;
    private static Button im7;
    private static Button im8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//ImageSlider Func
        int imgarray[] = {R.drawable.slideim1,R.drawable.slideim2,R.drawable.slideim3,R.drawable.slideim4};
        flipper = (ViewFlipper) findViewById(R.id.flipper);

        for(int i=0;i<imgarray.length;i++)
        {
            showImage(imgarray[i]);
        }



//SideBar Functionality
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        d1 = findViewById(R.id.drawer_layout);
        abdt = new ActionBarDrawerToggle(this,d1,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        abdt.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(abdt);
        abdt.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id==R.id.about)
                {
                    Toast.makeText(MainActivity.this, "You Clicked About", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        OpenRoute();
        OpenGates();
        OpenRecharge();
        OpenMap();
        OpenRate();
        OpenShare();
        OpenHelp();
        OpenAbout();

    }


//ImageSlider Method
    public  void showImage(int img){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu,menu);

        return true;
    }


    @Override
    public void onBackPressed() {
        if (this.d1.isDrawerOpen(GravityCompat.START)) {
            this.d1.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Menu Item Selected Method
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }



//Button Clickable Method
    public void OpenRoute(){
            im1 = (Button) findViewById(R.id.btn1);
            im1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent (MainActivity.this,RouteActivity.class);
                    startActivity(intent);
                }
            });
        }

        public void OpenGates(){
        im2 = (Button) findViewById(R.id.btn2);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }

        public void OpenRecharge(){
        im3 = (Button) findViewById(R.id.btn3);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RechargeActivity.class);
                startActivity(intent);
            }
        });
        }

        public void OpenMap(){
        im4 = (Button) findViewById(R.id.btn4);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        }

        public void OpenRate(){
        im5 = (Button) findViewById(R.id.btn5);
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("markets://details?id=" + "com.android.chrome")));
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
        }

        public void OpenShare(){
        im6 =(Button) findViewById(R.id.btn6);
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Here will be my app link...coming soon ");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"Share Via"));
            }
        });

            }

        public void OpenHelp(){
        im7 = (Button) findViewById(R.id.btn7);
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }

        public void OpenAbout(){
        im8 = (Button) findViewById(R.id.btn8);
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        }
    }

