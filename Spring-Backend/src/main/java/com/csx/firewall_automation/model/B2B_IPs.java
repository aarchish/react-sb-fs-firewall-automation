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
}
