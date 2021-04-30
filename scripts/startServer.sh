#!/bin/bash
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 237262788837.dkr.ecr.us-east-1.amazonaws.com
docker pull 237262788837.dkr.ecr.us-east-1.amazonaws.com/ship-finder:latest
docker run --log-driver=awslogs --log-opt awslogs-region=us-east-1 --log-opt awslogs-group=docker-ec2 -d --name shipFinder -p 8080:8080 237262788837.dkr.ecr.us-east-1.amazonaws.com/ship-finder
