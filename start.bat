@echo off

for /f "tokens=5" %%a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do taskkill /f /pid %%a

start /min java -jar -Dspring.profiles.active=prod build/libs/bcmanager-0.0.1.jar
