package com.apps.davidrm.rappitest.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DRM on 02/05/16.
 */
public class EntityEntry implements Serializable{

    private String EntityName;
    private String Entitysummary;
    private String Entityrights;
    private String Entitytitle;
    private String Entitycategory;

    public String getEntitycategory() {
        return Entitycategory;
    }

    public void setEntitycategory(String entitycategory) {
        Entitycategory = entitycategory;
    }

    public String getEntityID() {
        return EntityID;
    }

    public void setEntityID(String entityID) {
        EntityID = entityID;
    }

    private String EntityID;
    private ArrayList<String>images;

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getEntityName() {
        return EntityName;
    }

    public void setEntityName(String entityName) {
        EntityName = entityName;
    }

    public String getEntitysummary() {
        return Entitysummary;
    }

    public void setEntitysummary(String entitysummary) {
        Entitysummary = entitysummary;
    }

    public String getEntityrights() {
        return Entityrights;
    }

    public void setEntityrights(String entityrights) {
        Entityrights = entityrights;
    }

    public String getEntitytitle() {
        return Entitytitle;
    }

    public void setEntitytitle(String entitytitle) {
        Entitytitle = entitytitle;
    }
}
