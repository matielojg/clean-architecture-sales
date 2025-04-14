#!/bin/bash

set -e

profiles=("dev" "test" "docker")
modules=(
  "salesorder-core-domain"
  "salesorder-core-gateway"
  "salesorder-core-usecase"
  "salesorder-adapter-repository-jpa"
  "salesorder-adapter-gateway-distributor"
  "salesorder-api-rest"
)

echo "🧪 Running tests for all modules and profiles..."

for profile in "${profiles[@]}"; do
  echo ""
  echo "🔧 PROFILE: $profile"
  export SPRING_PROFILES_ACTIVE=$profile

  for module in "${modules[@]}"; do
    echo "👉 Testing module: $module with profile: $profile"
    ./gradlew ":$module:test" --info || {
      echo "❌ Test failed in $module with profile $profile"
      exit 1
    }
  done
done

echo ""
echo "✅ All tests passed for all profiles!"
