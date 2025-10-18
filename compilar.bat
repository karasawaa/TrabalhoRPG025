@echo off
echo Compilando todos os arquivos Java...
javac *.java
if %errorlevel% == 0 (
    echo Compilacao concluida com sucesso!
    echo.
    echo Executando o jogo...
    java RPGMedieval
) else (
    echo Erro na compilacao!
)
pause