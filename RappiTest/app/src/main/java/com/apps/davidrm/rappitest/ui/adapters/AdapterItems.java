package com.apps.davidrm.rappitest.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by DRM on 02/05/16.
 */
public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder> {


    Context context;
    public interface OnItemClickListener {
        void onItemClick(View v,EntityEntry item);
    }

    private final List<EntityEntry> items;
    private final OnItemClickListener listener;
    private int lastPosition = -1;

    public AdapterItems(Context context,List<EntityEntry> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.context=context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
        setAnimation(holder.itemView, position);

    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name_app,category_app;
        private ImageView image_item;

        public ViewHolder(View itemView) {
            super(itemView);
            name_app = (TextView) itemView.findViewById(R.id.name_app);
            category_app=(TextView)itemView.findViewById(R.id.category_app);
            image_item = (ImageView) itemView.findViewById(R.id.image_item);
        }

        public void bind(final EntityEntry item, final OnItemClickListener listener) {
            name_app.setText(item.getEntityName());
            category_app.setText(item.getEntitycategory());


            Picasso.with(itemView.getContext()).load(item.getImages().get(2)).into(image_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(itemView, item);
                }
            });
        }

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(1000);
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }


}