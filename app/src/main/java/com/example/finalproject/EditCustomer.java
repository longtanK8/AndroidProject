package com.example.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditCustomer extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText dateEdittext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_edit_layout);
        Button Btn_edit_submit = (Button) findViewById(R.id.btn_edit_submit);
        Bundle bundle = getIntent().getExtras();
        Customer customer = (Customer) bundle.get("customer");

        String name = customer.getName();
        EditText nameEdittext = (EditText)findViewById(R.id.edt_customer_edit_name);
        nameEdittext.setText(name);

        String email = customer.getEmail();
        EditText emailEdittext = (EditText)findViewById(R.id.edt_customer_edit_mail);
        emailEdittext.setText(email);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        String date = dateFormat.format(customer.getBirthDate().getTime());
        dateEdittext = (EditText)findViewById(R.id.edt_customer_edit_date);
        dateEdittext.setText(date);

        dateEdittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        String phone = customer.getPhoneNum();
        EditText phoneEdittext = (EditText)findViewById(R.id.edt_customer_edit_phone);
        phoneEdittext.setText(phone);

        Btn_edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdittext.getText().toString();
                String email = emailEdittext.getText().toString();
                String date = dateEdittext.getText().toString();
                String phone = phoneEdittext.getText().toString();

                if(name.equals("") || email.equals("") || date.equals("") || phone.equals("")){
                    Toast.makeText(EditCustomer.this, "Information not filled!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(EditCustomer.this, "Profile Edited Successfully!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(EditCustomer.this, "Wrong date format", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        Button Btn_edit_back = (Button) findViewById(R.id.btn_edit_back);
        Btn_edit_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(EditCustomer.this, "Edit cancelled!", Toast.LENGTH_SHORT).show();
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
}
