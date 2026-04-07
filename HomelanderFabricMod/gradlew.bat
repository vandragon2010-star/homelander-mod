@REM ------------------------------------------------------------------------------
@REM Gradle distribution-specific BAT file for invoking Gradle.
@REM ------------------------------------------------------------------------------
setlocal
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%
set DEFAULT_JVM_OPTS=

set JVM_OPTS=%DEFAULT_JVM_OPTS%

if defined JAVA_HOME goto findJavaFromJavaHome
set JAVA_EXE=java.exe
%JAVA_EXE% -version >nul 2>&1
if errorlevel 1 goto findJavaFromJavaHome
goto execute

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME%
if not defined JAVA_HOME goto fail
set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if not exist "%JAVA_EXE%" goto fail

goto execute

:fail
echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
exit /b 1

:execute
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
"%JAVA_EXE%" %JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
exit /b %errorlevel%
