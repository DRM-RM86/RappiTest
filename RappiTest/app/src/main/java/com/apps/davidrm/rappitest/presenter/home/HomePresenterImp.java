package com.apps.davidrm.rappitest.presenter.home;

import android.content.Context;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.interactor.home.HomeInteractor;
import com.apps.davidrm.rappitest.interactor.home.HomeInteractorImp;
import com.apps.davidrm.rappitest.utils.Device;
import com.apps.davidrm.rappitest.viewInterface.HomeView;

/**
 * Created by DRM on 02/05/16.
 */
public class HomePresenterImp implements HomePresenter, OnHomeFinishedListener {

    HomeView homeView;
    HomeInteractor homeInteractor;


    public HomePresenterImp(HomeView homeView){
        this.homeView=homeView;
        homeInteractor=new HomeInteractorImp();
    }

    @Override
    public void ValidateGetItems(Context context, int numItems) {
        if(Device.isConnected(context)){
            homeView.OnProgress();
            homeInteractor.GetItems(context,numItems,this);
        }else{
            homeView.OnError(context.getResources().getString(R.string.error_connection));
        }

    }


    // Methods OnHomeFinishedListener
    @Override
    public void OnSuccess(EntityFeed entityFeed) {
        homeView.OnFinish();
        if(entityFeed!=null){
            homeView.OnSuccess(entityFeed);
        }else {homeView.OnError("No fue posible recuperar los datos");}

    }

    @Override
    public void OnError(String msg) {
        homeView.OnFinish();
        homeView.OnError(msg);
    }


}
