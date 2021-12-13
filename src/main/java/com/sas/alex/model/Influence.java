package com.sas.alex.model;


import javax.persistence.*;

@Entity(name = "influence")
public class Influence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isMan")
    private boolean isMan;

    @Column(name = "isWomen")
    private boolean isWomen;


    public Influence() {
    }

    public Influence(long id, String name, boolean isMan, boolean isWomen) {
        this.id = id;
        this.name = name;
        this.isMan = isMan;
        this.isWomen = isWomen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public boolean isWomen() {
        return isWomen;
    }

    public void setWomen(boolean women) {
        isWomen = women;
    }
}
