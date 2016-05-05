package com.apps.davidrm.rappitest.viewInterface;

import com.apps.davidrm.rappitest.entities.EntityFeed;

/**
 * Created by DRM on 02/05/16.
 */
public interface HomeView {

    void OnProgress();
    void OnFinish();
    void OnError(String error);
    void OnSuccess(EntityFeed entityFeed);

}
