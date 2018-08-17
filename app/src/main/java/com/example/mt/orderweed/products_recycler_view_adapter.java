package com.example.mt.orderweed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class products_recycler_view_adapter extends RecyclerView.Adapter<products_recycler_view_adapter.ViewHolder>
{
    private ArrayList<product_item> Products = new ArrayList<>();
    private ArrayList<product_item> copy_Products = new ArrayList<>();
    private static Context mContext;

    public products_recycler_view_adapter(Context context, ArrayList<product_item> Products) {
        this.Products = Products;
        mContext = context;
        copy_Products = new ArrayList<>();
        for (int i = 0; i < Products.size(); i++)
        {
            copy_Products.add(Products.get(i));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        product_item item = Products.get(position);
        holder.product_name.setText(item.getName());
        holder.company_name.setText(item.getCompany());
        holder.thc.setText(item.getThc());
        holder.cbd.setText(item.getCbd());
        Picasso.with(mContext)
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView product_name,company_name,thc_header,thc,cbd_header,cbd;
        ImageView img;
        ViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView)itemView.findViewById(R.id.user_image);
            product_name = (TextView) itemView.findViewById(R.id.product_name);
            company_name = (TextView) itemView.findViewById(R.id.company_name);
            thc_header = (TextView) itemView.findViewById(R.id.thc_header);
            thc = (TextView) itemView.findViewById(R.id.thc);
            cbd_header = (TextView) itemView.findViewById(R.id.cbd_header);
            cbd = (TextView) itemView.findViewById(R.id.cbd);
            img = (ImageView) itemView.findViewById(R.id.product_img);
        }

        @Override
        public void onClick(View view) {
            Intent details = new Intent(mContext,product_details.class);
            mContext.startActivity(details);
        }
    }

    ///////////////////////////////// SETUP SEARCH FILTER /////////////////////////////////
    public void filter(String queryText)
    {
        Products.clear();

        if(queryText.isEmpty())
        {
            Products.addAll(copy_Products);
        }
        else
        {
            for(int i = 0; i < copy_Products.size(); i++)
            {
                String name = copy_Products.get(i).getName();
                String company = copy_Products.get(i).getCompany();
                if(name.toLowerCase().contains(queryText.toLowerCase()))
                {
                    Products.add(copy_Products.get(i));
                }

                if(company.toLowerCase().contains(queryText.toLowerCase()))
                {
                    Products.add(copy_Products.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }

}
