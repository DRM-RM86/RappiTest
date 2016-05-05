package com.apps.davidrm.rappitest.models;

import android.util.Log;

import com.apps.davidrm.rappitest.entities.EntityEntry;
import com.apps.davidrm.rappitest.entities.EntityFeed;
import com.apps.davidrm.rappitest.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DRM on 02/05/16.
 */
public class ParserMain {

    public static EntityFeed GetAllItems(String jsonResponse){
        EntityFeed entityFeed =new EntityFeed();

        try {

            JSONObject jsonFeed=new JSONObject(jsonResponse);
            JSONObject jsonItems=new JSONObject(jsonFeed.getString(Constants.FEED));


            JSONObject author=new JSONObject(jsonItems.getString(Constants.AUTHOR));
            entityFeed.setAuthor(author.getJSONObject(Constants.NAME).getString(Constants.LABEL));

            String title= new JSONObject(jsonItems.getString(Constants.TITLE)).getString(Constants.LABEL);
            String rights= new JSONObject(jsonItems.getString(Constants.RIGHTS)).getString(Constants.LABEL);

            JSONArray jsonArrayItems=new JSONArray(jsonItems.getString(Constants.ENTRY));
            JSONObject entityname;
            JSONArray entityimages;
            JSONObject entitysummary;
            JSONObject entitytitle;
            JSONObject entityrights;


            entityFeed.setTitle(title);
            entityFeed.setRights(rights);


            EntityEntry entry;
            ArrayList<EntityEntry>entries=new ArrayList<>();
            ArrayList<String>images;


            for(int i=0; i<jsonArrayItems.length(); i++){

                entry=new EntityEntry();
                images=new ArrayList<>();

                entityname=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.IMNAME));
                entry.setEntityName(entityname.getString(Constants.LABEL));


                entitysummary=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.SUMMARY));
                entry.setEntitysummary(entitysummary.getString(Constants.LABEL));


                entitytitle=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.TITLE));
                entry.setEntitytitle(entitytitle.getString(Constants.LABEL));



                entityrights=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.RIGHTS));
                entry.setEntityrights(entityrights.getString(Constants.LABEL));



                entityimages=new JSONArray(jsonArrayItems.getJSONObject(i).getString(Constants.IMIMAGE));
                for(int j=0; j<entityimages.length(); j++){
                    images.add(entityimages.getJSONObject(j).getString(Constants.LABEL));
                }



                JSONObject id=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.ID));
                JSONObject price=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.IMPRICE));
                entry.setEntityID(id.getJSONObject(Constants.ATTRIBUTES).getString(Constants.IMID));

                JSONObject category=new JSONObject(jsonArrayItems.getJSONObject(i).getString(Constants.CATEGORY));
                entry.setEntitycategory(category.getJSONObject(Constants.ATTRIBUTES).getString(Constants.LABEL));


                entry.setImages(images);
                entries.add(entry);
                entityFeed.setEntrys(entries);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return entityFeed;
    }
}
