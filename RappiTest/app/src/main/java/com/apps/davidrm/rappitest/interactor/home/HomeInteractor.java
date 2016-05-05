package com.apps.davidrm.rappitest.interactor.home;

import android.content.Context;

import com.apps.davidrm.rappitest.presenter.home.OnHomeFinishedListener;

/**
 * Created by DRM on 02/05/16.
 */
public interface HomeInteractor {
    void GetItems(Context context,int numItems, OnHomeFinishedListener listener);
}
