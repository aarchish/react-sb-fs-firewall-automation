# React-Frontend/Dockerfile
# Use an official node image as the base image
#FROM node:16-alpine as build
FROM node:current-alpine as build
# Set the working directory
WORKDIR /app

# List files to debug
RUN ls -al /

# List files to debug
RUN ls -al /app
# List files to debug
#RUN ls -al /usr/src
# List files to debug
#RUN ls -al /usr/src/app

# List files to debug
#RUN ls -al /home

# List files to debug
#RUN ls -al /home/node

# Copy package.json and package-lock.json
COPY **/package*.json ./
#COPY /package-lock.json /app

# List files to debug
RUN ls -al /app

# List files to debug
#RUN ls -al /app

# Install dependencies
RUN npm install

# List files to debug
RUN ls -al /app

# Copy the rest of the application code
COPY ./React-Frontend .

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