package com.csx.firewall_automation.model;

import jakarta.persistence.*;

@Entity
public class Jira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jira_id")
    private long id;

    @Column(nullable = false)
    private String jiraTicket;

    public Jira() {
    }
    
    public Jira(String jiraTicket) {
        this.jiraTicket = jiraTicket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJiraTicket() {
        return jiraTicket;
    }

    public void setJiraTicket(String jiraTicket) {
        this.jiraTicket = jiraTicket;
    }

    @Override
    public String toString() {
        return "Jira{" +
                "id=" + id +
                ", jiraTicket='" + jiraTicket + '\'' +
                '}';
    }
}
