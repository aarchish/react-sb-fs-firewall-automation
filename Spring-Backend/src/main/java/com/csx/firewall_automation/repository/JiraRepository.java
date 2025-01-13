package com.csx.firewall_automation.repository;

import com.csx.firewall_automation.model.Jira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JiraRepository extends JpaRepository<Jira, Long> {
    Jira findByJiraTicket(String jira_ticket);
}
