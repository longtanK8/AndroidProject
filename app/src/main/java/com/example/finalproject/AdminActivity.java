package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private static final int code = 0, add_code = 1;
    DBSimulator dbSimulator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);
        Bundle extras = getIntent().getExtras();
        dbSimulator = (DBSimulator) extras.get("package");
        List<Room> bookedList = new ArrayList<>();
        List<Room> availableList = new ArrayList<>();

        for(Room r:dbSimulator.roomList){
            if (r.available){
                availableList.add(r);
            }else{
                bookedList.add(r);
            }
        }
        Button btn_all_rooms = (Button) findViewById(R.id.btn_room);
        btn_all_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allRoom = new Intent(AdminActivity.this, AllRoomView.class);
                allRoom.putExtra("package", dbSimulator);
                startActivityForResult(allRoom, code);
            }
        });

        Button btn_add_room = (Button) findViewById(R.id.btn_addRoom);
        btn_add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRoomIntent = new Intent(AdminActivity.this, AdminAddRoom.class);
                addRoomIntent.putExtra("finalID", dbSimulator.getRoomList().get(dbSimulator.getRoomList().size() - 1).getId());
                startActivityForResult(addRoomIntent, add_code);
            }
        });

        Button btn_booked_room_view = (Button) findViewById(R.id.btn_booked_rooms);
        btn_booked_room_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allRoom = new Intent(AdminActivity.this, AdminBookedView.class);
                allRoom.putExtra("package", dbSimulator);
                startActivity(allRoom);
            }
        });

        Button btn_available_room_view = (Button) findViewById(R.id.btn_available_room);
        btn_available_room_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allRoom = new Intent(AdminActivity.this, AdminAvailableView.class);
                allRoom.putExtra("package", dbSimulator);
                startActivityForResult(allRoom, code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                dbSimulator = (DBSimulator) data.getExtras().get("package");

            }
        }
        else if(requestCode == add_code){
            if(resultCode == RESULT_OK){
                dbSimulator.roomList.add((Room)data.getExtras().get("newRoom"));
                Toast.makeText(AdminActivity.this, "Room added successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_admin) {
            Intent intent = new Intent();
            intent.putExtra("package", dbSimulator);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
