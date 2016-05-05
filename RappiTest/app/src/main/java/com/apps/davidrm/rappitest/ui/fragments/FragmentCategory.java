package com.apps.davidrm.rappitest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.ui.adapters.FragmentAdapter;
import com.apps.davidrm.rappitest.ui.bases.BaseUIFragment;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by DRM on 02/05/16.
 */
public class FragmentCategory extends BaseUIFragment {

    TabLayout tabLayout;
    ViewPager viewPager_tab;
    ViewPager viewPager ;
    TextView tx_title_service;
    private static String KEYARG="key";
    final String KEYGAMES="Games";
    final String KEYSOCIAL="Social Networking";



    public static FragmentCategory getInstance(EntityFeed entityFeed){
        FragmentCategory fragment=new FragmentCategory();
        Bundle args=new Bundle();
        args.putSerializable(KEYARG,entityFeed);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager_tab=(ViewPager)findViewById(R.id.viewpager_tabs);
        EntityFeed feeds=(EntityFeed) getArguments().getSerializable(KEYARG);
        setPages(feeds);
        viewPager=  (ViewPager) findViewById(R.id.viewpager);
        setViewPAgerHeader(feeds);
        tx_title_service=(TextView)findViewById(R.id.tx_title_service);
        tx_title_service.setText(feeds.getTitle());
    }

    void setViewPAgerHeader(EntityFeed feeds){

        FragmentAdapter adaper = new FragmentAdapter(getChildFragmentManager());
        for(int i=0; i<5; i++){
            adaper.addFragment(FragmentPager.newInstance(feeds.getEntrys().get(i)));
        }
        viewPager.setAdapter(adaper);
        CirclePageIndicator indicator=(CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        adaper.notifyDataSetChanged();

    }


    void setPages(EntityFeed feeds){

        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager());
        ArrayList<EntityEntry>entryGames=new ArrayList<>();
        ArrayList<EntityEntry>entrySocial=new ArrayList<>();
        ArrayList<EntityEntry>entryOthers=new ArrayList<>();

        for(int i=0; i<feeds.getEntrys().size(); i++){
            if(feeds.getEntrys().get(i).getEntitycategory().equals(KEYGAMES)){


                entryGames.add(feeds.getEntrys().get(i));

            }else if(feeds.getEntrys().get(i).getEntitycategory().equals(KEYSOCIAL)){
                entrySocial.add(feeds.getEntrys().get(i));
            }else{
                entryOthers.add(feeds.getEntrys().get(i));
            }
        }
        adapter.addFragment(FragmentItems.getInstance(entryGames), getActivity().getResources().getString(R.string.info_title_games));
        adapter.addFragment(FragmentItems.getInstance(entrySocial),getActivity().getResources().getString(R.string.info_title_social));
        adapter.addFragment(FragmentItems.getInstance(entryOthers),getActivity().getResources().getString(R.string.info_title_entertaiment));


        viewPager_tab.setAdapter(adapter);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager_tab);
            }
        });
        getActivity().setTitle(feeds.getAuthor());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categorys,container,false);
    }
}
