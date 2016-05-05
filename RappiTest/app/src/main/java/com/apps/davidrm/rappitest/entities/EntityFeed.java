package com.apps.davidrm.rappitest.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DRM on 02/05/16.
 */
public class EntityFeed implements Serializable {
    private String  author;
    private String rights;
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }




    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    ArrayList<EntityEntry>Entrys;

    public ArrayList<EntityEntry> getEntrys() {
        return Entrys;
    }

    public void setEntrys(ArrayList<EntityEntry> entrys) {
        Entrys = entrys;
    }
}
