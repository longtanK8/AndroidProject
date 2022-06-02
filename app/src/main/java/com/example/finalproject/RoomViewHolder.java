package com.example.finalproject;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoomViewHolder extends RecyclerView.ViewHolder{
    ImageView roomTypeView;
    TextView roomNameView;
    TextView priceView;
    TextView bedView;
    TextView statusView;
    TextView peopleView;
    private Booking booking;

    public RoomViewHolder(@NonNull View itemView, Booking booking) {
        super(itemView);
        this.booking = booking;
        this.roomTypeView = (ImageView) itemView.findViewById(R.id.img_typeroom);
        this.roomNameView = (TextView) itemView.findViewById(R.id.tv_typeroom_payment_name);
        this.priceView = (TextView) itemView.findViewById(R.id.tv_price);
        this.bedView = (TextView) itemView.findViewById(R.id.tv_bed);
        this.statusView = (TextView) itemView.findViewById(R.id.tv_status);
        this.peopleView = (TextView) itemView.findViewById(R.id.tv_people);
    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(view.getContext(), DetailBooking.class);
//        view.getContext().start
//    }
}
