# Project Name
> Spring Boot Apache kafka 

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Todo](#todo)
* [Status](#status)
* [Contact](#contact)

## General info
This project lets you push JSON content on Kafka and consume it , also saves content in file .

## Technologies
* Java 8
* Apache Kafka 
* Zookeeper 
* Docker images for zookeeper & kafka

## Setup
###### Docker
* Step 1) run command `docker-compose up` in docker(directory where docker-compose.yml is present)
* Step 2) `mvn clean install` and java -jar 'jar_name'

###### Local Environment
* Step 1) Install zookeeper and kafka locally.
* Step 2) Run project as 'Run as Java Application' in Eclipse.

## Todo:
* Added loadTest.json file for load testing . It is a json file so include your custom JSON payload 
  inside json Array.
  `[{},{}]`
* JSON Payload example
  ```{
    "eventType":"userCreated",
    "customerName":"Ajinkya",
    "customerAddress":"Address"
  }```
* Topic Names are mentioned in application.properties file       
  `Note :- By default auto creation of topic is enabled `.        
  But if you keep different eventType value in JSON payload and that eventType is not present in
  application.properties ,Custom Exception is thrown.          
* I have kept topic names , path for loading load test file , filename pattern , directory pattern
  , maxFilesize , maxWaitTime as configurable in application.properties.       

## Status
Project is:  _in progress_

## Contact
Created by Ajinkya Karode - feel free to contact me!(akarode199@gmail.com)