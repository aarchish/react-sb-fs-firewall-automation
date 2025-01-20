package com.csx.firewall_automation.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class TF_URLs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tf_url_id")
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jira_id")
    private Jira jiraObj;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "snow_id")
    private Snow snowReqObj;

    @Column(nullable = false)
    private boolean inFirewall;

    @CreationTimestamp
    @Column(updatable = false) // Prevent updates to this field
    private LocalDateTime createdAt;

    public TF_URLs() {
    }

    public TF_URLs(String url, Jira jiraObj, Snow snowReqObj, boolean inFirewall) {
        this.url = url;
        this.jiraObj = jiraObj;
        this.snowReqObj = snowReqObj;
        this.inFirewall = inFirewall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "TF_URLs{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", jiraObj=" + jiraObj +
                ", snowReqObj=" + snowReqObj +
                ", inFirewall=" + inFirewall +
                ", createdAt=" + createdAt +
                '}';
    }
}