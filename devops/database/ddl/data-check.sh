#!/bin/bash

# Wait to be sure that SQL Server came up
if [ -z "$1" ]; then
    echo "Usage: $0 <SA_PASSWORD>"
    exit 1
fi

echo "ADRIAN.log data-check 2"
    # Check if the initialization has been done before

    query=$(/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "$SA_PASSWORD" -d SummonersWar -Q "SELECT COUNT(*) FROM GameTool.Account" -h-1 -s"," -W)
    query=$(echo $query | cut -c1)
# Check if the query result is a valid integer
    if [ $query -gt 0 ]; then echo "Yes"
        # Check the query result
        echo "ADRIAN.log Data exists in the database. 3"
        echo "DO NOTHING"
        echo "Exiting initialization complete using existing data."
    else
        echo "ADRIAN.log running db-init and data scripts 4"
        echo "Waiting 10 seconds....."
        sleep 10
        /opt/mssql-tools/bin/sqlcmd -S localhost -l 60 -U SA -P "$SA_PASSWORD" -d SummonersWar -i insert-data.sql
        echo "DATA ADDED"
    fi


echo "FINNITO datacheck complete. 2"