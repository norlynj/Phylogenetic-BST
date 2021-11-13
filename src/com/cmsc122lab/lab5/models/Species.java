package com.cmsc122lab.lab5.models;

public class Species {
    private String name;
    private int lineage;

    public Species(){}
    public Species(String name, int lineage){
        this.name = name;
        this.lineage = lineage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLineage() {
        return lineage;
    }

    public void setLineage(int lineage) {
        this.lineage = lineage;
    }
}
