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
        docker build -t $IMAGE_NAME /database/ddl
    fi


    # Run the base SQL Server container
    docker run -d -e 'ACCEPT_EULA=Y' -p 1433:1433 --name $CONTAINER_NAME $IMAGE_NAME

    # Wait for a reasonable amount of time (adjust this as needed)
    echo "Waiting for SQL Server to start..."
    sleep 15  # You may need to adjust this sleep time based on your SQL Server's startup time

    # Check if the container is running
    if [ "$(docker ps -a -f name=$CONTAINER_NAME)" ]; then
        echo "Base container '$CONTAINER_NAME' created successfully!!"
    else
        echo "Container '$CONTAINER_NAME' failed to create!"
        exit 1
    fi

    echo "Graceful exit..."
    exit 0

fi
