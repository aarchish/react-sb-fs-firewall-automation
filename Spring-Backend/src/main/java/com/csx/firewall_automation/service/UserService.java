package com.csx.firewall_automation.service;

import com.csx.firewall_automation.model.User;
import com.csx.firewall_automation.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(SnowService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User retrieveUserByUserName(String userName) {
        logger.info("Searching for User record with username: {}", userName);
        User user = userRepository.findByName(userName);
        if (user == null) {
            logger.warn("No User record found for username: {}", userName);
        }
        return user;
    }
}
