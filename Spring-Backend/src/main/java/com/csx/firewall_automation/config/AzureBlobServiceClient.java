package com.csx.firewall_automation.config;

import com.azure.spring.cloud.autoconfigure.implementation.storage.blob.properties.AzureStorageBlobProperties;
import com.azure.spring.cloud.service.implementation.storage.blob.BlobServiceClientBuilderFactory;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobServiceClient {

    @Bean
    public BlobServiceClient blobServiceClient(AzureStorageBlobProperties properties) {
        return new BlobServiceClientBuilderFactory(properties).build().buildClient();
    }
}
