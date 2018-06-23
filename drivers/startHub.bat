@echo on
set JAVA_JRE_BIN=C:/Program Files (x86)/Java/jre1.8.0_51/bin/java.exe
java -jar "selenium-server-standalone-3.9.1.jar" -role hub -hubConfig "hubconfig.json"
pause
@echo off