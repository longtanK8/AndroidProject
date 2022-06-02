package com.example.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdminEditCustomer extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText dateEdittext;
    RoomAdapter adapter;
    ListView roomView;
    List<Room> roomList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit_customer);
        Button submit = (Button) findViewById(R.id.btn_admin_edit_submit);
        Button cancel = (Button) findViewById(R.id.btn_admin_edit_cancel);
        Button checkout = (Button) findViewById(R.id.btn_admin_edit_checkout);

        Bundle bundle = getIntent().getExtras();
        Customer customer = (Customer) bundle.get("customer");
        roomList = customer.bookedList;

        adapter = new RoomAdapter(AdminEditCustomer.this, roomList);
        roomView = (ListView) findViewById(R.id.lv_admin_edit_customer);
        roomView.setAdapter(adapter);

        String name = customer.getName();
        EditText nameEdittext = (EditText)findViewById(R.id.edt_admin_edit_name);
        nameEdittext.setText(name);

        String email = customer.getEmail();
        EditText emailEdittext = (EditText)findViewById(R.id.edt_admin_edit_email);
        emailEdittext.setText(email);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        String date = dateFormat.format(customer.getBirthDate().getTime());
        dateEdittext = (EditText)findViewById(R.id.edt_admin_edit_date);
        dateEdittext.setText(date);

        dateEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        String phone = customer.getPhoneNum();
        EditText phoneEdittext = (EditText)findViewById(R.id.edt_admin_edit_phone);
        phoneEdittext.setText(phone);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdittext.getText().toString();
                String email = emailEdittext.getText().toString();
                String date = dateEdittext.getText().toString();
                String phone = phoneEdittext.getText().toString();

                if(name.equals("") || email.equals("") || date.equals("") || phone.equals("")){
                    Toast.makeText(AdminEditCustomer.this, "Information not filled!", Toast.LENGTH_SHORT).show();
                }
                else{
                    customer.setName(name);
                    customer.setEmail(email);
                    try{
                        customer.setBirthDate(format.parse(date));
                        customer.setPhoneNum(phone);
                        Intent confirmIntent = new Intent();
                        confirmIntent.putExtra("customer", customer);
                        setResult(RESULT_OK, confirmIntent);
                        finish();
                        Toast.makeText(AdminEditCustomer.this, "Profile Edited Successfully!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(AdminEditCustomer.this, "Wrong date format", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(AdminEditCustomer.this, "Edit cancelled!", Toast.LENGTH_SHORT).show();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> tempList = new ArrayList<>();
                tempList = deleteHandle();
                for (int i = 0; i < tempList.size(); i++) {
                    for(Room r: roomList){
                        if(r.getId().equals(tempList.get(i))){
                            roomList.remove(r);
                            adapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateEdittext.setText(dateFormat.format(calendar.getTime()));
    }

    public ArrayList<String> deleteHandle() {
        ArrayList<String> idList = new ArrayList<>();
        for (int i = roomView.getChildCount() - 1; i >= 0; i--) {

            View v = roomView.getChildAt(i);
            CheckBox chk_delete = (CheckBox) v.findViewById(R.id.chk_all_delete);
            chk_delete.setEnabled(true);
            if (chk_delete.isChecked()) {
                idList.add(roomList.get(i).getId());
                chk_delete.setChecked(false);
            }
        }
        return idList;

    }
}