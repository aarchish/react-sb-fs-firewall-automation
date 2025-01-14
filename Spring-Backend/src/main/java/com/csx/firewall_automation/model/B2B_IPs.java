package com.csx.firewall_automation.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class B2B_IPs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b2b_ip_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private B2B_Customers customerObj;

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_id", nullable = false)
    private Snow snowReqObj;

    @Column(nullable = false)
    private boolean inFirewall;

    @CreationTimestamp
    @Column(updatable = false) // Prevent updates to this field
    private LocalDateTime createdAt;

    public B2B_IPs(B2B_Customers b2bCustomer, String ipAddress, Snow snowReqObj, boolean inFirewall) {
        this.customerObj = b2bCustomer;
        this.ipAddress = ipAddress;
        this.snowReqObj = snowReqObj;
        this.inFirewall = inFirewall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public B2B_Customers getCustomerObj() {
        return customerObj;
    }

    public void setCustomerObj(B2B_Customers customerObj) {
        this.customerObj = customerObj;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Snow getSnowReqObj() {
        return snowReqObj;
    }

    public void setSnowReqObj(Snow snowReqObj) {
        this.snowReqObj = snowReqObj;
    }

    public boolean isInFirewall() {
        return inFirewall;
    }

    public void setInFirewall(boolean inFirewall) {
        this.inFirewall = inFirewall;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "B2B_IPs{" +
                "id=" + id +
                ", customerObj=" + customerObj +
                ", ipAddress='" + ipAddress + '\'' +
                ", snowReqObj=" + snowReqObj +
                ", inFirewall=" + inFirewall +
                ", createdAt=" + createdAt +
                '}';
    }
}
