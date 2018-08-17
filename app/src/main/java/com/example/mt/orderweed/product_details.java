package com.example.mt.orderweed;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class product_details extends AppCompatActivity
{
    Toolbar toolbar;
    TextView screen_header,product_name,product_price,company_name,description_header,reviews_header;
    Button review_btn,go_to_website;
    RecyclerView reviews;
    ArrayList<review_item>reviews_list;
    int bookmarked = 0;
    FloatingActionButton bookmark;
    TextView cancel_txt, login_txt, dialog_header;
    RelativeLayout Cancel,Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init_components();
        init_toolbar();
        set_typeface();
        setup_reviews();

        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent review_screen = new Intent(getApplicationContext(),write_review.class);
                startActivity(review_screen);

                //// if guest
               /* product_details.ViewDialog alert = new product_details.ViewDialog();
                alert.showDialog(product_details.this,2);

                */
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /////// LOGGED IN USER ///////
                /*if (bookmarked == 0)
                {
                    Snackbar.make(view, "Product added to your Bookmarks!", Snackbar.LENGTH_LONG)
                            .setAction("", null).show();
                    bookmark.setImageResource(R.drawable.ic_bookmark_white_24dp);
                    bookmarked = 1;
                }
                else
                {
                    Snackbar.make(view, "Product removed from your Bookmarks!", Snackbar.LENGTH_LONG)
                            .setAction("", null).show();
                    bookmark.setImageResource(R.drawable.ic_bookmark_border_white_24dp);
                    bookmarked = 0;
                }*/

                /////// GUEST ///////
                product_details.ViewDialog alert = new product_details.ViewDialog();
                alert.showDialog(product_details.this,1);


            }
        });
    }

    private void init_components()
    {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        bookmark = (FloatingActionButton) findViewById(R.id.bookmark);
        screen_header = (TextView)findViewById(R.id.screen_header);
        product_name = (TextView)findViewById(R.id.product_name);
        product_price = (TextView)findViewById(R.id.product_price);
        company_name = (TextView)findViewById(R.id.company_name);
        description_header = (TextView)findViewById(R.id.description_header);
        reviews_header = (TextView)findViewById(R.id.reviews_header);
        review_btn = (Button) findViewById(R.id.review_btn);
        go_to_website = (Button) findViewById(R.id.go_to_website_btn);
    }

    private void init_toolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.details_screen_header);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }

    private void set_typeface()
    {
        Typeface typeface_futura = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        Typeface typeface_open_sans_semi_bold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");

        product_name.setTypeface(typeface_futura);
        product_price.setTypeface(typeface_open_sans_semi_bold);
        company_name.setTypeface(typeface_open_sans_semi_bold);
        description_header.setTypeface(typeface_open_sans_semi_bold);
        reviews_header.setTypeface(typeface_open_sans_semi_bold);
        review_btn.setTypeface(typeface_futura);
        go_to_website.setTypeface(typeface_futura);
    }

    public void setup_reviews()
    {
        reviews = (RecyclerView)findViewById(R.id.reviews_recycler_view);
        reviews_list = new ArrayList<>();
        reviews_list.add(new review_item(5,"Ted Mosby",R.drawable.tedd,"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"));
        reviews_list.add(new review_item(5,"Ted Mosby",R.drawable.tedd,"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"));
        reviews_list.add(new review_item(5,"Ted Mosby",R.drawable.tedd,"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"));
        reviews_list.add(new review_item(5,"Ted Mosby",R.drawable.tedd,"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviews.setLayoutManager(layoutManager);
        reviews_recycler_view_adapter adapter = new reviews_recycler_view_adapter(this, reviews_list);
        reviews.setAdapter(adapter);
    }


    public class ViewDialog {

        public void showDialog(Activity activity, int selector) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            if (selector == 1)
                dialog.setContentView(R.layout.cannot_bookmark_dialog);

            else if (selector == 2)
                dialog.setContentView(R.layout.cannot_rate_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ////////// Set typeface of dialog components //////////
            cancel_txt = (TextView) dialog.findViewById(R.id.cancel_txt);
            login_txt = (TextView) dialog.findViewById(R.id.continue_txt);
            dialog_header = (TextView) dialog.findViewById(R.id.dialog_header);

            Typeface typeface_btns = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
            Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Semibold.ttf");
            cancel_txt.setTypeface(typeface_btns);
            login_txt.setTypeface(typeface_btns);
            dialog_header.setTypeface(typeface_header);

            Cancel = (RelativeLayout) dialog.findViewById(R.id.cancel_action);
            Continue = (RelativeLayout) dialog.findViewById(R.id.continue_action);
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),signup_login_tabs.class));

                }
            });

            dialog.show();
        }}
}
