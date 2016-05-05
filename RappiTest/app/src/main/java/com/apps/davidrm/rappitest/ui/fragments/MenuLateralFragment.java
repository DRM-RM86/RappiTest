package com.apps.davidrm.rappitest.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.entities.menuLateral.MenuLateralSection;
import com.apps.davidrm.rappitest.entities.menuLateral.MenuLateralType;
import com.apps.davidrm.rappitest.ui.activitys.MainActivity;
import com.apps.davidrm.rappitest.ui.adapters.AdapterMenuLateral;
import com.apps.davidrm.rappitest.ui.bases.BaseUIFragment;

import java.util.List;

/**
 * Created by DRM on 02/05/16.
 */
public class MenuLateralFragment extends BaseUIFragment {

    private List<MenuLateralSection> menuLateralSections;

    ListView list_view_menu;

    public static MenuLateralFragment getInstance(){
        MenuLateralFragment fragment=new MenuLateralFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_lateral, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menuLateralSections = MenuLateralSection.MenuBuilder.build(getActivity());
        AdapterMenuLateral adapterMenuLateral =new AdapterMenuLateral();
        adapterMenuLateral.setData(getActivity(), menuLateralSections);
        list_view_menu=(ListView)findViewById(R.id.list_view_menu);
        list_view_menu.setAdapter(adapterMenuLateral);


        list_view_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MenuLateralType type=menuLateralSections.get(position).getType();

                switch (type){
                    case INICIO:
                        ((MainActivity)getActivity()).ShowMessageMenuLAteral("Haz seleccionado inicio");
                        break;

                    case INFORMATION:
                        ((MainActivity)getActivity()).ShowMessageMenuLAteral(getActivity().getResources().getString(R.string.msg_info));
                        break;
                }
            }
        });
    }
}
