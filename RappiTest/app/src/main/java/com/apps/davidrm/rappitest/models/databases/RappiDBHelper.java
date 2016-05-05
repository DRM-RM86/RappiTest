package com.apps.davidrm.rappitest.models.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.entities.EntityFeed;

import java.util.ArrayList;

/**
 * Created by DRM on 05/05/16.
 */


public class RappiDBHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "RappiDBHelper";
    private static final String TAG= "RappiDBHelper.class";

    public Context context;
    private static RappiDBHelper mInstance;

    public static final String TABLE_DATA = "ITUNES_DATA";

    public static final String AUTHOR = "author";
    public static final String RIGHTS = "rights";
    public static final String TITLE="title";

    public static final String TABLE_FEED = "FEED_DATA";

    public static final String ENTITYID = "EntityID";
    public static final String ENTITYNAME = "EntityName";
    public static final String ENTITYSUMMARY = "Entitysummary";
    public static final String ENTITYRIGHTS = "Entityrights";
    public static final String ENTITYTITLE = "Entitytitle";
    public static final String ENTITYCATEGORY = "Entitycategory";


    public static final String TABLE_IMG_FEED = "FEED_IMG_DATA";

    public static final String IMG1 = "Img1";
    public static final String IMG2 = "Img2";
    public static final String IMG3 = "Img3";
   ;




    public static synchronized RappiDBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new RappiDBHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    public RappiDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDownloadedGuides(db);
    }

    private void createDownloadedGuides(SQLiteDatabase db){
        String sqlData="CREATE TABLE IF NOT EXISTS " + RappiDBHelper.TABLE_DATA + "("
                + RappiDBHelper.AUTHOR + " TEXT TEXT PRIMARY KEY, "
                + RappiDBHelper.RIGHTS + " TEXT, "
                + RappiDBHelper.TITLE +" TEXT );";

        String sqlFeed="CREATE TABLE IF NOT EXISTS " + RappiDBHelper.TABLE_FEED + "("

                + RappiDBHelper.ENTITYID + " TEXT PRIMARY KEY, "
                + RappiDBHelper.ENTITYNAME + " TEXT, "
                + RappiDBHelper.ENTITYSUMMARY + " TEXT, "
                + RappiDBHelper.ENTITYRIGHTS + " TEXT, "
                + RappiDBHelper.ENTITYTITLE + " TEXT, "
                + RappiDBHelper.ENTITYCATEGORY + " TEXT );";

        String sqlImg="CREATE TABLE IF NOT EXISTS " + RappiDBHelper.TABLE_IMG_FEED + "("

                + RappiDBHelper.ENTITYID + " TEXT PRIMARY KEY, "
                + RappiDBHelper.IMG1 + " TEXT, "
                + RappiDBHelper.IMG2 + " TEXT, "
                + RappiDBHelper.IMG3 + " TEXT );";

        db.execSQL(sqlData);
        db.execSQL(sqlFeed);
        db.execSQL(sqlImg);
    }

    /***
     * Insert or update product of cart
     * @param feed
     * @return
     */

    public boolean insertDatas(EntityFeed feed){
        boolean res=false;
        String sql_qwery_data;
        SQLiteDatabase db=getWritableDatabase();


        sql_qwery_data="INSERT OR REPLACE INTO "+RappiDBHelper.TABLE_DATA +
                "("+RappiDBHelper.AUTHOR
                +", "+RappiDBHelper.RIGHTS
                +",  " +RappiDBHelper.TITLE
                +

                ")  VALUES('"+
                feed.getAuthor()+
                "'" + ",'"+feed.getRights() +
                "','" +feed.getTitle()+"')";





        if(db!=null){
            try {
                Log.e("insert Data >> ",sql_qwery_data);
                db.execSQL(sql_qwery_data);
                insertFeeds(feed.getEntrys());
                res=true;
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }else{
            res=false;
        }
        return res;
    }



    public boolean insertFeeds(ArrayList<EntityEntry>entrys){
        boolean res=false;
        String sql_qwery_feed="";
        SQLiteDatabase db=getWritableDatabase();


        for(int i=0; i<entrys.size(); i++){

            String summary=entrys.get(i).getEntitysummary().replace("'","");
            sql_qwery_feed="INSERT OR REPLACE INTO "+RappiDBHelper.TABLE_FEED +
                    "("+RappiDBHelper.ENTITYID
                    +", "+RappiDBHelper.ENTITYNAME
                    +", "+RappiDBHelper.ENTITYSUMMARY
                    +", "+RappiDBHelper.ENTITYRIGHTS
                    +",  " +RappiDBHelper.ENTITYTITLE
                    +", "+RappiDBHelper.ENTITYCATEGORY
                    +

                    ")  VALUES('"+
                    entrys.get(i).getEntityID()+"','"+
                    entrys.get(i).getEntityName()+"','"+
                    summary+"','"+
                    entrys.get(i).getEntityrights()+"','"+
                    entrys.get(i).getEntitytitle()+"','" +
                    entrys.get(i).getEntitycategory()+"')";

            if(db!=null){
                try {
                    Log.e("insert Feed >> ",sql_qwery_feed);
                    insertImgs(entrys.get(i));
                    db.execSQL(sql_qwery_feed);
                    res=true;
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }else{
                res=false;
            }
        }

        return res;
    }


    public boolean insertImgs(EntityEntry entry)
    {
        boolean res=false;
        String sql_qwery_img_feed="";
        SQLiteDatabase db=getWritableDatabase();

            sql_qwery_img_feed="INSERT OR REPLACE INTO "+RappiDBHelper.TABLE_IMG_FEED +

                    "("+RappiDBHelper.ENTITYID
                    +", "+RappiDBHelper.IMG1
                    +", "+RappiDBHelper.IMG2
                    +", "+RappiDBHelper.IMG3
                    +

                    ")  VALUES('"+
                    entry.getEntityID()+"','"+
                    entry.getImages().get(0)+"','"+
                    entry.getImages().get(1)+"','"+
                    entry.getImages().get(2)+"')";


            if(db!=null){
                try {
                    Log.e("insert Feed >> ",sql_qwery_img_feed);
                    db.execSQL(sql_qwery_img_feed);
                    res=true;
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }else{
                res=false;
                }


        return res;
    }


    public EntityFeed getFeeds(){
        EntityFeed feed = null;

        SQLiteDatabase db;
        db=getReadableDatabase();

        String qerty="SELECT *  FROM "+RappiDBHelper.TABLE_DATA;

        Cursor c=null;

        if(db!=null){
            c=db.rawQuery(qerty,null);
            if(c.moveToFirst()){
                do{
                    feed=new EntityFeed();
                    feed.setAuthor(c.getString(0));
                    feed.setRights(c.getString(1));
                    feed.setTitle(c.getString(2));

                }while(c.moveToNext());
            }
        }
        feed.setEntrys(getEntrys());
        db.close();
        return feed;
    }


    private ArrayList<EntityEntry>getEntrys(){
        ArrayList<EntityEntry> entrys=new ArrayList<>();
        EntityEntry entry=null;
        SQLiteDatabase db=getReadableDatabase();

        String qerty="SELECT *  FROM "+RappiDBHelper.TABLE_FEED;
        Cursor c=null;
        if(db!=null){
            c=db.rawQuery(qerty,null);
            if(c.moveToFirst()){

                do{
                    entry = new EntityEntry();
                    entry.setEntityID(c.getString(0));
                    entry.setEntityName(c.getString(1));
                    entry.setEntitysummary(c.getString(2));
                    entry.setEntityrights(c.getString(3));
                    entry.setEntitytitle(c.getString(4));
                    entry.setEntitycategory(c.getString(5));
                    entry.setImages(getImgs(c.getString(0)));
                    entrys.add(entry);
                }while(c.moveToNext());
            }
        }
        db.close();
        return entrys;
    }


    public ArrayList<String>getImgs(String id){
        ArrayList<String>datos=new ArrayList<>();

        SQLiteDatabase db;

        db=getReadableDatabase();

        String qerty="SELECT * FROM "+RappiDBHelper.TABLE_IMG_FEED + " WHERE "+RappiDBHelper.ENTITYID +" = '"+id+"'";

        Log.e("sql",qerty);
        Cursor c=null;

        if(db!=null){
            c=db.rawQuery(qerty,null);
            datos.clear();
            if(c.moveToFirst()){

                do{
                    datos.add(c.getString(1));
                    datos.add(c.getString(2));
                    datos.add(c.getString(3));


                    Log.d(TAG,datos.toString());
                }while(c.moveToNext());
            }
        }else{

        }
        db.close();
        return datos;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
