package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailBooking extends AppCompatActivity {
    Customer customer;
    Room room;
    DBSimulator dbSimulator;
    private final int book_code = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_room_detail_layout);

        Bundle bundle = getIntent().getExtras();
        customer = (Customer) bundle.get("customer");
        room = (Room) bundle.get("room");
        dbSimulator = (DBSimulator) bundle.get("package");

        String name = room.getType();
        TextView nameView = (TextView) findViewById(R.id.tv_typename_room_de);
        nameView.setText(name);

        int bed = room.getBed();
        TextView bedView = (TextView) findViewById(R.id.tv_bed_de);
        bedView.setText("Bed: " + bed);

        int people = room.getPeople();
        TextView peopleView = (TextView) findViewById(R.id.tv_people_de);
        peopleView.setText("People: " + people);

        String status = (room.available ? "Available" : "Booked");
        TextView statusView = (TextView) findViewById(R.id.tv_status_de);
        statusView.setText("Status: " + status);

        int price = room.getPrice();
        TextView priceView = (TextView) findViewById(R.id.tv_price_de);
        priceView.setText("Price: $" + price);

        String imgsource = bundle.getString("imgsource");
        int img = Integer.parseInt(imgsource);
        ImageView imgView = (ImageView) findViewById(R.id.imageView_de);
        imgView.setImageResource(img);

        Button Btn_book_de = (Button) findViewById(R.id.btn_book_de);
        Btn_book_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(DetailBooking.this);
                ad.setTitle("Notification!");
                ad.setMessage("Are you sure want to book this room?");
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (room.available) {
                            for (Room r : dbSimulator.roomList) {
                                if (r.getId().equals(room.getId())) {
                                    r.setAvailable(false);
                                    customer.bookedList.add(r);
                                    break;
                                }
                            }
                            dbSimulator.totalBookings++;
                            dbSimulator.totalIncome += room.price;
                            Intent intent = new Intent();
                            intent.putExtra("package", dbSimulator);
                            intent.putExtra("customer", customer);
                            setResult(RESULT_OK, intent);
                            Toast.makeText(DetailBooking.this, "Booking Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(DetailBooking.this, "This room is already booked!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(DetailBooking.this, "Booking process cancelled!", Toast.LENGTH_SHORT).show();
                    }
                });
                ad.create().show();
            }
        });

        Button Btn_back_de = (Button) findViewById(R.id.btn_back_de);
        Btn_back_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homepage) {
            doOpenHomepageLayout();
            Toast.makeText(getApplicationContext(), "Homepage clicked", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.booking) {
            doOpenBookingLayout();
            Toast.makeText(getApplicationContext(), "Booking clicked", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.profile) {
            doOpenUserLayout();
            Toast.makeText(getApplicationContext(), "My Profile clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void doOpenHomepageLayout() {
        Intent homepageIntent = new Intent(this, Homepage.class);
        startActivity(homepageIntent);
    }

    public void doOpenBookingLayout() {
        Intent bookingIntent = new Intent(this, Booking.class);
        startActivity(bookingIntent);
    }

    public void doOpenUserLayout() {
        Intent userIntent = new Intent(this, CustomerControl.class);
        startActivity(userIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == book_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                customer = (Customer) data.getExtras().get("customer");

            }
        }
    }


}
