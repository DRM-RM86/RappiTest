package com.apps.davidrm.rappitest.ui.bases;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.apps.davidrm.rappitest.R;


/**
 * Created by DRM on 02/05/16.
 */
public class BaseUIFragment extends Fragment{


    public View findViewById(int resource){
         try{return getView().findViewById(resource);}catch (Exception e){return null;}
    }

    /**
     * Replace fragment int container
     * @param fragment
     * @param container
     */
    public void replaceFragment(Fragment fragment, int container){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(container, fragment).commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
