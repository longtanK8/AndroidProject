package com.example.finalproject;

public class Room {
    private String id;
    private String type;
    boolean available;

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

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", available=" + available +
                '}';
    }
}
