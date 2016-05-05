package com.apps.davidrm.rappitest.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.ui.activitys.ActivityDetail;
import com.apps.davidrm.rappitest.ui.adapters.AdapterItems;
import com.apps.davidrm.rappitest.ui.bases.BaseUIFragment;
import com.apps.davidrm.rappitest.utils.Device;
import com.kogitune.activity_transition.ActivityTransitionLauncher;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by DRM on 02/05/16.
 */
public class FragmentItems extends BaseUIFragment{



    RecyclerView recycler_apps;
    private static String KEYARG="key";


    public static FragmentItems getInstance(ArrayList<EntityEntry> entrys){
        FragmentItems fragment=new FragmentItems();
        Bundle args=new Bundle();
        args.putSerializable(KEYARG,entrys);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inicio_fragment, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<EntityEntry> items= (List<EntityEntry>) getArguments().getSerializable(KEYARG);
        if(Device.isTablet(getActivity())){
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit) .replace(R.id.section_container_tablet, FragmentDetail.getInstance(items.get(0))).commit();
        }

        recycler_apps = (RecyclerView) findViewById(R.id.recycler_apps);
        int value= Device.isTablet(getActivity()) ? 4 : 3 ;
        GridLayoutManager  manager1   = new GridLayoutManager(getActivity(), value);
        recycler_apps.setLayoutManager(manager1);




        recycler_apps.setAdapter(new AdapterItems(getActivity(),items, new AdapterItems.OnItemClickListener() {
            @Override
            public void onItemClick(View v,EntityEntry item) {
                if(Device.isTablet(getActivity())){
                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).replace(R.id.section_container_tablet, FragmentDetail.getInstance(item)).setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).commit();
                }else {
                    final Intent intent = new Intent(getActivity(), ActivityDetail.class);
                    intent.putExtra(KEYARG,item);
                    ActivityTransitionLauncher.with(getActivity()).from(v.findViewById(R.id.image_item)).launch(intent);
                }
            }
        }));
    }

}
