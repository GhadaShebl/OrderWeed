package com.example.mt.orderweed;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class forgot_password_email extends AppCompatActivity
{
    Toolbar toolbar;
    TextView screen_header,screen_guide,email_error;
    EditText mail;
    Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_email);
        init_components();
        init_toolbar();
        setup_typeface();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /* if email doesn't exist - call show_error() */
                Intent reset_pass = new Intent(getApplicationContext(),Reset_password.class);
                startActivity(reset_pass);

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
        mail.addTextChangedListener(mTextWatcher);
    }

    private void init_components()
    {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        screen_header = (TextView) findViewById(R.id.screen_header);
        next_btn = (Button) findViewById(R.id.next_btn);
        screen_guide = (TextView) findViewById(R.id.screen_guide);
        mail = (EditText)findViewById(R.id.email);

    }
    private void init_toolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.forgot_password_screen_header);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }

    private void setup_typeface()
    {
        Typeface typeface_btns = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        Typeface typeface_guide = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");

        next_btn.setTypeface(typeface_btns);
        screen_guide.setTypeface(typeface_guide);
    }


    private void validate_input()
    {

        if(!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches())
        {
            next_btn.setEnabled(false);
            next_btn.setAlpha(0.5f);
        }
        else
        {
            next_btn.setEnabled(true);
            next_btn.setAlpha(1f);
        }
    }

    void show_error()
    {
        email_error = (TextView)findViewById(R.id.email_error);
        Typeface typeface_error = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        email_error.setTypeface(typeface_error);
        email_error.setVisibility(View.VISIBLE);
    }
}
