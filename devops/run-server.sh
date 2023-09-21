#!/bin/bash

# Define your DockerHub username, repository name, and tag
DOCKERHUB_USERNAME="adriannoa91"
REPO_NAME="sw-tool"
TAG="latest"

# Define your container name
CONTAINER_NAME="SW-OptimizerTool-Db"
DATA_VOLUME_NAME="SW-OptimizerTool-Volume"


# Check if Docker is installed
if ! [ -x "$(command -v docker)" ]; then
  echo "Error: Docker is not installed. Please install Docker before running this script."
  exit 1
fi

# Check if the SQL Server container is already running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
  echo "Container '$CONTAINER_NAME' is already running."
else
  # Prompt the user for SA password for the user container
  read -sp "Enter SA password for the user container: " SA_PASSWORD
  echo

  # Prompt the user for the port number
  read -p "Enter port number for the user container (default is 1433): " PORT_NUMBER
  PORT_NUMBER=${PORT_NUMBER:-1433}

  # Create the full image name
  IMAGE_NAME="$DOCKERHUB_USERNAME/$REPO_NAME:$TAG"


  # Run the SQL Server container with user-defined SA_PASSWORD and port number
  docker run -d -e 'ACCEPT_EULA=Y' -e "SA_PASSWORD=$SA_PASSWORD" -p $PORT_NUMBER:1433 --name $CONTAINER_NAME -v $DATA_VOLUME_NAME:/var/opt/mssql $IMAGE_NAME

  # Check if the container started successfully
  if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "SQL Server container '$CONTAINER_NAME' successfully deployed on port $PORT_NUMBER!"
  else
    echo "Error: Container '$CONTAINER_NAME' failed to start."
    exit 1
  fi
fi
