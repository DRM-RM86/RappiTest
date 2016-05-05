package com.apps.davidrm.rappitest.ui.activitys;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.models.databases.RappiDBHelper;
import com.apps.davidrm.rappitest.presenter.home.HomePresenter;
import com.apps.davidrm.rappitest.presenter.home.HomePresenterImp;
import com.apps.davidrm.rappitest.ui.bases.BaseUIActivity;
import com.apps.davidrm.rappitest.ui.fragments.FragmentCategory;
import com.apps.davidrm.rappitest.ui.fragments.MenuLateralFragment;
import com.apps.davidrm.rappitest.utils.Utilities;
import com.apps.davidrm.rappitest.viewInterface.HomeView;

/**
 * Created by DRM on 03/05/16.
 */

public class MainActivity extends BaseUIActivity implements HomeView{

    Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    HomePresenter presenter;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Un momento por favor");
        pDialog.setCancelable(false);
        pDialog.setIndeterminate(false);

        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_container, MenuLateralFragment.getInstance() ).commit();
        presenter=new HomePresenterImp(this);
        presenter.ValidateGetItems(this,30);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void ShowMessageMenuLAteral(String msg) {
        Utilities.createAlert(this,msg);
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else

        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                else
                    mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.item_menu1:
                presenter.ValidateGetItems(this,20);
                break;

            case R.id.item_menu2:
                presenter.ValidateGetItems(this,30);
                break;

            case R.id.item_menu3:
                presenter.ValidateGetItems(this,40);
                break;

            case R.id.item_menu4:
                presenter.ValidateGetItems(this,50);
                break;


            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnProgress() {
        pDialog.show();
    }

    @Override
    public void OnFinish() {
        pDialog.dismiss();
    }

    @Override
    public void OnError(String error) {
        Utilities.createAlert(this,error);
    }

    @Override
    public void OnSuccess(EntityFeed entityFeed1) {
        EntityFeed entityFeed=RappiDBHelper.getInstance(this).getFeeds();

            getSupportFragmentManager().beginTransaction().remove(FragmentCategory.getInstance(entityFeed)).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.section_container, FragmentCategory.getInstance(entityFeed)).commit();
            setTitleToolbar(entityFeed.getAuthor());
    }
}
