package com.csx.firewall_automation.commandLineRunner;

//@Component
//public class TF_IPs_CommandLineRunner implements CommandLineRunner {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    private final TF_IPsRepository repository;
//
//    public TF_IPs_CommandLineRunner(TF_IPsRepository repository) {
//        this.repository = repository;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        repository.save(new TF_IPs("10.0.0.0/32", new Jira("NETSEC-1000"), new Snow("REQ-1000001"), true));
//        repository.save(new TF_IPs("11.0.0.0/32", new Jira("NETSEC-1001"), new Snow("REQ-1000002"), true));
//        repository.save(new TF_IPs("12.0.0.0/32", new Jira("NETSEC-1002"), new Snow("REQ-1000003"), true));
//    }
//}
