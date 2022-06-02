package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class Homepage extends AppCompatActivity {
    Customer customer;
    private final int profile_code = 0, booking_code = 1;
    DBSimulator dbSimulator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        Button Btn_book_homepage = (Button) findViewById(R.id.btn_book_homepage);

        Bundle bundle = getIntent().getExtras();
        dbSimulator = (DBSimulator) bundle.get("package");
        customer = (Customer) bundle.get("customer");
        Btn_book_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOpenBookingLayout();
            }
        });

        Button logout = (Button) findViewById(R.id.btn_book_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("package", dbSimulator);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_profile) {
            doOpenUserLayout();
            Toast.makeText(getApplicationContext(), "Profile clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void doOpenBookingLayout() {
        Intent bookingIntent = new Intent(this, Booking.class);
        bookingIntent.putExtra("package", dbSimulator);
        bookingIntent.putExtra("customer", customer);
        startActivityForResult(bookingIntent, booking_code);
    }

    public void doOpenUserLayout() {
        Intent userIntent = new Intent(this, CustomerControl.class);
        userIntent.putExtra("customer", customer);
        startActivityForResult(userIntent, profile_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == profile_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                customer = (Customer) data.getExtras().get("customer");
                for (int i = 0; i < dbSimulator.customerList.size(); i++) {
                    Customer c = dbSimulator.customerList.get(i);
                    if(c.getId().equals(customer.getId())){
                        dbSimulator.customerList.remove(i);
                        dbSimulator.customerList.add(i, customer);
                        break;
                    }
                }
            }
        }
        if(requestCode == booking_code){
            if(resultCode == RESULT_OK){
                dbSimulator = (DBSimulator) data.getExtras().get("package");
            }
        }
    }
}