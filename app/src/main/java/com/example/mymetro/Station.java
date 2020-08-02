package com.example.mymetro;

public class Station {



    private String stationName;
    private int stationGraphNode;

    public void setStationName(String name){
        this.stationName = name;
    }

    public void setStationGraphNode(int node) {
        this.stationGraphNode = node;
    }

    public String getStationName(){
        return stationName;
    }

    public int getStationGraphNode(){
        return stationGraphNode;
    }

    // default constructor
    public Station(){
        this.stationName = "null";
        this.stationGraphNode = 0;
    }

    // Parameterised constructor
    public Station(String name, int node){
        this.stationName = name;
        this.stationGraphNode = node;
    }

    //function to add station values explicitly
    public void addStationValues(String name, int node){
        this.stationName = name;
        this.stationGraphNode = node;
    }


}
