# Wait to be sure that SQL Server came up

if [ -z "$1" ]; then
		echo "Usage: $0 <SA_PASSWORD>"
		exit 1
fi

SA_PASSWORD="$1"


# Run database initialization script and Microsoft SQl Server (at the same time)
# /usr/src/app/run-init.sh $SA_PASSWORD  & /opt/mssql/bin/sqlservr
/opt/mssql-tools/bin/sqlcmd -S localhost -l 60 -U SA -P "$SA_PASSWORD" -i db-init.sql & /opt/mssql/bin/sqlservr