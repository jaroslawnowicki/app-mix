# app-mix
Apps mix

Technologies in project: 
- Spring Boot and Spring Cloud
- keycloak
- Apache Kafka
- MongoDB
- Neo4j
- Spock 
- Groovy
- Jacoco
- Kotlin

> gradle clean build 
> gradle jacocoTestReport

# Keycloak

After logon add new realm and import file realm-export from directory resources.
After import file, add user and user must join to group G_USER and G_ADMIN.


#Upgrade gradle

sudo add-apt-repository ppa:cwchien/gradle
sudo apt-get update
sudo apt upgrade gradle
