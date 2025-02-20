#!/bin/sh

# Substitute environment variables in the Nginx configuration template
envsubst '${VM_PUBLIC_IP}' < /etc/nginx/templates/nginx.conf.template > /etc/nginx/nginx.conf

# Create the SSL certificate files from environment variables
echo "$SSL_CERT" > /etc/nginx/ssl/nginx-selfsigned.crt
echo "$SSL_KEY" > /etc/nginx/ssl/nginx-selfsigned.key

# Start Nginx
exec "$@"