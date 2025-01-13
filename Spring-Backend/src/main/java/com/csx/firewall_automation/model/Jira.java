package com.csx.firewall_automation.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Jira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jira_id")
    private long id;

    @Column(nullable = false)
    private String jiraTicket;

    public Jira(String jiraTicket) {
        this.jiraTicket = jiraTicket;
    }
}
