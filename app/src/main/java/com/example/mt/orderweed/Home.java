package com.example.mt.orderweed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class Home extends AppCompatActivity implements  SearchView.OnQueryTextListener {

    private TextView screen_header;
    android.support.v7.widget.Toolbar toolbar;
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    private static ViewPager mPager;
    private static int currentPage = 0;
    RecyclerView indica_recyclerview, sativa_recyclerview, oils_recyclerview, hybrid_recyclerview, others_recyclerview;
    ArrayList<product_item> products;
    products_recycler_view_adapter adapter;
    TextView indica_header,sativa_header,oils_header,hybrid_header,others_header;
    LinearLayout indica_layout, sativa_layout, oils_layout, hybrid_layout, others_layout;
    String []categories= {"Indica","Sativa","Oils","Hybrid","Others"};
    ArrayList<TextView> Category_headers;
    Typeface typeface_header;
    ArrayList<LinearLayout> Category_layouts;
    Button view_more;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init_components();
        init_toolbar();
        init_slider();
        setup_categories();
    }

    private void init_components()
    {
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        screen_header = (TextView)findViewById(R.id.screen_header);

        mPager = (ViewPager) findViewById(R.id.pager);

        ///////// ADD ALL CATEGORIES LAYOUTS TO ARRAYLIST TO MANIPULATE THEM LATER
        Category_layouts = new ArrayList<>();
        indica_layout = (LinearLayout)findViewById(R.id.indica_layout);
        sativa_layout = (LinearLayout)findViewById(R.id.sativa_layout);
        oils_layout = (LinearLayout)findViewById(R.id.oils_layout);
        hybrid_layout = (LinearLayout)findViewById(R.id.hybrid_layout);
        others_layout = (LinearLayout)findViewById(R.id.others_layout);
        Category_layouts.add(indica_layout);Category_layouts.add(sativa_layout);Category_layouts.add(oils_layout);Category_layouts.add(hybrid_layout);Category_layouts.add(others_layout);

        typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
    }
    ///////////////////////////////////// INITIALIZE TOOLBAR /////////////////////////////////////
    private void init_toolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.home_screen_header);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// SETUP ALL 5 CATEGORIES IN 1 GO /////////////////////////////////////
    public void setup_categories()
    {
        for (int i = 0 ;i < Category_layouts.size(); i++)
        {
            TextView category_name = (TextView)Category_layouts.get(i).findViewById(R.id.category_name);
            category_name.setText(categories[i]);
            category_name.setTypeface(typeface_header);
            RecyclerView recyclerView = (RecyclerView)Category_layouts.get(i).findViewById(R.id.category_recyclerview);
            setup_recyclerview(recyclerView,i);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// SETUP BANNERS SLIDER  /////////////////////////////////////
    public void init_slider()
    {
        for(int i=0;i<3;i++)
        {
            XMENArray.add(R.mipmap.banner);
        }

        mPager.setAdapter(new Slider_adapter(this,XMENArray));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMENArray.size())
                {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// SETUP SEARCH BAR /////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search products..");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /////////////////////// CALL FILTER FUNCTION IN ADAPTER ///////////////////////
    @Override
    public boolean onQueryTextChange(String newText)
    {
        adapter.filter(newText);
        return true;
    }
    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return true;
    }


    public void setup_recyclerview(RecyclerView recyclerView,int category_id)
    {
        //////// Momkn nst5dm b2a category_id da w n select el products el sa7 bto3o mn el DB

        products = new ArrayList<>();
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb","Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_imgg,5,"Zombie Cush","Another Company","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Snow Dome","Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_imgg,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));
        products.add(new product_item(R.drawable.product_img,5,"Devils Thumb", "Aurora Cannabis Inc.","0.0%","0.0%",""));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new products_recycler_view_adapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    /////////////////////////////// WHEN VIEW MORE BUTTON IS CLICKED FROM ANY CATEGORY  ////////////////////////////////////

    public void view_more(View v)
    {
        startActivity(new Intent(getApplicationContext(), Home_Products.class));
    }

}