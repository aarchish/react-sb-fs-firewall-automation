package com.csx.firewall_automation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
}