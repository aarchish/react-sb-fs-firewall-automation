package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.Jira;
import com.csx.firewall_automation.model.Snow;
import com.csx.firewall_automation.model.TF_IPs;
import com.csx.firewall_automation.model.User;
import com.csx.firewall_automation.repository.TF_IPsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TF_IP_Service {

    private static final Logger logger = LoggerFactory.getLogger(TF_IP_Service.class);

    private final TF_IPsRepository tfIPsRepository;
    private final JiraService jiraService;
    private final SnowService snowService;
    private final UserService userService;

    public TF_IP_Service(TF_IPsRepository tfIPsRepository, JiraService jiraService, SnowService snowService, UserService userService) {
        this.tfIPsRepository = tfIPsRepository;
        this.jiraService = jiraService;
        this.snowService = snowService;
        this.userService = userService;
    }

    public List<TF_IPs> retrieveAllTfIPs() {
        return tfIPsRepository.findAll().stream().toList();
    }

    @Transactional
    public void insertNewTfEntry(TF_IPs tf_ip) {
        logger.info("Inserting new TF_IPs entry for IP address: {}", tf_ip.getIpAddress());

        String inputJiraTicket = tf_ip.getJiraObj().getJiraTicket();
        logger.debug("Processing Jira ticket: {}", inputJiraTicket);
        Jira findJira = jiraService.retrieveJiraByJiraTicket(inputJiraTicket);

        Jira newJira = (findJira == null) ? new Jira(inputJiraTicket) : findJira;
        logger.info("Linked Jira object: {}", newJira);

        String inputSnowReq = tf_ip.getSnowReqObj().getSnowREQ();
        logger.debug("Processing Snow REQ: {}", inputSnowReq);
        Snow findSnow = snowService.retrieveSnowByRequestNumber(inputSnowReq);

        Snow newSnow = (findSnow == null) ? new Snow(inputSnowReq) : findSnow;
        logger.info("Linked Snow object: {}", newSnow);

        String inputRequestor = tf_ip.getRequestedBy().getName();
        logger.debug("Processing Requesting User: {}", inputRequestor);
        User findRequestor = userService.retrieveUserByUserName(inputRequestor);

        User newRequestor = (findRequestor == null) ? new User(inputRequestor) : findRequestor;
        logger.info("Linked Requesting User: {}", newRequestor);


        TF_IPs newTfIp = new TF_IPs(tf_ip.getIpAddress(), newJira, newSnow, tf_ip.isInFirewall(), newRequestor);
        tfIPsRepository.save(newTfIp);

        logger.info("Successfully saved TF_IPs entry: {}", newTfIp);
    }

}
