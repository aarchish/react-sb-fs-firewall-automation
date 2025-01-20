package com.csx.firewall_automation.commandLineRunner;

//import com.csx.firewall_automation.model.Jira;
//import com.csx.firewall_automation.model.Snow;
//import com.csx.firewall_automation.model.TF_URLs;
//import com.csx.firewall_automation.repository.TF_URLsRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TF_URLs_CommandLineRunner implements CommandLineRunner {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    private final TF_URLsRepository repository;
//
//    public TF_URLs_CommandLineRunner(TF_URLsRepository repository) {
//        this.repository = repository;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        repository.save(new TF_URLs("*.google.com", new Jira("NETSEC-2000"), new Snow("REQ-2000001"), true));
//        repository.save(new TF_URLs("*.hello.com", new Jira("NETSEC-2001"), new Snow("REQ-2000002"), true));
//        repository.save(new TF_URLs("*.yahoo.com", new Jira("NETSEC-2002"), new Snow("REQ-2000003"), true));
//    }
//}
