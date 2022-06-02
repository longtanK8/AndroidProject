package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminAddRoom extends AppCompatActivity {
    EditText id, type, price, description;
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_room_view);

        title = (TextView) findViewById(R.id.txt_title_add);
        title.setText("ADD NEW ROOM");
        id = (EditText) findViewById(R.id.edt_add_id);
        type = (EditText) findViewById(R.id.edt_add_type);
        price = (EditText) findViewById(R.id.edt_add_price);
        description = (EditText) findViewById(R.id.edt_add_description);
        Bundle bundle = getIntent().getExtras();

        long tempID = Long.parseLong((String) bundle.get("finalID"));
        id.setText("00" + (tempID + 1));

        Button btnAdd = (Button) findViewById(R.id.btn_add_room_add);
        btnAdd.setText("ADD");
        Button btnReturn = (Button) findViewById(R.id.btn_add_room_return);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeIn, priceIn, descIn;
                typeIn = type.getText().toString();
                priceIn = price.getText().toString();
                descIn = description.getText().toString();
                if (typeIn.equals("") || priceIn.equals("") || descIn.equals("")) {
                    Toast.makeText(AdminAddRoom.this, "Data not filled!", Toast.LENGTH_SHORT).show();
                }else{
                    Room nRoom = new Room("00" + (tempID + 1),typeIn, true);
                    nRoom.setPrice(Integer.parseInt(priceIn));
                    nRoom.setDescription(descIn);
                    Intent intent = new Intent();
                    intent.putExtra("newRoom", nRoom);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
