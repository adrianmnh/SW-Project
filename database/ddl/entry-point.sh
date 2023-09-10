# Wait to be sure that SQL Server came up

if [ -z "$1" ]; then
		echo "Usage: $0 <SA_PASSWORD>"
		exit 1
fi

SA_PASSWORD="$1"

# Run Microsoft SQl Server and initialization script (at the same time)
/usr/src/app/run-init.sh $SA_PASSWORD  & /opt/mssql/bin/sqlservr