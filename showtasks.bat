:runcrud
call runcrud.bat
if "%ERRORLEVEL%" == "0" goto :openFirefox
echo.
echo Runcrud script has errors
goto fail

:openFirefox
start firefox http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
goto fail
echo.
echo Error during opening Firefox

:fail
echo.
echo Script has errors

:end
echo.
echo Script has been successfully executed
