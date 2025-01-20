package com.csx.firewall_automation.commandLineRunner;

import com.csx.firewall_automation.model.Jira;
import com.csx.firewall_automation.model.Snow;
import com.csx.firewall_automation.model.TF_IPs;
import com.csx.firewall_automation.model.User;
import com.csx.firewall_automation.repository.TF_IPsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TF_IPs_CommandLineRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TF_IPsRepository repository;

    public TF_IPs_CommandLineRunner(TF_IPsRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(String... args) throws Exception {
        repository.save(new TF_IPs("10.0.0.0/32", new Jira("NETSEC-1000"), new Snow("REQ-1000001"), true, new User("Koushik")));
        repository.save(new TF_IPs("11.0.0.0/32", new Jira("NETSEC-1001"), new Snow("REQ-1000002"), true, new User("Mobeen")));
        repository.save(new TF_IPs("12.0.0.0/32", new Jira("NETSEC-1002"), new Snow("REQ-1000003"), true, new User("Eveyline")));
    }
}
