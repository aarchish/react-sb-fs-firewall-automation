package com.csx.firewall_automation.repository;

import com.csx.firewall_automation.model.Snow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnowRepository extends JpaRepository<Snow, Long> {
    Snow findBySnowREQ(String reqNumber);
}
