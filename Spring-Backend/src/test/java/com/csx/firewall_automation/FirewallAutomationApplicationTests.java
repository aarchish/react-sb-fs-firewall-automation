package com.csx.firewall_automation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("local")
@SpringBootTest
class FirewallAutomationApplicationTests {

    @Test
    void contextLoads() {
    }

}
