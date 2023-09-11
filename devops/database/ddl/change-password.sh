#!/bin/bash

# Prompt the user for the new SA password
read -sp "Enter new SA password: " NEW_SA_PASSWORD

# Use sqlcmd to change the SA password
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$SA_PASSWORD" -Q "ALTER LOGIN sa WITH PASSWORD='$NEW_SA_PASSWORD'"
