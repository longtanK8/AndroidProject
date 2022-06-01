package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomerAdapter extends BaseAdapter {
    Context context;
    List<Customer> list;

    public CustomerAdapter(Context context, List<Customer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListView_Row lv_row;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.customer_list_item, null);

            lv_row = new ListView_Row();
            lv_row.customerAvt = (ImageView) view.findViewById(R.id.customer_avt);
            lv_row.name = (TextView) view.findViewById(R.id.txt_customer_name);
            lv_row.totalSpend = (TextView) view.findViewById(R.id.txt_customer_spending);
            lv_row.delete = (CheckBox) view.findViewById(R.id.chk_customer_delete);

            view.setTag(lv_row);
        } else {
            lv_row = (ListView_Row) view.getTag();
        }

        lv_row.name.setText(list.get(i).getName());
        long spend = 0;
        List<Room> bookedList = list.get(i).getBookedList();

        for (int j = 0; j < bookedList.size(); j++) {
            spend += bookedList.get(j).price;
        }
        if (spend == 0) {
            lv_row.totalSpend.setText("No rooms booked!");
        } else {
            lv_row.totalSpend.setText("Total spending: $" + spend);
        }


        return view;
    }

    private class ListView_Row {
        public ImageView customerAvt;
        public TextView name;
        public TextView totalSpend;
        public CheckBox delete;
    }
}
