package com.bookit.pojos;

import com.google.gson.annotations.SerializedName;

public class Room implements Comparable<Room>{

    private int id;
    private String name;
    private String description;
    private int capacity;
    @SerializedName("withTv")
    private boolean isWithTV;
    @SerializedName("withWhiteBoard")
    private boolean isWithWhiteBoard;


    public Room() {
    }

    public Room(String name, String description, int capacity, boolean isWithTV, boolean isWithWhiteBoard) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.isWithTV = isWithTV;
        this.isWithWhiteBoard = isWithWhiteBoard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWithTV() {
        return isWithTV;
    }

    public void setWithTV(boolean withTV) {
        isWithTV = withTV;
    }

    public boolean isWithWhiteBoard() {
        return isWithWhiteBoard;
    }

    public void setWithWhiteBoard(boolean withWhiteBoard) {
        isWithWhiteBoard = withWhiteBoard;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", isWithTV=" + isWithTV +
                ", isWithWhiteBoard=" + isWithWhiteBoard +
                '}';
    }

    @Override
    public int compareTo(Room o) {
        return this.name.compareTo(o.getName());
    }
}
