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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AdminAddCustomer extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText fullName, phone, email, birthdate;
    Button confirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account_information);

        fullName = (EditText) findViewById(R.id.edt_infor_fullname);
        phone = (EditText) findViewById(R.id.edt_infor_phone);
        email = (EditText) findViewById(R.id.edt_infor_email);
        birthdate = (EditText) findViewById(R.id.edt_infor_birthdate);
        confirm = (Button) findViewById(R.id.btn_infor_confirm);

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        Bundle bundle = getIntent().getExtras();
        DBSimulator dbSimulator = (DBSimulator) bundle.get("package");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer;
                String fname = fullName.getText().toString();
                String phoneNum = phone.getText().toString();
                String mail = email.getText().toString();
                String tempDate = birthdate.getText().toString();
                Date birth = new Date();

                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    birth = format.parse(tempDate);
                } catch (Exception e) {
                    Toast.makeText(AdminAddCustomer.this, "Wrong date format input!", Toast.LENGTH_SHORT).show();
                }

                if (fname.equals("") || phoneNum.equals("") || mail.equals("") || tempDate.equals("")) {
                    Toast.makeText(AdminAddCustomer.this, "Please fill in all information!", Toast.LENGTH_SHORT).show();
                } else {
                    customer = new Customer("cus" + phoneNum + tempDate.split("/")[0], fname, mail, birth, phoneNum);
                    dbSimulator.customerList.add(customer);
                    Toast.makeText(AdminAddCustomer.this, "Information filled in successfully!", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("package", dbSimulator);
                    setResult(RESULT_OK, returnIntent);
                    finish();
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
        birthdate.setText(dateFormat.format(calendar.getTime()));
    }
}
