package com.example.mt.orderweed;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity
{
    TextView app_name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ////////// Make Activity Fullscreen //////////
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        /////////////////////////////////////////////
        setup_logo_font();
        splash_thread();

    }

    /////////////////////////// Change the default font of logo text to Coolvetica font ///////////////////////////
    void setup_logo_font()
    {
        app_name = (TextView)findViewById(R.id.app_name);
        Typeface typeface_logo = Typeface.createFromAsset(getAssets(), "fonts/harlow.ttf");
        app_name.setTypeface(typeface_logo);
    }

    void splash_thread()
    {
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork()
    {
        try
        {
            Thread.sleep(2500);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startApp()
    {
        ///// If not logged in
        Intent signup_login_tabs = new Intent(SplashScreen.this, signup_login_tabs.class);
        startActivity(signup_login_tabs);

        ///// If logged in
    }
}
