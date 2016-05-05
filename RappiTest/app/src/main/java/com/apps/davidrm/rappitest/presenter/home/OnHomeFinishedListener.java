package com.apps.davidrm.rappitest.presenter.home;

import com.apps.davidrm.rappitest.entities.EntityFeed;

/**
 * Created by DRM on 02/05/16.
 */
public interface OnHomeFinishedListener {

    void OnSuccess(EntityFeed entityFeed);
    void OnError(String msg);
}
