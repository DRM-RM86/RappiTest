package com.apps.davidrm.rappitest.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.ui.activitys.ActivityDetail;
import com.apps.davidrm.rappitest.ui.bases.BaseUIFragment;
import com.apps.davidrm.rappitest.utils.Device;
import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;

/**
 * Created by DRM on 02/05/16.
 */
public class FragmentPager extends BaseUIFragment {

    static String KEYARG="key";
    ImageView img_pager;
    TextView text_title_app,text_category_app,text_right_app;

    public static FragmentPager newInstance(EntityEntry entityEntry){
        FragmentPager fragment=new FragmentPager();
        Bundle args=new Bundle();
        args.putSerializable(KEYARG,entityEntry);
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EntityEntry entityEntry=(EntityEntry)getArguments().getSerializable(KEYARG);
        text_title_app=(TextView)findViewById(R.id.text_title_app);
        text_title_app.setText(entityEntry.getEntityName());
        text_category_app=(TextView)findViewById(R.id.text_category_app);
        text_right_app=(TextView)findViewById(R.id.text_right_app);
        text_category_app.setText(entityEntry.getEntitycategory());
        text_right_app.setText(entityEntry.getEntityrights());
        img_pager=(ImageView)findViewById(R.id.img_pager);
        Picasso.with(getActivity()).load(entityEntry.getImages().get(2)).into(img_pager);


        img_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Device.isTablet(getActivity())){
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.section_container_tablet, FragmentDetail.getInstance(entityEntry)).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).commit();
                }else {
                    final Intent intent = new Intent(getActivity(), ActivityDetail.class);
                    intent.putExtra(KEYARG,entityEntry);
                    ActivityTransitionLauncher.with(getActivity()).from(v.findViewById(R.id.img_pager)).launch(intent);
                }
            }
        });
    }
}
