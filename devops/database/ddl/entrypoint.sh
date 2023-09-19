#!/bin/bash

# Wait to be sure that SQL Server came up
if [ -z "$1" ]; then
    echo "Usage: $0 <SA_PASSWORD>"
    exit 1
fi

SA_PASSWORD="$1"


echo "ADRIAN.log entrypoint 1"

if [ -e .initialized ]; then

    echo "ADRIAN.log . Has been initialized. 1.1"
    /usr/src/app/data-check.sh $SA_PASSWORD  & /opt/mssql/bin/sqlservr

else
    touch .initialized
    echo "ADRIAN.log . First Time . 1.2"

    /opt/mssql-tools/bin/sqlcmd -S localhost -l 60 -U SA -P "$SA_PASSWORD" -i db-init.sql & /usr/src/app/data-check.sh $SA_PASSWORD & /opt/mssql/bin/sqlservr

fi

echo "ADRIAN.log entrypoint complete 1"