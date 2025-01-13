package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.B2B_Customers;
import com.csx.firewall_automation.repository.B2B_CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class B2B_Customer_Service {

    private static final Logger logger = LoggerFactory.getLogger(SnowService.class);

    private final B2B_CustomersRepository b2BCustomersRepository;

    public B2B_Customer_Service(B2B_CustomersRepository b2BCustomersRepository) {
        this.b2BCustomersRepository = b2BCustomersRepository;
    }

    public List<B2B_Customers> retrieveAllB2bCustomers() {
        return b2BCustomersRepository.findAll().stream().toList();
    }

    public B2B_Customers retrieveB2bCustomerByName(String customerName) {
        logger.info("Searching for Snow record with REQ number: {}", customerName);
        B2B_Customers customer = b2BCustomersRepository.findByCustomerName(customerName);
        if (customer == null) {
            logger.warn("No Snow record found for REQ number: {}", customerName);
        }
        return b2BCustomersRepository.findByCustomerName(customerName);
    }

    public void insertNewB2bCustomerEntry(B2B_Customers b2b_customer) {
        b2BCustomersRepository.save(b2b_customer);
    }

    public void deleteB2bCustomerEntry(B2B_Customers b2b_customer) {
        b2BCustomersRepository.delete(b2b_customer);
    }


}
