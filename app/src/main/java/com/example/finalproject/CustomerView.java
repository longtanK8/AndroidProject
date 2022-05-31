package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CustomerView extends AppCompatActivity {
    ListView customerView;
    List<Customer> customerList;
    TextView title;
    CustomerAdapter adapter;
    Button btnDelete;
    DBSimulator dbSimulator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_room_view);

        title = (TextView) findViewById(R.id.txt_view_title);
        Bundle extras = getIntent().getExtras();
        dbSimulator = (DBSimulator) extras.get("package");
        customerList = dbSimulator.customerList;
        adapter = new CustomerAdapter(CustomerView.this, customerList);
        customerView = (ListView) findViewById(R.id.lv_all_room);
        customerView.setAdapter(adapter);
        btnDelete = (Button) findViewById(R.id.btn_all_delete);
        title.setText("ALL CUSTOMERS");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHandle();
            }
        });

        Button returnBtn = (Button) findViewById(R.id.btn_all_return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("package", dbSimulator);
                setResult(RESULT_OK, intent);
                dbSimulator.setCustomerList(customerList);
                finish();
            }
        });
    }
    public void deleteHandle() {
        for (int i = customerView.getChildCount() - 1; i >=0; i--) {
            View v = customerView.getChildAt(i);
            CheckBox chk_delete = (CheckBox) v.findViewById(R.id.chk_customer_delete);
            if(chk_delete.isChecked()){
                chk_delete.setChecked(false);
                customerList.remove(i);
                adapter.notifyDataSetChanged();
            }
        }

    }
}
