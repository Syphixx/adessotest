@echo on
java -Dwebdriver.chrome.driver="chromedriver.exe" -Dwebdriver.ie.driver="IEDriverServer64.exe" -jar "selenium-server-standalone-3.9.1.jar" -role node -nodeConfig "nodeconfig.json"
pause
@echo off