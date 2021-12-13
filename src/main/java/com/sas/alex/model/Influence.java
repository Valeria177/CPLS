package com.sas.alex.model;


import javax.persistence.*;

@Entity(name = "influence")
public class Influence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "m_critic_male")
    private boolean isWomen;

    @Column(name = "q_critic_male")
    private boolean isMan;

    public Influence() {
    }

    public Influence(long id, String name, boolean isWomen, boolean isMan) {
        this.id = id;
        this.name = name;
        this.isWomen = isWomen;
        this.isMan = isMan;
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

    public boolean isWomen() {
        return isWomen;
    }

    public void setWomen(boolean women) {
        isWomen = women;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }
}
