#!/bin/bash

echo "Validating the service"
if curl -s http://localhost | grep "Register"; then
  echo "App is running successfully"
else
  echo "App failed to start"
  exit 1
fi