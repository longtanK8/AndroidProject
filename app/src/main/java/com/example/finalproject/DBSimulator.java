package com.example.finalproject;

import android.os.Bundle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DBSimulator implements Serializable {
    List<Room> roomList;
    List<Customer> customerList;
    int totalCustomer = 0;
    int totalBookings = 0;
    int bookedRooms = 0;
    int availableRooms = 0;
    long totalIncome = 0;


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
        c.setUserName("long");
        c.setPassword("long");
        Customer c2 = new Customer("002", "Danh Dang", "danh@gmail.com", d, "034031434623");
        Customer c3 = new Customer("003", "Thanh Tien", "tien@gmail.com", d, "054676287434");
        for (int i = 0; i < 50; i++) {
//            boolean temp;
//            if (i % 2 == 0) {
//                temp = true;
//            } else {
//                temp = false;
//            }
            roomList.add(new Room("00" + i, random[r.nextInt(4)], true));
            switch (roomList.get(i).getType()) {
                case "Deluxe":
                    roomList.get(i).setPrice(10);
                    roomList.get(i).setPeople(2);
                    roomList.get(i).setBed(2);
                    break;
                case "BusinessSuite":
                    roomList.get(i).setPrice(30);
                    roomList.get(i).setPeople(3);
                    roomList.get(i).setBed(2);
                    break;
                case "Junior":
                    roomList.get(i).setPrice(50);
                    roomList.get(i).setPeople(4);
                    roomList.get(i).setBed(4);
                    break;
                case "Superior":
                    roomList.get(i).setPrice(70);
                    roomList.get(i).setPeople(4);
                    roomList.get(i).setBed(4);
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
        List<Room> bookedList = new ArrayList<>();
        for(Room room : roomList){
            room.setAvailable(true);
            room.booker = null;
        }
        for(Customer cus : customerList){
            for(int i = 0 ; i < cus.bookedList.size(); i++){
                cus.bookedList.get(i).booker = cus;
                bookedList.add(cus.bookedList.get(i));
            }
        }
        Collections.sort(bookedList, (Room i1, Room i2) ->{
            return Long.compare(Long.parseLong(i1.getId()), Long.parseLong(i2.getId()));
        });
        for(Room room: bookedList){
            int i = 0;
            for(; i<roomList.size(); i++){
                if(roomList.get(i).getId().equals(room.getId())){
                    roomList.get(i).setAvailable(false);
                    roomList.get(i).booker = room.booker;
                    i++;
                    break;
                }
            }
        }
        bookedRooms = bookedList.size();
        availableRooms = roomList.size() - bookedList.size();
        totalCustomer = customerList.size();
        totalBookings = totalBookings == 0? bookedRooms : totalBookings;
        if(totalIncome == 0){
            for (Room r: bookedList){
                totalIncome+=r.price;
            }
        }
    }
}
