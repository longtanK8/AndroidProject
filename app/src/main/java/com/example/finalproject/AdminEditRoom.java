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

public class AdminEditRoom extends AppCompatActivity {
    EditText id, type, price, description;
    TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_room_view);

        Button btnAdd = (Button) findViewById(R.id.btn_add_room_add);
        Button btnReturn = (Button) findViewById(R.id.btn_add_room_return);

        Bundle bundle = getIntent().getExtras();
        Room room = (Room) bundle.get("room");

        title = (TextView) findViewById(R.id.txt_title_add);
        title.setText("EDIT ROOM");
        id = (EditText) findViewById(R.id.edt_add_id);
        type = (EditText) findViewById(R.id.edt_add_type);
        price = (EditText) findViewById(R.id.edt_add_price);
        description = (EditText) findViewById(R.id.edt_add_description);

        id.setText(room.getId());
        type.setText(room.getType());
        price.setText(room.getPrice()+"");
        description.setText(room.getDescription());

        btnAdd.setText("CONFIRM");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rType, rPrice, rDescription;
                rType = type.getText().toString();
                rPrice = price.getText().toString();
                rDescription = description.getText().toString();

                if(rType.equals("") || rPrice.equals("")){
                    Toast.makeText(AdminEditRoom.this, "Data is not filled in!", Toast.LENGTH_SHORT).show();
                }else{
                    room.setType(rType);
                    room.setPrice(Integer.parseInt(rPrice));
                    room.setDescription(rDescription);

                    Intent intent = new Intent();
                    intent.putExtra("room", room);
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AdminEditRoom.this, "Room information changed successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminEditRoom.this, "Changing process cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
