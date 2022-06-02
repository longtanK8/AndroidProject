package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class CustomerControl extends AppCompatActivity {
    private final int edit_code = 0;
    TextView tv_name, tv_id,  tv_date, tv_mail,  tv_phone;
    Customer customer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_layout);
        Bundle bundle = getIntent().getExtras();
        customer = (Customer) bundle.get("customer");

        tv_name = (TextView) findViewById(R.id.tv_username);
        tv_id = (TextView) findViewById(R.id.tv_user_id);
        tv_date = (TextView) findViewById(R.id.tv_user_date);
        tv_mail = (TextView) findViewById(R.id.tv_user_mail);
        tv_phone = (TextView) findViewById(R.id.tv_user_phone);

//        String name = intent.getStringExtra("cusEditName");
//        String email = intent.getStringExtra("cusEditEmail");
//        String date = intent.getStringExtra("cusEditDate");
//        String phone = intent.getStringExtra("cusEditPhone");
//
//        customer.setName(name);
//        customer.setBirthDate(date);
//        customer.setEmail(email);
//        customer.setPhoneNum(phone);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        tv_name.setText(customer.getName());
        tv_id.setText(customer.getId());
        tv_date.setText(format.format(customer.getBirthDate().getTime()));
        tv_mail.setText(customer.getEmail());
        tv_phone.setText(customer.getPhoneNum());

        Button Btn_back = (Button) findViewById(R.id.btn_user_back);
        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent();
                backIntent.putExtra("customer", customer);
                setResult(RESULT_OK, backIntent);
                finish();
            }
        });
        Button Btn_edit = (Button) findViewById(R.id.btn_edit_user);
        Btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userEditUser = new Intent(CustomerControl.this, EditCustomer.class);
                userEditUser.putExtra("customer", customer);
                startActivityForResult(userEditUser, edit_code);
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
        }
        if(id == R.id.booking) {
            doOpenBookingLayout();
        }
        if(id == R.id.profile) {
            doOpenUserLayout();
        }
        return super.onOptionsItemSelected(item);
    }
    public void doOpenHomepageLayout(){
        Toast.makeText(this, "Cannot perform this action!", Toast.LENGTH_SHORT).show();
    }
    public void doOpenBookingLayout(){
        Toast.makeText(this, "You are at profile page!", Toast.LENGTH_SHORT).show();
    }

    public void doOpenUserLayout(){
        Toast.makeText(this, "Cannot perform this action!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == edit_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                customer = (Customer) data.getExtras().get("customer");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                tv_name.setText(customer.getName());
                tv_id.setText(customer.getId());
                tv_date.setText(format.format(customer.getBirthDate().getTime()));
                tv_mail.setText(customer.getEmail());
                tv_phone.setText(customer.getPhoneNum());

            }
        }
    }
}
