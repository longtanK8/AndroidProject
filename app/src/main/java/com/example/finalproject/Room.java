package com.example.finalproject;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;
    private String type;
    boolean available;
    String description = "Nothing to show";
    int price;

    public Room(String id, String type, boolean available) {
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

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
