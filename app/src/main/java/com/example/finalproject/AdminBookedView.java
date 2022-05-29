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

public class AdminBookedView extends AppCompatActivity {
    ListView roomView;
    TextView title;
    List<Room> roomList;
    RoomAdapter adapter;
    Button btnDelete;
    DBSimulator dbSimulator;
    final int detail_edit_code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_room_view);
        roomList = new ArrayList<>();

        title = (TextView) findViewById(R.id.txt_view_title);
        Bundle extras = getIntent().getExtras();
        title.setText("BOOKED ROOMS");
        dbSimulator = (DBSimulator) extras.get("package");
        for(Room room : dbSimulator.getRoomList()){
            if (!room.available){
                roomList.add(room);
            }
        }

        adapter = new RoomAdapter(AdminBookedView.this, roomList);
        roomView = (ListView) findViewById(R.id.lv_all_room);
        roomView.setAdapter(adapter);
        btnDelete = (Button) findViewById(R.id.btn_all_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminBookedView.this, "Booked rooms cannot be removed", Toast.LENGTH_SHORT).show();
            }
        });

        Button returnBtn = (Button) findViewById(R.id.btn_all_return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        roomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AdminBookedView.this, "Booked rooms cannot be removed", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
