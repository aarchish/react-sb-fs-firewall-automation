#!/bin/sh

echo "Starting entrypoint script..."

# Substitute environment variables in the Nginx configuration template
echo "Substituting environment variables in the Nginx configuration template..."
envsubst '${VM_PUBLIC_IP} ${SSL_CERT} ${SSL_KEY}' < /etc/nginx/templates/default.conf.template > /etc/nginx/conf.d/default.conf
echo "Nginx configuration file created at /etc/nginx/conf.d/default.conf"

# Create the SSL certificate files from environment variables
echo "Creating SSL certificate files..."
mkdir -p /etc/nginx/ssl
echo "$SSL_CERT" > /etc/nginx/ssl/nginx-selfsigned.crt
echo "$SSL_KEY" > /etc/nginx/ssl/nginx-selfsigned.key
echo "SSL certificate files created at /etc/nginx/ssl"

# List the contents of the /etc/nginx/ssl directory to verify
echo "Listing contents of /etc/nginx/ssl directory:"
ls -l /etc/nginx/ssl

# Display the contents of the Nginx configuration file for debugging
echo "Displaying contents of /etc/nginx/conf.d/default.conf:"
cat /etc/nginx/conf.d/default.conf

# Start Nginx
echo "Starting Nginx..."
exec "$@"