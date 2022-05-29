package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends BaseAdapter {

    Context context;
    List<Room> list;

    public RoomAdapter(Context context, List<Room> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Room getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListView_Row lv_row;

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.room_list_item, null);

            lv_row = new ListView_Row();
            lv_row.roomAvt = (ImageView) convertView.findViewById(R.id.room_avt);
            lv_row.type = (TextView) convertView.findViewById(R.id.txt_type);
            lv_row.availability = (TextView) convertView.findViewById(R.id.txt_available);
            lv_row.delete = (CheckBox) convertView.findViewById(R.id.chk_all_delete);

            convertView.setTag(lv_row);
        }else{
            lv_row = (ListView_Row) convertView.getTag();
        }

        lv_row.type.setText(list.get(position).getType() + " " + list.get(position).getId());
        if(list.get(position).available){
            lv_row.availability.setText("Status: Available" );
        }else{
            lv_row.availability.setText("Status: Booked" );
        }

        if(!list.get(position).available){
            lv_row.delete.setEnabled(false);
        }
        return convertView;
    }

    private class ListView_Row{
        public ImageView roomAvt;
        public TextView type;
        public TextView availability;
        public CheckBox delete;
    }
}
