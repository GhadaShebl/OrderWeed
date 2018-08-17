package com.example.mt.orderweed;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class reviews_recycler_view_adapter extends RecyclerView.Adapter<reviews_recycler_view_adapter.ViewHolder>
{
    private ArrayList<review_item> Reviews = new ArrayList<>();
    private Context mContext;

    public reviews_recycler_view_adapter(Context context, ArrayList<review_item> Posts) {
        this.Reviews = Posts;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.itemView.setLongClickable(true);
        holder.user_img.setImageResource(Reviews.get(position).getUser_image());
        holder.user_name.setText(Reviews.get(position).getUser_name());
        holder.review.setText(Reviews.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return Reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener
    {
        ImageView user_img;
        TextView user_name;
        TextView review;

        public ViewHolder(final View itemView)
        {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            user_img = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            review = itemView.findViewById(R.id.review);
            set_typeface();

        }

        public void set_typeface()
        {
            Typeface typeface_open_sans_semi_bold = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Semibold.ttf");
            Typeface typeface_open_sans_regular = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-Regular.ttf");

            user_name.setTypeface(typeface_open_sans_semi_bold);
            review.setTypeface(typeface_open_sans_regular);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem myActionItem = contextMenu.add("Delete");
            myActionItem.setOnMenuItemClickListener(this);
        }
    }

}
