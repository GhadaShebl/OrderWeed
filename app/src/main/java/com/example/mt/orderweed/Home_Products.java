package com.example.mt.orderweed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Home_Products extends AppCompatActivity implements  SearchView.OnQueryTextListener{
    Toolbar toolbar;
    TextView screen_header;
    int expanded = 0;
    BottomNavigationView navigation;
    products_recycler_view_adapter adapter;
    Button search_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_products);
        init_components();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        disableShiftMode(navigation);
        init_toolbar();
        setup_recyclerview();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ViewDialog alert =new ViewDialog();
                alert.showDialog(Home_Products.this);
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmarks:
                startActivity(new Intent(getApplicationContext(),Favorites.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ViewDialog {
        Button filter;
        public void showDialog(Activity activity) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.search_filter);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            filter = (Button) dialog.findViewById(R.id.filter_btn);
            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });


            dialog.show();
        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_indica: {
                    screen_header.setText(R.string.indica);
                    return true;
                }
                case R.id.nav_sativa: {
                    screen_header.setText(R.string.sativa);
                    return true;
                }
                case R.id.nav_oils:{
                    screen_header.setText(R.string.oils);
                    return true;
                }
                case R.id.nav_hybrid:{
                    screen_header.setText(R.string.hybrid);
                    return true;
                }
                case R.id.nav_others:{
                    screen_header.setText(R.string.other);
                    return true;
                }
            }
            return false;
        }
    };

    private void setup_recyclerview()
    {
        RecyclerView Products_recyclerview = (RecyclerView) findViewById(R.id.products_recyclerview);
        ArrayList <product_item>products = new ArrayList<>();

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


    private void init_components()
    {
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        screen_header = (TextView)findViewById(R.id.screen_header);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }
    ///////////////////////////////////// INITIALIZE TOOLBAR /////////////////////////////////////
    private void init_toolbar()
    {
        toolbar.setNavigationIcon(R.drawable.ic_filter_list_primary_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        screen_header.setText(R.string.indica);
        Typeface typeface_header = Typeface.createFromAsset(getAssets(), "fonts/futura.otf");
        screen_header.setTypeface(typeface_header);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// SETUP SEARCH BAR /////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search..");
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


    //////////////////////////// DISABLE BOTTOM NAVIATION VIEW SHIFT MODE /////////////////////////////
    @SuppressLint("RestrictedApi")
    private static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);

                Field shiftAmount = item.getClass().getDeclaredField("mShiftAmount");
                shiftAmount.setAccessible(true);
                shiftAmount.setInt(item, 0);
                shiftAmount.setAccessible(false);

                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {

        } catch (IllegalAccessException e) {
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

}
