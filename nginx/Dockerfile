FROM nginx:latest

# Install envsubst
RUN apt-get update && apt-get install -y gettext-base

# Copy the Nginx configuration template
COPY nginx.conf /etc/nginx/templates/nginx.conf.template

# Copy the entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
CMD ["nginx", "-g", "daemon off;"]