#!/bin/bash

# Define container and image names
CONTAINER_NAME=sw-dev-container
IMAGE_NAME=sw-base-image

# Define the Docker volume name for data persistence
DATA_VOLUME_NAME=sw-dev-volume

REMOVE="Y"
REMOVE_IMAGE="N"

# Check if the container is already running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Container '$CONTAINER_NAME' is already running."
#    read -p "Remove container? (Y/n): " REMOVE
    REMOVE="Y"
    if [[ $REMOVE == "Y" || $REMOVE == "y" ]]; then
        docker stop $CONTAINER_NAME
        docker rm $CONTAINER_NAME
        echo "Container '$CONTAINER_NAME' removed."
    else
        echo "Container '$CONTAINER_NAME' not removed."
        exit 1
    fi
fi

# Check if the Docker volume exists
if [ "$(docker volume ls -q -f name=$DATA_VOLUME_NAME)" ]; then
    echo "Docker volume '$DATA_VOLUME_NAME' already exists."
#    read -p "Remove the existing volume? (Y/n): " REMOVE_VOLUME
    REMOVE_VOLUME="Y"
    if [[ $REMOVE_VOLUME == "Y" || $REMOVE_VOLUME == "y" ]]; then
        docker volume rm $DATA_VOLUME_NAME
        echo "Docker volume '$DATA_VOLUME_NAME' removed."
    fi
else
    echo "Docker volume '$DATA_VOLUME_NAME' does not exist."
fi

# Check if the image exists
if [ "$(docker images -q $IMAGE_NAME)" ]; then
    echo "Image '$IMAGE_NAME' exists."
#    read -p "Remove image? (Y/n): " REMOVE_IMAGE
    REMOVE_IMAGE="Y"
fi

if [[ $REMOVE == "Y" || $REMOVE == "y" ]]; then
    # Remove the image if requested
    if [[ $REMOVE_IMAGE == "Y" || $REMOVE_IMAGE == "y" ]]; then
        echo "Removing image '$IMAGE_NAME'..."
        docker image rm $IMAGE_NAME
    fi

    # Build the SQL Server image (if not already built)
    if [ ! "$(docker images -q $IMAGE_NAME)" ]; then
        echo "Building image '$IMAGE_NAME'..."
        docker build -t $IMAGE_NAME ./database/ddl
    else
        echo "Using existing image '$IMAGE_NAME'."
    fi

    # Prompt the user to enter SA_PASSWORD
#    read -sp "Enter SA password for the base container: " SA_PASSWORD
    SA_PASSWORD="Legion1@#$"
    echo

    # Prompt the user for PORT_NUMBER
#    read -p "Enter PORT_NUMBER for the base container (default is 1433): " PORT_NUMBER
    PORT_NUMBER=1433
    PORT_NUMBER=${PORT_NUMBER:-1433}

    # Check if the user provided SA_PASSWORD
    if [ -z "$SA_PASSWORD" ]; then
        echo "SA_PASSWORD is required."
        exit 1
    fi

    # Run the SQL Server container with user-defined SA_PASSWORD, port number, and Docker volume
    docker run -d -e 'ACCEPT_EULA=Y' -e "SA_PASSWORD=$SA_PASSWORD" -p $PORT_NUMBER:1433 --name $CONTAINER_NAME -v $DATA_VOLUME_NAME:/var/opt/mssql $IMAGE_NAME

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
    # echo "No additional setup commands..."

    echo "Graceful exit..."

    # exit 0
fi
