package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.B2B_Customers;
import com.csx.firewall_automation.model.B2B_IPs;
import com.csx.firewall_automation.model.Snow;
import com.csx.firewall_automation.repository.B2B_IPsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class B2B_IP_Service {

    private static final Logger logger = LoggerFactory.getLogger(B2B_IP_Service.class);

    private final B2B_IPsRepository b2BIPsRepository;
    private final B2B_Customer_Service b2BCustomersService;
    private final SnowService snowService;

    public B2B_IP_Service(B2B_IPsRepository b2BIPsRepository, B2B_Customer_Service b2BCustomersService, SnowService snowService) {
        this.b2BIPsRepository = b2BIPsRepository;
        this.b2BCustomersService = b2BCustomersService;
        this.snowService = snowService;
    }

    public List<B2B_IPs> retrieveAllB2bIps() {
        return b2BIPsRepository.findAll().stream().toList();
    }

    @Transactional
    public void insertNewB2bIPEntry(B2B_IPs b2b_ip) {
        logger.info("Inserting new B2B_IP entry for IP address: {}", b2b_ip.getIpAddress());

        String inputCustomerName = b2b_ip.getCustomerObj().getCustomerName();
        logger.debug("Processing Customer Name: {}", inputCustomerName);

        B2B_Customers checkCustomer = b2BCustomersService.retrieveB2bCustomerByName(inputCustomerName);
        B2B_Customers newCustomer = checkCustomer == null
                ? new B2B_Customers(inputCustomerName) : checkCustomer;
        logger.info("Linked Customer object: {}", newCustomer);

        String inputSnowReq = b2b_ip.getSnowReqObj().getSnowREQ();
        logger.debug("Processing Snow REQ: {}", inputSnowReq);
        Snow findSnow = snowService.retrieveSnowByRequestNumber(inputSnowReq);

        Snow newSnow = (findSnow == null) ? new Snow(inputSnowReq) : findSnow;
        logger.info("Linked Snow object: {}", newSnow);

        B2B_IPs newB2Bip = new B2B_IPs(newCustomer, b2b_ip.getIpAddress(), newSnow, b2b_ip.isInFirewall());
        b2BIPsRepository.save(newB2Bip);

        logger.info("Successfully saved B2B_IP entry: {}", newB2Bip);
    }

}
