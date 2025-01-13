package com.csx.firewall_automation.commandLineRunner;

//import com.csx.firewall_automation.model.B2B_Customers;
//import com.csx.firewall_automation.model.B2B_IPs;
//import com.csx.firewall_automation.model.Snow;
//import com.csx.firewall_automation.repository.B2B_IPsRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class B2B_IPs_CommandLineRunner implements CommandLineRunner {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    private final B2B_IPsRepository repository;
//
//    public B2B_IPs_CommandLineRunner(B2B_IPsRepository repository) {
//        this.repository = repository;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        repository.save(new B2B_IPs(new B2B_Customers("Google"), "70.0.0.0/32", new Snow("REQ-3000001"), true));
//        repository.save(new B2B_IPs(new B2B_Customers("Amtrack"), "80.0.0.0/32", new Snow("REQ-3000002"), true));
//        repository.save(new B2B_IPs(new B2B_Customers("East Coal"), "90.0.0.0/32", new Snow("REQ-3000003"), true));
//    }
//}
