#!/usr/bin/env bash

host="$1"
port="$2"
shift 2

echo "Esperando $host:$port ficar disponível..."

while ! nc -z "$host" "$port"; do
  sleep 1
done

echo "$host:$port está disponível. Executando comando: $@"
exec "$@"
