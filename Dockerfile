FROM openjdk:11-slim
COPY target/vacation-pay-calculator-1.0-SNAPSHOT.jar /calculator.jar
ENTRYPOINT exec java -jar /calculator.jar