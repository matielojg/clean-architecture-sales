#!/bin/bash
#
#echo "🔄 Parando todos os daemons do Gradle..."
#./gradlew --stop
#
#echo "🧹 Limpando cache local do projeto..."
#rm -rf .gradle
#rm -rf build/ */build/
#
#echo "🛠️ Executando build com cache local..."
#./gradlew clean build --build-cache
#
#if [ $? -ne 0 ]; then
#    echo "❌ Build falhou."
#    exit 1
#fi
#
#echo "🚀 Iniciando módulos da aplicação..."

# Start resale-api-rest em background
./gradlew :resale-api-rest:bootRun -Dspring.profiles.active=dev &
PID_RESALE=$!

# Start salesorder-api-rest em background com resources corretos
./gradlew :salesorder-api-rest:bootRun -Dspring.profiles.active=dev \
    -Dspring.config.location=classpath:/application.yml &
PID_SALESORDER=$!

# Espera CTRL+C e mata os processos
trap "echo '🛑 Encerrando apps...'; kill $PID_RESALE $PID_SALESORDER" SIGINT

wait


#tree -I 'build|.gradle|.git|.class|.jar|.bin|.iml|*.db|out|tmp' . | tee /dev/tty | xclip -selection clipboard
