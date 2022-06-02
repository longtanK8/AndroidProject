package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomViewHolder> implements Filterable {
    private List<Room> rooms;
    public DBSimulator dbSimulator;
    private Context context;
    public Customer customer;
    List<Room> roomsFilter = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private Booking booking;

    public RoomRecyclerViewAdapter(Context context, List<Room> rooms, Booking booking) {
        this.booking = booking;
        this.rooms = rooms;
        this.roomsFilter = rooms;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.room_item_layout,parent,false);
        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               eventClick((RecyclerView) parent, view);
            }
        });
        return new RoomViewHolder(recyclerViewItem, booking);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = this.rooms.get(position);

        int imageResId = this.getDrawableResIdByName(room.getType().toLowerCase());
        holder.roomTypeView.setImageResource(imageResId);
        holder.roomNameView.setText(room.getType());
        holder.bedView.setText("Bed: " + room.getBed());
        holder.peopleView.setText("People: " + room.getPeople());
        holder.statusView.setText("Status: " + (room.isAvailable()?"Avail": "Booked"));
        holder.priceView.setText("Price: " + room.getPrice());
    }


    private void eventClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Room room = this.rooms.get(itemPosition);
        Intent intent = new Intent(booking, DetailBooking.class);
        intent.putExtra("customer", customer);
        intent.putExtra("room", room);
        intent.putExtra("package", dbSimulator);

        int imageResId = this.getDrawableResIdByName(room.getType().toLowerCase());
        String img = Integer.toString(imageResId);
        intent.putExtra("imgsource", img);
        booking.startActivityForResult(intent, 0);
    }


    public int getDrawableResIdByName(String name) {
        String pkgName = context.getPackageName();
        int resID =  context.getResources().getIdentifier(name, "drawable", pkgName);
        return resID;
    }
    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public void dataChange(List<Room> rooms){
        this.rooms = rooms;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.values = roomsFilter;
                    filterResults.count = roomsFilter.size();
                }else {
                    String search = charSequence.toString().toLowerCase();
                    ArrayList<Room> rooms = new ArrayList<>();
                    for (Room room: roomsFilter){
                        if (room.getType().toLowerCase().contains(search)){
                            rooms.add(room);
                        }
                    }
                    filterResults.values = rooms;
                    filterResults.count = rooms.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                rooms = (ArrayList<Room>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
