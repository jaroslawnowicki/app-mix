# app-mix
Apps mix

Technologies in project: 
- Spring Boot and Spring Cloud
- Keycloak
- Apache Kafka (Kyro Serialize)
- MongoDB
- Neo4j
- Spock 
- Groovy
- Jacoco
- Kotlin
- Play Framework
- Scala with Akka Actor 
- Gradle
- Sbt


> gradle clean build 
> gradle jacocoTestReport

# Keycloak

After logon add new realm and import file realm-export from directory resources.
After import file, add user and user must join to group G_USER and G_ADMIN.


#Upgrade gradle

sudo add-apt-repository ppa:cwchien/gradle
sudo apt-get update
sudo apt upgrade gradle

#Angular

npm install -g @angular/cli
npm cache clean -f
npm install -g n
n stable
ng new app-ui --routing
cd app-ui
ng serve 

ng generate module apache-kafka --routing

