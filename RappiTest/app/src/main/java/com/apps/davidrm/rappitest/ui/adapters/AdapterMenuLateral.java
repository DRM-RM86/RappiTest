package com.apps.davidrm.rappitest.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.davidrm.rappitest.R;
import com.apps.davidrm.rappitest.entities.menuLateral.MenuLateralSection;

import java.util.List;

/**
 * Created by DRM on 02/05/16.
 */
public class AdapterMenuLateral extends BaseAdapter{
    Context context;
    List<MenuLateralSection> menus;
    public void setData(Context context, List<MenuLateralSection> menus){
        this.menus=menus;
        this.context=context;

    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public MenuLateralSection getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.row_menu_lateral,parent,false);
            viewHolder=new ViewHolderItem();
            viewHolder.tx_row_item=(TextView)convertView.findViewById(R.id.tx_row_item);
            viewHolder.img_row_item=(ImageView)convertView.findViewById(R.id.img_row_item);
            convertView.setTag(viewHolder);
        }else{            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.tx_row_item.setText(menus.get(position).getTitle());
        viewHolder.img_row_item.setBackgroundResource(menus.get(position).getIcon());

        return convertView;
    }

    static class ViewHolderItem {
        TextView tx_row_item;
        ImageView img_row_item;
    }



}
