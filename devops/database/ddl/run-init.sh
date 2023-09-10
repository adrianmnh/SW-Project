# Wait to be sure that SQL Server came up
sleep 10s

if [ -z "$1" ]; then
		echo "Usage: $0 <SA_PASSWORD>"
		exit 1
fi

SA_PASSWORD="$1"

# Run the setup script to create the DB and the schema in the DB
# Note: make sure that your password matches what is in the Dockerfile
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P $SA_PASSWORD -d master -i db-init.sql