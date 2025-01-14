package com.csx.firewall_automation.model;

import jakarta.persistence.*;

@Entity
public class B2B_Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    public B2B_Customers(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "B2B_Customers{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
