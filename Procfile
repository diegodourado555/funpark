web: java $JAVA_OPTS -Xmx256m -jar target/*.jar --spring.profiles.active=prod,heroku,no-liquibase --server.port=$PORT 
release: cp -R src/main/resources/config config && ./mvnw -ntp liquibase:update -Pprod,heroku
web: java -jar target/funpark-0.0.1-SNAPSHOT.jar