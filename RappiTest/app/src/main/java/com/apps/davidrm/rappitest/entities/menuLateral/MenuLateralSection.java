package com.apps.davidrm.rappitest.entities.menuLateral;

import android.content.Context;

import com.apps.davidrm.rappitest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DRM on 03/05/16.
 */
public class MenuLateralSection {

    private MenuLateralType type;
    private String title;
    private int icon;

    public MenuLateralType getType() {
        return type;
    }

    public void setType(MenuLateralType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public static class MenuBuilder{
        public static List<MenuLateralSection> build(Context context){
            List<MenuLateralSection> sections = new ArrayList<>();

            MenuLateralSection section = new MenuLateralSection();


            section.setTitle(context.getResources().getString(R.string.title_init));
            section.setType(MenuLateralType.INICIO);
            section.setIcon(R.mipmap.inicio);
            sections.add(section);

            section = new MenuLateralSection();
            section.setTitle(context.getResources().getString(R.string.title_info));
            section.setType(MenuLateralType.INFORMATION);
            section.setIcon(R.mipmap.info_menu);
            sections.add(section);

            return sections;
        }
    }
}
