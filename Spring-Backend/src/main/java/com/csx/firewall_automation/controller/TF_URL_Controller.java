package com.csx.firewall_automation.controller;

import com.csx.firewall_automation.model.TF_URLs;
import com.csx.firewall_automation.service.TF_URL_Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tf_urls")
public class TF_URL_Controller {

    private final TF_URL_Service tfUrlService;

    public TF_URL_Controller(TF_URL_Service tfUrlService) {
        this.tfUrlService = tfUrlService;
    }

    @GetMapping
    public List<TF_URLs> retrieveAllTfUrls() {
        List<TF_URLs> tfUrlsList = tfUrlService.retrieveAllTfUrls();

        if (tfUrlsList.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return tfUrlsList;
    }

    @PostMapping
    public ResponseEntity<String> insertNewTfUrlEntry(@RequestBody List<TF_URLs> tf_url_list) {
        for (TF_URLs tf_url : tf_url_list) {
            tfUrlService.insertNewTfUrlEntry(tf_url);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("TF_URL_List entry created successfully.");
    }

}
