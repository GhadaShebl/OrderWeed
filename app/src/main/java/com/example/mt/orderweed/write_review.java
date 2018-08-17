package com.example.mt.orderweed;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class write_review extends AppCompatActivity {

    Toolbar toolbar;
    TextView screen_header,product_name,company_name,my_rate_header,my_review_header;
    EditText review;
    Button review_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        init_components();
        init_toolbar();
        set_typeface();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void init_components()
    {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        screen_header = (TextView)findViewById(R.id.screen_header);
        product_name = (TextView)findViewById(R.id.product_name);
        company_name = (TextView)findViewById(R.id.company_name);
        my_rate_header = (TextView)findViewById(R.id.my_rate_header);
        my_review_header = (TextView)findViewById(R.id.my_review_header);
        review_btn = (Button) findViewById(R.id.review_btn);
        review = (EditText)findViewById(R.id.review);
    }

    private void init_toolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.rate_product_screen_header);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }

    private void set_typeface()
    {
        Typeface typeface_futura = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        Typeface typeface_open_sans_semi_bold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
        Typeface typeface_open_sans_regular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");

        product_name.setTypeface(typeface_futura);
        company_name.setTypeface(typeface_open_sans_semi_bold);
        my_rate_header.setTypeface(typeface_open_sans_semi_bold);
        my_review_header.setTypeface(typeface_open_sans_semi_bold);
        review_btn.setTypeface(typeface_futura);
        review.setTypeface(typeface_open_sans_regular);
    }


}
