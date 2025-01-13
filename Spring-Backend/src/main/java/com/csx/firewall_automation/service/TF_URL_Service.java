package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.Jira;
import com.csx.firewall_automation.model.Snow;
import com.csx.firewall_automation.model.TF_URLs;
import com.csx.firewall_automation.repository.TF_URLsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TF_URL_Service {

    private static final Logger logger = LoggerFactory.getLogger(TF_URL_Service.class);

    private final TF_URLsRepository tfURLsRepository;
    private final JiraService jiraService;
    private final SnowService snowService;

    public TF_URL_Service(TF_URLsRepository tfURLsRepository, JiraService jiraService, SnowService snowService) {
        this.tfURLsRepository = tfURLsRepository;
        this.jiraService = jiraService;
        this.snowService = snowService;
    }

    public List<TF_URLs> retrieveAllTfUrls() {
        return tfURLsRepository.findAll().stream().toList();
    }

    @Transactional
    public void insertNewTfUrlEntry(TF_URLs tf_url) {
        logger.info("Inserting new TF_URL entry for URL: {}", tf_url.getUrl());

        String inputJiraTicket = tf_url.getJiraObj().getJiraTicket();
        logger.debug("Processing Jira ticket: {}", inputJiraTicket);
        Jira findJira = jiraService.retrieveJiraByJiraTicket(inputJiraTicket);

        Jira newJira = (findJira == null) ? new Jira(inputJiraTicket) : findJira;
        logger.info("Linked Jira object: {}", newJira);

        String inputSnowReq = tf_url.getSnowReqObj().getSnowREQ();
        logger.debug("Processing Snow REQ: {}", inputSnowReq);
        Snow findSnow = snowService.retrieveSnowByRequestNumber(inputSnowReq);

        Snow newSnow = (findSnow == null) ? new Snow(inputSnowReq) : findSnow;
        logger.info("Linked Snow object: {}", newSnow);

        TF_URLs newTFurl = new TF_URLs(tf_url.getUrl(), newJira, newSnow, tf_url.isInFirewall());
        tfURLsRepository.save(newTFurl);

        logger.info("Successfully saved TF_URL entry: {}", newTFurl);
    }

}
