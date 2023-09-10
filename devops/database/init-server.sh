#!/bin/bash

# Define container and image names
CONTAINER_NAME=sw-base-container
IMAGE_NAME=sw-base-image

# Check if the container is already running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Container '$CONTAINER_NAME' is already running."
		exit 1
else
    # Build the SQL Server image (if not already built)
    if [ "$(docker images -q $IMAGE_NAME)" ]; then
        echo "Using existing image '$IMAGE_NAME'."
    else
        echo "Building image '$IMAGE_NAME'..."
        docker build -t $IMAGE_NAME ./ddl
    fi

    # Check for user-defined SA_PASSWORD and PORT_NUMBER
    if [ -z "$1" ] || [ -z "$2" ]; then
        echo "Usage: $0 <SA_PASSWORD> <PORT_NUMBER>"
        exit 1
    fi

    SA_PASSWORD="$1"
    PORT_NUMBER="$2"

    # Run the SQL Server container with user-defined SA_PASSWORD and port number
    docker run -d -e 'ACCEPT_EULA=Y' -e "SA_PASSWORD=$SA_PASSWORD" -p $PORT_NUMBER:1433 --name $CONTAINER_NAME $IMAGE_NAME

    # Wait for a reasonable amount of time (adjust this as needed)
    echo "Waiting for SQL Server to start..."
    sleep 15  # You may need to adjust this sleep time based on your SQL Server's startup time

    # Check if the container is running
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        echo "Application database container '$CONTAINER_NAME' running successfully!!"
    else
        echo "Container '$CONTAINER_NAME' failed to install!"
        exit 1
    fi

    # Execute any additional setup commands here
		echo "No additional setup commands..."

		echo "Gracefull exit..."
		exit 1

fi
