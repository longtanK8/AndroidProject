package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailRoomView extends AppCompatActivity {

    TextView id, type, price, description;
    Button btn_return, btn_edit;
    final private int edit_code = 1;
    Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail_view);

        id = (TextView) findViewById(R.id.txt_room_id);
        type = (TextView) findViewById(R.id.txt_room_type);
        price = (TextView) findViewById(R.id.txt_room_price);
        description = (TextView) findViewById(R.id.txt_room_description);

        Bundle bundle = getIntent().getExtras();
        room = (Room) bundle.get("room");
        id.setText(room.getId());
        type.setText(room.getType());
        price.setText("" + room.getPrice());
        description.setText(room.getDescription());

        btn_return = (Button) findViewById(R.id.btn_detail_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("room", room);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn_edit = (Button) findViewById(R.id.btn_detail_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!room.available) {
                    Toast.makeText(DetailRoomView.this, "This room is not available to edit!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent editIntent = new Intent(DetailRoomView.this, AdminEditRoom.class);
                    editIntent.putExtra("room", room);
                    startActivityForResult(editIntent, edit_code);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == edit_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                room = (Room) data.getExtras().get("room");
                type.setText(room.getType());
                price.setText(room.getPrice() + "");
                description.setText(room.getDescription());

            }
        }
    }
}
