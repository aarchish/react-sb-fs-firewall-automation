package com.csx.firewall_automation.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class TF_IPs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tf_ip_id")
    private Long id;

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jira_id", nullable = false)
    //@JsonManagedReference
    private Jira jiraObj;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_id", nullable = false)
    //@JsonManagedReference
    private Snow snowReqObj;

    @Column(nullable = false)
    private boolean inFirewall;

    @CreationTimestamp
    @Column(updatable = false) // Prevent updates to this field
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User requestedBy;

    public TF_IPs() {
    }

    public TF_IPs(String ipAddress, Jira jiraObj, Snow snowReqObj, boolean inFirewall, User requestedBy) {
        this.ipAddress = ipAddress;
        this.jiraObj = jiraObj;
        this.snowReqObj = snowReqObj;
        this.inFirewall = inFirewall;
        this.requestedBy = requestedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Jira getJiraObj() {
        return jiraObj;
    }

    public void setJiraObj(Jira jiraObj) {
        this.jiraObj = jiraObj;
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

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    public String toString() {
        return "TF_IPs{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", jiraObj=" + jiraObj +
                ", snowReqObj=" + snowReqObj +
                ", inFirewall=" + inFirewall +
                ", createdAt=" + createdAt +
                ", requestedBy=" + requestedBy +
                '}';
    }
}
