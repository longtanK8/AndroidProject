package com.example.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminAvailableView extends AppCompatActivity {
    ListView roomView;
    List<Room> roomList;
    TextView title;
    RoomAdapter adapter;
    Button btnDelete;
    DBSimulator dbSimulator;
    final int detail_edit_code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_room_view);
        roomList = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();

        title = (TextView) findViewById(R.id.txt_view_title);
        title.setText("AVAILABLE ROOMS");
        Bundle extras = getIntent().getExtras();
        dbSimulator = (DBSimulator) extras.get("package");
        for (Room room : dbSimulator.getRoomList()) {
            if (room.available) {
                roomList.add(room);
            }
        }
        adapter = new RoomAdapter(AdminAvailableView.this, roomList);
        roomView = (ListView) findViewById(R.id.lv_all_room);
        roomView.setAdapter(adapter);
        btnDelete = (Button) findViewById(R.id.btn_all_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList = deleteHandle();
                for (int i = 0; i < tempList.size(); i++) {
                   for(Room r:dbSimulator.getRoomList()){
                       if(r.getId().equals(tempList.get(i))){
                           dbSimulator.roomList.remove(r);
                           roomList.remove(r);
                           adapter.notifyDataSetChanged();
                           break;
                       }
                   }
                }
            }
        });

        Button returnBtn = (Button) findViewById(R.id.btn_all_return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("package", dbSimulator);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        roomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent roomIntent = new Intent(AdminAvailableView.this, DetailRoomView.class);
                roomIntent.putExtra("room", roomList.get(i));
                startActivity(roomIntent);
            }
        });


    }

    public ArrayList<String> deleteHandle() {
        ArrayList<String> idList = new ArrayList<>();
        for (int i = roomView.getChildCount() - 1; i >= 0; i--) {
            View v = roomView.getChildAt(i);
            CheckBox chk_delete = (CheckBox) v.findViewById(R.id.chk_all_delete);
            if (chk_delete.isChecked()) {
                idList.add(roomList.get(i).getId());
                chk_delete.setChecked(false);
            }
        }
        return idList;

    }

}
