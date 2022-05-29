package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailRoomView extends AppCompatActivity {

    TextView id, type, price, description;
    Button btn_return;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_detail_view);

        id = (TextView) findViewById(R.id.txt_room_id);
        type = (TextView) findViewById(R.id.txt_room_type);
        price = (TextView) findViewById(R.id.txt_room_price);
        description = (TextView) findViewById(R.id.txt_room_description);

        Bundle bundle = getIntent().getExtras();
        Room data = (Room) bundle.get("room");
        id.setText(data.getId());
        type.setText(data.getType());
        price.setText(data.getPrice());
        description.setText(data.getDescription());

        btn_return = (Button) findViewById(R.id.btn_detail_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
