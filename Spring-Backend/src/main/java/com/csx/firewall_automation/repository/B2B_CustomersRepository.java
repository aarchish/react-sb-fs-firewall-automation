package com.csx.firewall_automation.repository;

import com.csx.firewall_automation.model.B2B_Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface B2B_CustomersRepository extends JpaRepository<B2B_Customers, Long> {
    B2B_Customers findByCustomerName(String customerName);
}
