package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminProfitsView extends AppCompatActivity {
    EditText totalCustomer, totalBookings, bookedRooms, availableRooms, totalIncome;
    Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_profits_view);

        totalCustomer = (EditText) findViewById(R.id.edt_profits_customer);
        totalBookings = (EditText) findViewById(R.id.edt_profits_total_booking);
        bookedRooms = (EditText) findViewById(R.id.edt_profits_booked);
        availableRooms = (EditText) findViewById(R.id.edt_profits_available);
        totalIncome = (EditText) findViewById(R.id.edt_profits_income);
        confirm = (Button) findViewById(R.id.btn_profits_confirm);

        Bundle bundle = getIntent().getExtras();
        DBSimulator dbSimulator = (DBSimulator) bundle.get("package");
        totalCustomer.setText(dbSimulator.totalCustomer + "");
        totalBookings.setText(dbSimulator.totalBookings + "");
        bookedRooms.setText(dbSimulator.bookedRooms + "");
        availableRooms.setText(dbSimulator.availableRooms + "");
        totalIncome.setText("$ " + dbSimulator.totalIncome);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
