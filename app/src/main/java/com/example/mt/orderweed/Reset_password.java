package com.example.mt.orderweed;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cdflynn.android.library.checkview.CheckView;

public class Reset_password extends AppCompatActivity
{
    CheckView check;
    Toolbar toolbar;
    TextView screen_header,dialog_header, pass_error,screen_guide;
    EditText pass,confirm_pass;
    Button reset_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init_components();

        init_toolbar();
        setup_typeface();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        validate_input();
        TextWatcher mTextWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // check Fields For Empty Values
                validate_input();
            }
        };
        pass.addTextChangedListener(mTextWatcher);
        confirm_pass.addTextChangedListener(mTextWatcher);

        reset_pass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (!pass.getText().toString().trim().equals(confirm_pass.getText().toString().trim()))
                {
                    show_error();
                }

                else
                {
                    final Reset_password.ViewDialog alert = new Reset_password.ViewDialog();
                    alert.showDialog(Reset_password.this);
                    final Timer timer2 = new Timer();
                }
            }
        });
    }

    private void init_components()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        screen_header = (TextView) findViewById(R.id.screen_header);
        reset_pass = (Button) findViewById(R.id.reset_pass_btn);
        pass = (EditText)findViewById(R.id.pass);
        confirm_pass = (EditText)findViewById(R.id.confirm_pass);
        screen_guide = (TextView)findViewById(R.id.screen_guide);
    }


    private void init_toolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.reset_password_screen_header);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }

    private void setup_typeface()
    {
        Typeface typeface_btns = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        reset_pass.setTypeface(typeface_btns);

        Typeface typeface_guide = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        screen_guide.setTypeface(typeface_guide);
    }

    void validate_input()
    {
        if(pass.getText().toString().trim().length() < 4 || confirm_pass.getText().toString().trim().length() < 4 || pass.getText().toString().trim().length()!= confirm_pass.getText().toString().trim().length() )
        {
            reset_pass.setEnabled(false);
            reset_pass.setAlpha(0.5f);
        }
        else
        {
            reset_pass.setEnabled(true);
            reset_pass.setAlpha(1f);
        }
    }

    private void  show_error()
    {
        pass_error = (TextView)findViewById(R.id.email_error);
        Typeface typeface_error = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        pass_error.setTypeface(typeface_error);
        pass_error.setVisibility(View.VISIBLE);
    }



    public class ViewDialog {

        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.success_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ////////// Set typeface of dialog components //////////
            dialog_header = (TextView) dialog.findViewById(R.id.dialog_header);

            Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
            dialog_header.setTypeface(typeface_header);

            check = (CheckView) dialog.findViewById(R.id.check);
            check.check();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    check.uncheck();
                }
            }, 2200);

            ////////// Automatically close the dialog after 3 seconds //////////
            final Timer timer2 = new Timer();
            timer2.schedule(new TimerTask() {
                public void run() {
                    dialog.dismiss();
                    timer2.cancel();
                    getApplicationContext().startActivity(new Intent(getApplicationContext(),Home_Products.class));
                }
            }, 2200);
            dialog.show();

        }}
}

