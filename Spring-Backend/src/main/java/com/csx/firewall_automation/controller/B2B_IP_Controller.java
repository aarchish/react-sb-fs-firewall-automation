package com.csx.firewall_automation.controller;

import com.csx.firewall_automation.model.B2B_IPs;
import com.csx.firewall_automation.service.B2B_IP_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/b2b_ips")
public class B2B_IP_Controller {

    private final B2B_IP_Service b2bIPService;

    public B2B_IP_Controller(B2B_IP_Service b2bIPService) {
        this.b2bIPService = b2bIPService;
    }

    @GetMapping
    public List<B2B_IPs> retrieveAllB2bIps() {
        List<B2B_IPs> b2BIPsList = b2bIPService.retrieveAllB2bIps();

        if (b2BIPsList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return b2BIPsList;
    }

    @PostMapping
    public ResponseEntity<String> insertNewB2bIPEntry(@RequestBody List<B2B_IPs> b2b_ip_list) {
        for (B2B_IPs b2b_ip : b2b_ip_list) {
            b2bIPService.insertNewB2bIPEntry(b2b_ip);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("B2B_IP_List entry created successfully.");
    }

}
