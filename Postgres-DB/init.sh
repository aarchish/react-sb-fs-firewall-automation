#!/bin/bash
set -e

# Create the database using the psql command
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE firewall_automation;
EOSQL