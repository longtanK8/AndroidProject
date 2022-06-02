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
        Customer c2 = new Customer("002", "Danh Dang", "danh@gmail.com", d, "034031434623");
        Customer c3 = new Customer("003", "Thanh Tien", "tien@gmail.com", d, "054676287434");
        for (int i = 0; i < 30; i++) {
//            boolean temp;
//            if (i % 2 == 0) {
//                temp = true;
//            } else {
//                temp = false;
//            }
            roomList.add(new Room("00" + i, random[r.nextInt(3)], true));
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

        c.bookedList.add(roomList.get(1));
        c.bookedList.add(roomList.get(12));
        c.bookedList.add(roomList.get(23));
        c.bookedList.add(roomList.get(27));
        c2.bookedList.add(roomList.get(13));
        c2.bookedList.add(roomList.get(26));
        c2.bookedList.add(roomList.get(14));
        c3.bookedList.add(roomList.get(3));
        c3.bookedList.add(roomList.get(19));
        c3.bookedList.add(roomList.get(22));
        customerList.add(c);
        customerList.add(c2);
        customerList.add(c3);

        for(Customer cus : customerList){
            for(int i = 0 ; i < cus.bookedList.size(); i++){
                for(Room room : roomList){
                    if(room.getId().equals(cus.bookedList.get(i).getId())){
                        room.setAvailable(false);
                        room.booker = cus;
                        break;
                    }
                }
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

    public void updateBooking(){
        for(Customer cus : customerList){
            for(int i = 0 ; i < cus.bookedList.size(); i++){
                for(Room room : roomList){
                    if(room.getId().equals(cus.bookedList.get(i).getId())){
                        room.setAvailable(false);
                        room.booker = cus;
                        break;
                    }else{
                        room.setAvailable(true);
                    }
                }
            }
        }
    }
}
