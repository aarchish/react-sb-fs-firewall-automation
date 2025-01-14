package com.csx.firewall_automation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Snow {

    @Id
    @GeneratedValue
    @Column(name = "snow_id")
    private long id;

    @Column(nullable = false)
    private String snowREQ;

    public Snow(String snowREQ) {
        this.snowREQ = snowREQ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSnowREQ() {
        return snowREQ;
    }

    public void setSnowREQ(String snowREQ) {
        this.snowREQ = snowREQ;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "id=" + id +
                ", snowREQ='" + snowREQ + '\'' +
                '}';
    }
}