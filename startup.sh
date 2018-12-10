#!/bin/bash
mkdir -p logs
docker-compose down >> logs/`date +%Y-%m-%d.log`
docker-compose up >> logs/`date +%Y-%m-%d.log` 2>>`date +%Y-%m-%d.err.log`
