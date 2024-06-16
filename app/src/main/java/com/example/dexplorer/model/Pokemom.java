package com.example.dexplorer.model;

public class Pokemom {
    private int id = 0;
    private String name;
    private String type;


    public Pokemom(String name) {
        this.name = name;
    }
    public Pokemom(){}
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
//    public void setId(int id) { this.id = String.valueOf(id); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public void setDetails(int id /*String type*/){
        setId(id);
//        setType(type);

    }
}
