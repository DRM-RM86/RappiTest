package com.apps.davidrm.rappitest.interactor.home;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.models.Connection;
import com.apps.davidrm.rappitest.models.ParserMain;
import com.apps.davidrm.rappitest.models.databases.RappiDBHelper;
import com.apps.davidrm.rappitest.presenter.home.OnHomeFinishedListener;

/**
 * Created by DRM on 02/05/16.
 */
public class HomeInteractorImp implements HomeInteractor {

    OnHomeFinishedListener listener;
    Context context;

    @Override
    public void GetItems(Context context,int numItems, OnHomeFinishedListener listener) {
        this.listener=listener;
        this.context=context;
        new GetItemsTask().execute(numItems);
    }

    private class GetItemsTask extends AsyncTask<Integer,Void,EntityFeed>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected EntityFeed doInBackground(Integer... params) {
            return ParserMain.GetAllItems(Connection.ResponseService(params[0]));
        }

        @Override
        protected void onPostExecute(EntityFeed entityFeed) {
            super.onPostExecute(entityFeed);
            if(entityFeed!=null){
                RappiDBHelper.getInstance(context).insertDatas(entityFeed);
                listener.OnSuccess(entityFeed);
            }else{
                listener.OnError(context.getResources().getString(R.string.error_msg_nodata));
            }
        }
    }
}
