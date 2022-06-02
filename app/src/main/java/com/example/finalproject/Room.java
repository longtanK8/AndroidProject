package com.example.finalproject;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;
    private String type;
    boolean available;
    public int bed;
    public int people;
    String description = "Nothing to show";
    Customer booker;
    int price;

    public Room(String id, String type, boolean available) {
        booker = new Customer();
        this.id = id;
        this.type = type;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBed() {
        return bed;
    }

    public int getPeople() {
        return people;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
