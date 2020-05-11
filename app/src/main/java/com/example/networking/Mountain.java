package com.example.networking;

public class Mountain {

    private String name;
    private String location;
    private int size;

    public Mountain(String name, String location, int size){
        this.name = name;
        this.location = location;
        this.size = size;

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        return name;
    }
}
