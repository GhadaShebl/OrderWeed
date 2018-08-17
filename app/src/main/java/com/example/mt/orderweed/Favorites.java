package com.example.mt.orderweed;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    Toolbar toolbar;
    TextView screen_header;
    products_recycler_view_adapter adapter;
    RecyclerView Products_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        init_components();
        init_toolbar();
        setup_recyclerview();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    ///////////////////////////////////// INITIALIZE TOOLBAR /////////////////////////////////////
    private void init_components()
    {
       toolbar = (Toolbar)findViewById(R.id.toolbar);
       Products_recyclerview = (RecyclerView)findViewById(R.id.products_recyclerview);
       screen_header = (TextView)findViewById(R.id.screen_header);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// INITIALIZE TOOLBAR /////////////////////////////////////
    private void init_toolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.favorites_screen_header);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    private void setup_recyclerview()
    {
        ArrayList<product_item> products = new ArrayList<>();

        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb","Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Zombie Cush","Another Company","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Snow Dome","Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Different Product", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));

        Products_recyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2) );
        adapter = new products_recycler_view_adapter(getApplicationContext(),products);
        Products_recyclerview.setAdapter(adapter);
    }

}
