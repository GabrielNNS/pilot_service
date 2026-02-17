Write-Host "### Starting... ###"

$env:SPRING_PROFILES_ACTIVE = "dev"
$env:DB_NAME="pilot-db"
$env:DB_URL = "jdbc:postgresql://localhost:5433/pilot-db"
$env:DB_USER = "pilot-user"
$env:DB_PASSWORD = "12345"

Write-Host "### Docker... ###"
docker compose up -d
Start-Sleep -Seconds 10

Write-Host "### Spring Boot... ###"
Write-Host "### Press CTRL+C to close. ###"

try {
    & ./gradlew.bat bootRun
}
catch {
    Write-Host "### Error... ###" -ForegroundColor Red
}
finally {
    Write-Host "### Stoping... ###"
    docker compose stop
    Write-Host "### Closed! ###" -ForegroundColor Green
}