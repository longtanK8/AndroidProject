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

public class AllRoomView extends AppCompatActivity {
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

        title = (TextView) findViewById(R.id.txt_view_title);
        Bundle extras = getIntent().getExtras();
        dbSimulator = (DBSimulator) extras.get("package");
        roomList = dbSimulator.getRoomList();
        adapter = new RoomAdapter(AllRoomView.this, roomList);
        roomView = (ListView) findViewById(R.id.lv_all_room);
        roomView.setAdapter(adapter);
        btnDelete = (Button) findViewById(R.id.btn_all_delete);
        title.setText("ALL ROOMS");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHandle();
            }
        });

        Button returnBtn = (Button) findViewById(R.id.btn_all_return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("package", dbSimulator);
                setResult(RESULT_OK, intent);
                dbSimulator.setRoomList(roomList);
                finish();
            }
        });

        roomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AllRoomView.this, ""+roomList.get(i).getType(), Toast.LENGTH_SHORT).show();
                Intent roomIntent = new Intent(AllRoomView.this, DetailRoomView.class);
                roomIntent.putExtra("room", roomList.get(i));
                startActivityForResult(roomIntent, detail_edit_code);
            }
        });



        roomView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

    }

    public void deleteHandle() {
        for (int i = roomView.getChildCount() - 1; i >=0; i--) {
            View v = roomView.getChildAt(i);
            CheckBox chk_delete = (CheckBox) v.findViewById(R.id.chk_all_delete);
            if(chk_delete.isChecked()){
                chk_delete.setChecked(false);
                roomList.remove(i);
                adapter.notifyDataSetChanged();
            }
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == detail_edit_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                Room r = (Room) data.getExtras().get("room");
                for(int i = 0; i<dbSimulator.roomList.size(); i++){
                    Room room = dbSimulator.roomList.get(i);
                    if(r.getId().equals(room.getId())){
                        dbSimulator.roomList.remove(i);
                        dbSimulator.roomList.add(i, r);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }
    }

}
