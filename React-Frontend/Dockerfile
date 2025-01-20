# React-Frontend/Dockerfile
# Use an official node image as the base image
FROM node:16-alpine as build

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# List files to debug
RUN ls -al /app

# Install dependencies
RUN npm install

# List files to debug
RUN ls -al /app

# Copy the rest of the application code
COPY . .

# List files to debug
RUN ls -al /app

# Build the application
RUN npm run build

# Use an official nginx image to serve the built application
FROM nginx:alpine

# Copy the built application from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]