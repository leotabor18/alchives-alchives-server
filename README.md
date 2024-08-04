# CashPayClinic Server
# Introduction
Server that is responsible for general server functionality.

# Getting Started
## Pre-requisite
1. [Homebrew](https://docs.brew.sh/)
2. [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
3. [Apache Maven](https://maven.apache.org/download.cgi)
4. [MySQL WorkBench](https://dev.mysql.com/downloads/workbench/)

## Installation
### Setup
**Step 1:** Clone this repository\
**Step 2:** Go to cloned repository directory\
**Step 3** Create MySQL Database, User schema locally
```
-- Create SQL Server Database for local alchives or use the current alchives database
CREATE DATABASE alchives CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Use the database
USE `alchives`;

DROP USER 'server_user_local_login'@'localhost';

-- Create MySQL User
CREATE USER 'server_user_local_login'@'localhost' IDENTIFIED BY '!Password1';

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON `alchives`.* TO 'server_user_local_login'@'localhost';

```
**Step 4:** Create the local .env file
```
BASE_PATH=/api
DB_URL=jdbc:mysql://localhost:3306/alchives?allowPublicKeyRetrieval=true&useSSL=false
DB_USER=root
DB_PASSWORD=
DB_DRIVER=com.mysql.cj.jdbc.Driver
SHOW_SQL=true
OPEN_IN_VIEW=false

UI_SERVICE_HOST=http://localhost:5173
ALLOWED_ORIGINS=http://localhost:8081,http://localhost:8080,http://localhost:5173
JWT_EXPIRATION_MS=3600000
JWT_REFRESH_EXP_MS=86400000

MAIL_SERVER_HOST = smtp.gmail.com
MAIL_SERVER_PORT = 587
MAIL_SERVER_PROTOCOL = smtp
MAIL_SERVER_USERNAME = cocoon.1718@gmail.com
MAIL_SERVER_PASSWORD = 
MAIL_SERVER_FROM      = no-reply@akbay.com
MAIL_SERVER_NAME      = Akbay

BASE_URL=http://localhost:5173
# BASE_URL=<CHANGE IT TO HOSTNAME>

STORAGE_URL = 
STORAGE_PATH_URL =
STORAGE_ACCESS_KEY = 
STORAGE_SECRET_KEY = 

```
**Step 4:** Export environment variables using `export $(cat .env | tr -d ' ' | grep -v "#" | xargs)`\
**Step 5:** Clean and install the build artifact into the local repository with `mvn clean install`

## Build and Run
**Step 1:** Run development server locally with `mvn spring-boot:run`

docker run --rm --name alchives-server \
  -e "BASE_PATH=/api" \
  -e "DB_URL=jdbc:mysql://alchives-db-do-user-15962280-0.c.db.ondigitalocean.com:25060/defaultdb?allowPublicKeyRetrieval=true&useSSL=false" \
  -e "DB_USERNAME=doadmin" \
  -e "DB_PASSWORD=AVNS_eZbBVt_nLTPA7a2qQSF" \
  -e "DB_DRIVER=com.mysql.cj.jdbc.Driver" \
  -e "SHOW_SQL=true" \
  -e "OPEN_IN_VIEW=false" \
  -e "UI_SERVICE_HOST=https://alchives.site" \
  -e "ALLOWED_ORIGINS=http://localhost:3000,http://localhost:5173,https://alchives.site" \
  -e "JWT_EXPIRATION_MS=3600000" \
  -e "JWT_REFRESH_EXP_MS=86400000" \
  -e "MAIL_SERVER_HOST=smtp.gmail.com" \
  -e "MAIL_SERVER_PORT=465" \
  -e "MAIL_SERVER_PROTOCOL=smtp" \
  -e "MAIL_SERVER_USERNAME=al.chives01@gmail.com" \
  -e "MAIL_SERVER_PASSWORD=gmpt tbxv gcxj xvxz" \
  -e "MAIL_SERVER_FROM=no-reply@alchives.com" \
  -e "MAIL_SERVER_NAME=Al-Chives" \
  -e "BASE_URL=https://alchives.site" \
  -e "STORAGE_URL=sgp1.digitaloceanspaces.com" \
  -e "STORAGE_PATH_URL=https://alchives-cdn.sgp1.digitaloceanspaces.com" \
  -e "STORAGE_ACCESS_KEY=DO00JZRCAXZFJXUG6UWQ" \
  -e "STORAGE_SECRET_KEY=vXq2GvOcK/RMa036lvmsLEImVrzn26dK/21lJK2ofeM" \
  -p 80:9000 -d leotabor/alchives-server:1.0```