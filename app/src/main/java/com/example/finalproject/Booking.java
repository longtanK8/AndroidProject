package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Booking extends AppCompatActivity {
    private RecyclerView recyclerView;
    private final int profile_code = 0;
    DBSimulator dbSimulator;
    List<Room> rooms;
    Customer customer;

    RoomRecyclerViewAdapter rAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        Bundle bundle = getIntent().getExtras();
        dbSimulator = (DBSimulator) bundle.get("package");
        rooms = dbSimulator.getRoomList();
        customer = (Customer) bundle.get("customer");

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview_booking);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        rAdapter = new RoomRecyclerViewAdapter(this, rooms, Booking.this);
        recyclerView.setAdapter(rAdapter);

        rAdapter.customer = customer;
        rAdapter.dbSimulator = dbSimulator;



        EditText search = (EditText) findViewById(R.id.edt_search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                rAdapter.getFilter().filter(editable.toString());
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.homepage) {
            doOpenHomepageLayout();
            Toast.makeText(getApplicationContext(),"Homepage clicked", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.booking) {
            doOpenBookingLayout();
            Toast.makeText(getApplicationContext(),"Booking clicked", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.profile) {
            doOpenUserLayout();
        }
        return super.onOptionsItemSelected(item);
    }
    public void doOpenHomepageLayout(){
        Intent homepageIntent = new Intent();
        homepageIntent.putExtra("package", dbSimulator);
        setResult(RESULT_OK, homepageIntent);
        finish();
    }
    public void doOpenBookingLayout(){
        Toast.makeText(this, "You are at booking page!", Toast.LENGTH_SHORT).show();
    }

    public void doOpenUserLayout(){
        Toast.makeText(this, "This action cannot be performed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == profile_code) {
            if (resultCode == RESULT_OK) {

                dbSimulator = (DBSimulator) data.getExtras().get("package");
                customer = (Customer) data.getExtras().get("customer");

                for(int i = 0; i<dbSimulator.customerList.size(); i++){
                    Customer c = dbSimulator.customerList.get(i);
                    if(c.getId().equals(customer.getId())){
                        dbSimulator.customerList.remove(i);
                        dbSimulator.customerList.add(i, customer);
                        break;
                    }
                }

            }
        }

        dbSimulator.updateBooking();
        rooms = dbSimulator.getRoomList();
        rAdapter.dataChange(rooms);
    }

}
