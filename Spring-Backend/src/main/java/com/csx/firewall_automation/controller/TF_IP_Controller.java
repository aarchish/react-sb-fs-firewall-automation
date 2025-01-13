package com.csx.firewall_automation.controller;

import com.csx.firewall_automation.model.TF_IPs;
import com.csx.firewall_automation.service.TF_IP_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tf_ips")
public class TF_IP_Controller {

    private final TF_IP_Service tfIpService;

    public TF_IP_Controller(TF_IP_Service tfIpService) {
        this.tfIpService = tfIpService;
    }

    @GetMapping
    public List<TF_IPs> retrieveAllTfIPs() {
        List<TF_IPs> tfIPsList = tfIpService.retrieveAllTfIPs();

        if (tfIPsList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return tfIPsList;
    }

    @PostMapping
    public ResponseEntity<String> insertNewTfEntry(@RequestBody List<TF_IPs> tf_ip_list) {
        for (TF_IPs tf_ip : tf_ip_list) {
            tfIpService.insertNewTfEntry(tf_ip);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("TF_IP_List entry created successfully.");
    }

}
