package com.csx.firewall_automation.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileController {

    private final BlobServiceClient blobServiceClient;

    public FileController(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    @GetMapping("/{filename}")
    public ResponseEntity<String> readFile(@PathVariable String filename) {
        try {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient("your-container-name").getBlobClient(filename + ".txt");
            String content = new String(blobClient.downloadContent().toBytes(), StandardCharsets.UTF_8);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
            return new ResponseEntity<>(content, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{filename}")
    public ResponseEntity<String> writeFile(@PathVariable String filename, @RequestParam("file") MultipartFile file) {
        try {
            BlobClient blobClient = blobServiceClient.getBlobContainerClient("your-container-name").getBlobClient(filename + ".txt");
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            return new ResponseEntity<>("File updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
