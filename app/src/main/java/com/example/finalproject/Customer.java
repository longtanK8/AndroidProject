package com.example.finalproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Customer implements Serializable {
    private String id = "";
    private String name = "";
    private String email = "";
    private Date birthDate = null;
    private String phoneNum = "";
    private String userName = "";
    private String password = "";
    ArrayList<Room> bookedList;

    public Customer(String id, String name, String email, Date birthDate, String phoneNum) {
        bookedList = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
    }

    public Customer(String userName, String password) {
        bookedList = new ArrayList<>();
        this.userName = userName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public ArrayList<Room> getBookedList() {
        return bookedList;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void addRoom(Room room) {
        this.bookedList.add(room);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
