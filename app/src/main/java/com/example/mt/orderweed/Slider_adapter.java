package com.example.mt.orderweed;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Slider_adapter extends PagerAdapter
{
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    TextView slider_caption;
    ImageView myImage;
    View myImageLayout;
    Typeface typeface_futura;
    public Slider_adapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        myImageLayout = inflater.inflate(R.layout.slider_item, view, false);
        myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        slider_caption = (TextView) myImageLayout.findViewById(R.id.image_caption);
        myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        set_typeface();
        return myImageLayout;
    }



    private void set_typeface() {
        typeface_futura = Typeface.createFromAsset(context.getAssets(), "fonts/futura.otf");
        slider_caption.setTypeface(typeface_futura);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
