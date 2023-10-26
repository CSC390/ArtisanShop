#!/bin/bash
sudo su
yum update -y
yum install -y jq
sudo yum install -y nodejs
sudo yum install -y git
sudo npm i -g pm2
sudo yum install -y nginx
#sudo systemctl start nginx
#sudo systemctl enable nginx
echo “Hello World'