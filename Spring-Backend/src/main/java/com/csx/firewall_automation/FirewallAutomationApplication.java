package com.csx.firewall_automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FirewallAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirewallAutomationApplication.class, args);
    }

}
