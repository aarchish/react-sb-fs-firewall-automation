package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.Jira;
import com.csx.firewall_automation.repository.JiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JiraService {

    private static final Logger logger = LoggerFactory.getLogger(JiraService.class);

    private final JiraRepository jiraRepository;


    public JiraService(JiraRepository jiraRepository) {
        this.jiraRepository = jiraRepository;
    }

    public Jira retrieveJiraByJiraTicket(String jira_ticket) {
        logger.info("Attempting to retrieve Jira with jira_ticket: {}", jira_ticket);
        Jira jira = jiraRepository.findByJiraTicket(jira_ticket);
        if (jira == null) {
            logger.warn("Jira with jira_ticket: {} not found", jira_ticket);
        }
        return jira;
    }
}
