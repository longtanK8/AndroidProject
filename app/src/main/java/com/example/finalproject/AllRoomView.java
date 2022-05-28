package com.example.finalproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRoomView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random r = new Random(3);
        String[] random = new String[4];
        random[0] = "Deluxe";
        random[1] = "BusinessSuite";
        random[2] = "Junior";
        random[3] = "Superior";
        setContentView(R.layout.admin_all_room_view);
        List<Room> roomList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            roomList.add(new Room("00" + i, random[r.nextInt(3)], true));
        }
        RoomApdater adapter = new RoomApdater(AllRoomView.this, roomList);
        ListView roomView = (ListView) findViewById(R.id.lv_all_room);
        roomView.setAdapter(adapter);
    }
}
