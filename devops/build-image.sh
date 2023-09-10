#!/bin/bash

# Define the name of the container you want to commit
CONTAINER_NAME="test-container"

# Define the name and tag for the new Docker image
IMAGE_NAME="adriannoa91/sw-tool"
IMAGE_TAG="v2.0"

# Check if the container with the given name exists
if [ "$(docker ps -a -q -f name=$CONTAINER_NAME)" ]; then
    echo "Found container '$CONTAINER_NAME'. Committing it to create an image..."
    wait 10

    # Commit the container to create a new image
    IMAGE_ID=$(docker commit $CONTAINER_NAME $IMAGE_NAME:$IMAGE_TAG)

    # Check if the image was successfully created
    if [ $? -eq 0 ]; then
        echo "Image '$IMAGE_NAME:$IMAGE_TAG' created with ID: $IMAGE_ID"

        # Log in to Docker Hub (replace with your Docker Hub credentials)
        docker login -u adriannoa91

        # Push the new image to Docker Hub
        docker push $IMAGE_NAME:$IMAGE_TAG
        docker push $IMAGE_NAME:latest  # Push with the 'latest' tag

        if [ $? -eq 0 ]; then
            echo "Image '$IMAGE_NAME:$IMAGE_TAG' pushed to Docker Hub successfully."
            echo "Image '$IMAGE_NAME:latest' pushed to Docker Hub successfully."
        else
            echo "Failed to push image to Docker Hub."
            exit 1
        fi
    else
        echo "Failed to create image from container '$CONTAINER_NAME'."
        exit 1
    fi
else
    echo "Container '$CONTAINER_NAME' not found."
    exit 1
fi
