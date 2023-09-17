#!/bin/bash

# Define container and image names
CONTAINER_NAME=sw-dev-container
IMAGE_NAME=sw-base-image

REMOVE="Y"
# Check if the container is already running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "
    Container '$CONTAINER_NAME' is already running."
    read -p "
    Remove container? (Y/n): " REMOVE
    if [[ $REMOVE == "Y" || $REMOVE == "y" ]]; then
        docker stop $CONTAINER_NAME
        docker rm $CONTAINER_NAME
        echo "
    Container '$CONTAINER_NAME' removed."
    else
        echo "
    Container '$CONTAINER_NAME' not removed."
        exit 1
    fi
fi

if [[ $REMOVE == "Y" || $REMOVE == "y" ]]; then
    # Build the SQL Server image (if not already built)
#    if [ "$(docker images -q $IMAGE_NAME)" ]; then
#        echo "
#    Using existing image '$IMAGE_NAME'."
#    else
        echo "
    Building image '$IMAGE_NAME'..."
        docker build -t $IMAGE_NAME ./database/ddl
    fi

    # Prompt the user to enter SA_PASSWORD
    read -sp "
    Enter SA password for the base container: " SA_PASSWORD
    echo

    # Prompt the user for PORT_NUMBER
    read -p "
    Enter PORT_NUMBER for the base container (default is 1433): " PORT_NUMBER
    PORT_NUMBER=${PORT_NUMBER:-1433}

    # Check if the user provided SA_PASSWORD
    if [ -z "$SA_PASSWORD" ]; then
        echo "
    SA_PASSWORD is required."
        exit 1
    fi

    # Run the SQL Server container with user-defined SA_PASSWORD and port number
    docker run -d -e 'ACCEPT_EULA=Y' -e "SA_PASSWORD=$SA_PASSWORD" -p $PORT_NUMBER:1433 --name $CONTAINER_NAME $IMAGE_NAME

    # Wait for a reasonable amount of time (adjust this as needed)
    echo "
    Waiting for SQL Server to start..."
#    sleep 15  # You may need to adjust this sleep time based on your SQL Server's startup time

    # Check if the container is running
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        echo "
    Application database container '$CONTAINER_NAME' running successfully!!"
    else
        echo "
    Container '$CONTAINER_NAME' failed to install!"
        exit 1
    fi

    # Execute any additional setup commands here
#    echo "No additional setup commands..."

    echo "
    Graceful exit..."
    exit 0
fi
