# mediscren_report

Technology and Prerequisites

JAVA 8 JDk

Springboot

Maven

Docker

Installing

Install Java: - https://www.oracle.com/fr/java/technologies/javase-downloads.html

Install Maven - https://maven.apache.org/install.html

Install Docker: - https://www.docker.com/products/docker-desktop

MicroServices Details

Port:8704 - mediscreen_report

This microservice defines the risk's level of a patient's diabetes. 

It based on the data retrieved by FeignClient thanks to Patient and note.


Run Application

Install the prerequisites and Technology list above.

1️⃣ Build .jar

Open your terminal and go to each microServices directory and run this command :


▶️ SYNTAX = mvn clean package


thanks to this command you build .jar


you have to build jar to run docker-compose.


Docker


Open your terminal and go to the directory containing docker-compose.yml
▶️ SYNTAX = docker-compose up -d
