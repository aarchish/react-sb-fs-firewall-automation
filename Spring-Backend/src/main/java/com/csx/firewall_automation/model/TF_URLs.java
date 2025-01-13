package com.csx.firewall_automation.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    public TF_URLs(String url, Jira jiraObj, Snow snowReqObj, boolean inFirewall) {
        this.url = url;
        this.jiraObj = jiraObj;
        this.snowReqObj = snowReqObj;
        this.inFirewall = inFirewall;
    }
}