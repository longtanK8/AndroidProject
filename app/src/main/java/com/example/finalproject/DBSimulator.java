package com.example.finalproject;

import android.os.Bundle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DBSimulator implements Serializable {
    List<Room> roomList;
    List<Customer> customerList;


    public DBSimulator() {
        Random r = new Random(3);
        String[] random = new String[4];
        random[0] = "Deluxe";
        random[1] = "BusinessSuite";
        random[2] = "Junior";
        random[3] = "Superior";
        roomList = new ArrayList<>();
        customerList = new ArrayList<>();
        Date d = new Date();
        try{
            SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy");
            d = fm.parse("12/01/2000");
        }catch (Exception e){
            d = d;
        }


        Customer c = new Customer("001", "Long Truong", "long@gmail.com", d, "023213214112");
        customerList.add(c);

        for (int i = 0; i < 30; i++) {
            boolean temp;
            if (i % 2 == 0) {
                temp = true;
            } else {
                temp = false;
            }
            roomList.add(new Room("00" + i, random[r.nextInt(3)], temp));
            switch (roomList.get(i).getType()) {
                case "Deluxe":
                    roomList.get(i).setPrice(10);
                    break;
                case "BusinessSuite":
                    roomList.get(i).setPrice(30);
                    break;
                case "Junior":
                    roomList.get(i).setPrice(50);
                    break;
                case "Superior":
                    roomList.get(i).setPrice(70);
                    break;
            }
        }
    }

    public ArrayList<Room> getRoomList() {
        return (ArrayList<Room>) roomList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
