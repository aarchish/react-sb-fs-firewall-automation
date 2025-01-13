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

    public TF_IPs(String ipAddress, Jira jiraObj, Snow snowReqObj, boolean inFirewall) {
        this.ipAddress = ipAddress;
        this.jiraObj = jiraObj;
        this.snowReqObj = snowReqObj;
        this.inFirewall = inFirewall;
    }
}
