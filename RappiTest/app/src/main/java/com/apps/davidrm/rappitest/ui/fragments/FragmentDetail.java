package com.apps.davidrm.rappitest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.ui.bases.BaseUIFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by DRM on 03/05/16.
 */
public class FragmentDetail extends BaseUIFragment {

    ImageView image_item,img_detail;
    EntityEntry entityEntry;
    public static String KEYARG="key";
    Animation anim1;
    Animation anim2;
    LinearLayout content_summary,content_app_data;
    TextView tx_summary,tx_title,tx_rigth,tx_category;

    public static FragmentDetail getInstance(EntityEntry entityEntry){
       FragmentDetail fragment= new FragmentDetail();
        Bundle arg=new Bundle();
        arg.putSerializable(KEYARG,entityEntry);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         entityEntry= (EntityEntry) getArguments().getSerializable(KEYARG);
        image_item=(ImageView)findViewById(R.id.image_item);
        img_detail=(ImageView)findViewById(R.id.img_detail);
        tx_summary=(TextView)findViewById(R.id.tx_summary);
        tx_title=(TextView)findViewById(R.id.tx_title);
        tx_rigth=(TextView)findViewById(R.id.tx_rigth);
        tx_category=(TextView)findViewById(R.id.tx_category);
        content_summary=(LinearLayout)findViewById(R.id.content_summary);
        content_app_data=(LinearLayout)findViewById(R.id.content_app_data);

        Picasso.with(getActivity()).load(entityEntry.getImages().get(2)).into(image_item);
        Picasso.with(getActivity()).load(entityEntry.getImages().get(2)).into(img_detail);
        tx_summary.setText(entityEntry.getEntitysummary());
        tx_title.setText(entityEntry.getEntityName());
        tx_rigth.setText(entityEntry.getEntityrights());
        tx_category.setText(entityEntry.getEntitycategory());

        anim1= AnimationUtils.loadAnimation(getActivity(),R.anim.anim_enter_up);
        anim2= AnimationUtils.loadAnimation(getActivity(),R.anim.anim_enter_down);
        content_summary.startAnimation(anim1);
        content_app_data.startAnimation(anim2);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }
}
