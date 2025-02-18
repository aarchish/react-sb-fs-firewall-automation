package com.csx.firewall_automation.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/files")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final BlobServiceClient blobServiceClient;
    private final String localCacheDir = "local-cache";

    @Value("${AZURE_STORAGE_CONTAINER_NAME}")
    private String containerName;

    public FileController(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
        createLocalCacheDir();
    }

    private void createLocalCacheDir() {
        Path cacheDir = Paths.get(localCacheDir);
        if (!Files.exists(cacheDir)) {
            try {
                Files.createDirectories(cacheDir);
                logger.info("Local cache directory created at {}", cacheDir);
            } catch (IOException e) {
                logger.error("Failed to create local cache directory", e);
                throw new RuntimeException("Failed to create local cache directory", e);
            }
        }
    }

    @GetMapping("/tf_ips")
    public ResponseEntity<String> readTFIPs() {
        logger.info("Reading TF IPs");
        return readFile("TF_IPs");
    }

    @PostMapping("/tf_ips")
    public ResponseEntity<String> writeTFIPs(@RequestParam("file") MultipartFile file) {
        logger.info("Writing TF IPs");
        return writeFile("TF_IPs", file);
    }

    @GetMapping("/b2b_ips")
    public ResponseEntity<String> readB2BIPs() {
        logger.info("Reading B2B IPs");
        return readFile("B2B_IPs");
    }

    @PostMapping("/b2b_ips")
    public ResponseEntity<String> writeB2BIPs(@RequestParam("file") MultipartFile file) {
        logger.info("Writing B2B IPs");
        return writeFile("B2B_IPs", file);
    }

    private ResponseEntity<String> readFile(String filename) {
        try {
            Path localFilePath = Paths.get(localCacheDir, filename + ".txt");
            if (Files.exists(localFilePath)) {
                String content = new String(Files.readAllBytes(localFilePath), StandardCharsets.UTF_8);
                logger.info("File read from local cache: {}", localFilePath.toAbsolutePath());
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
                return new ResponseEntity<>(content, headers, HttpStatus.OK);
            } else {
                BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(filename + ".txt");
                String content = new String(blobClient.downloadContent().toBytes(), StandardCharsets.UTF_8);
                Files.write(localFilePath, content.getBytes(StandardCharsets.UTF_8));
                logger.info("Read file from blob storage and cached locally: {}", localFilePath.toAbsolutePath());
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
                return new ResponseEntity<>(content, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Failed to read file: {}", filename, e);
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<String> writeFile(String filename, MultipartFile file) {
        try {
            Path localFilePath = Paths.get(localCacheDir, filename + ".txt");
            try (OutputStream out = Files.newOutputStream(localFilePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                out.write(file.getBytes());
                logger.info("Wrote file to local cache: {}", localFilePath.toAbsolutePath());
            }

            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(filename + ".txt");
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            logger.info("Uploaded file to blob storage: {}", filename);

            return new ResponseEntity<>("File updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Failed to update file: {}", filename, e);
            return new ResponseEntity<>("Failed to update file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}