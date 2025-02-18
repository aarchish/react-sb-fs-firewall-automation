# Firewall Automation Backend

This repository contains the backend application for the Firewall Automation project. The backend is built using Spring Boot and provides APIs for managing TF IPs, B2B IPs, and TF URLs. It also includes file operations for reading and writing IP data.

## Table of Contents

- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
  - [TF IPs](#tf-ips)
  - [B2B IPs](#b2b-ips)
  - [TF URLs](#tf-urls)
  - [File Operations](#file-operations)
- [Security](#security)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (optional, for containerized deployment)

Configuration

Environment Variables

# Spring Boot Server Port
server_port: Define the Server Port to be used by Spring Boot

# DataBase Configuration
db_url= Full Database URL with prefix = "jdbc:"
db_username= Database Username
db_password=Thunder Database Password

# Okta Secrets
okta_issuer= Okta Authentication Server URL
okta_client_id= SpringBoot Application Client ID
okta_client_secret= SpringBoot Application Client Password

# Azure Secrets -> fortinet_uat subsciption
AZURE_STORAGE_ACCOUNT_NAME= Blob Storage Account Name
AZURE_STORAGE_CONTAINER_NAME= Blob Storage Container Name
AZURE_CLIENT_ID= Blob Storage Service Principle Client ID
AZURE_CLIENT_SECRET= Blob Storage Service Principle Client Password
AZURE_TENANT_ID= Blob Storage - Azure Tenant ID

# User credentials
FIREWALL_USERNAME= HTTP Basic Authentication Username
FIREWALL_PASSWORD= HTTP Basic Authentication Password

Application Properties

The application properties are configured in the src/main/resources/application.properties file. Additional profiles can be configured in application-local.properties, application-public.properties and application-prod.properties.

API Documentation

All Endpoints on the application are Okta OAuth2 Based.
This requires a POST Request to Okta to retrieve an access token to be passed in Bearer Authorization Header.

Database APIs

GET /api/tf_ips
Returns a JSON Array of all the Objects

Response : 200 - OK

POST /api/tf_ips
Pass a JSON Array of objects to be entered into the database

Example :

Response : 201 - Created


GET /api/tf_urls
Returns a JSON Array of all the Objects

Response : 200 - OK

POST /api/tf_urls
Pass a JSON Array of objects to be entered into the database

Example :

Response : 201 - Created


GET /api/b2b_ips
Returns a JSON Array of all the Objects

Response : 200 - OK

POST /api/b2b_ips
Pass a JSON Array of objects to be entered into the database

Example :

Response : 201 - Created


File Management APIs

GET /files/tf_ips
Returns a plain text format string of TF IPs

Response : 200 - OK, 404 - Not Found

POST /files/tf_ips
Returns a plain text format string of TF IPs to be entered into the text file

Example :

Response : 200 - OK, 500 - Internal Server Error : Failed to update file


GET /files/b2b_ips
Returns a plain text format string of TF IPs

Response : 200 - OK, 404 - Not Found

POST /files/b2b_ips
Returns a plain text format string of B2B IPs to be entered into the text file

Example :

Response : 200 - OK, 500 - Internal Server Error : Failed to update file




Security

The application uses 
1. HTTP Basic authentication for the GET /files/** endpoints. Ensure that the FIREWALL_USERNAME and FIREWALL_PASSWORD environment variables are set correctly.
2. OAuth2 authentication for /api and /files endpoints.