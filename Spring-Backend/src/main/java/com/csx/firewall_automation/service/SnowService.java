package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.Snow;
import com.csx.firewall_automation.repository.SnowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SnowService {

    private static final Logger logger = LoggerFactory.getLogger(SnowService.class);

    private final SnowRepository snowRepository;

    public SnowService(SnowRepository snowRepository) {
        this.snowRepository = snowRepository;
    }

    public Snow retrieveSnowByRequestNumber(String reqNumber) {
        logger.info("Searching for Snow record with REQ number: {}", reqNumber);
        Snow snow = snowRepository.findBySnowREQ(reqNumber);
        if (snow == null) {
            logger.warn("No Snow record found for REQ number: {}", reqNumber);
        }
        return snow;
    }
}
