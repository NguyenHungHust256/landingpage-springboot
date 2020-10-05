package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Price {
    public enum TIME {
        DAY, MONTH, YEAR
    }

    public enum MONEY {
        USD, VND
    }

    private long id;
    private int cost;
    private MONEY type;
    private TIME time;
    private int storage;
    private int bandwidth;
    private int numDatabase;
    private int numAccountEmail;

    public Price(long id, int cost, MONEY type, TIME time, int storage, int bandwidth, int numDatabase, int numAccountEmail) {
        this.id = id;
        this.cost = cost;
        this.type = type;
        this.time = time;
        this.storage = storage;
        this.bandwidth = bandwidth;
        this.numDatabase = numDatabase;
        this.numAccountEmail = numAccountEmail;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "cost", nullable = false)
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Column(name = "type", nullable = false)
    public MONEY getType() {
        return type;
    }

    public void setType(MONEY type) {
        this.type = type;
    }

    @Column(name = "time", nullable = false)
    public TIME getTime() {
        return time;
    }

    public void setTime(TIME time) {
        this.time = time;
    }

    @Column(name = "storage", nullable = false)
    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Column(name = "bandwidth", nullable = false)
    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    @Column(name = "num_databases", nullable = false)
    public int getNumDatabase() {
        return numDatabase;
    }

    public void setNumDatabase(int numDatabase) {
        this.numDatabase = numDatabase;
    }

    @Column(name = "num_account_emails")
    public int getNumAccountEmail() {
        return numAccountEmail;
    }

    public void setNumAccountEmail(int numAccountEmail) {
        this.numAccountEmail = numAccountEmail;
    }
}
