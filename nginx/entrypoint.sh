#!/bin/sh

# Substitute environment variables in the Nginx configuration template
envsubst '${VM_PUBLIC_IP}' < /etc/nginx/templates/default.conf.template > /etc/nginx/conf.d/default.conf

# Create the SSL certificate files from environment variables
mkdir -p /etc/nginx/ssl
echo "$SSL_CERT" > /etc/nginx/ssl/nginx-selfsigned.crt
echo "$SSL_KEY" > /etc/nginx/ssl/nginx-selfsigned.key

# Start Nginx
exec "$@"