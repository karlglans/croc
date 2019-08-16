FROM node:8 as frontend
WORKDIR /usr/src/reactapp
ARG jar_name
ARG oauth2_redirect
#ARG oauth2_redirect
ENV REACT_APP_OAUTH2_REDIRECT="$oauth2_redirect"
COPY ./src/main/frontend3 ./
COPY ./docker/.env ./
RUN echo "oauth2_redirect: $REACT_APP_OAUTH2_REDIRECT"
RUN echo "jar_name: $jar_name"
RUN npm install
RUN npm run build

FROM maven:3.6.0-jdk-11-slim as build
WORKDIR /usr/src/jdk_appserver
COPY ./pom.xml ./
COPY ./src/main/java ./src/main/java
COPY ./src/main/resources ./src/main/resources
COPY --from=frontend /usr/src/reactapp/build ./src/main/resources/static
# -Dmaven.test.skip=true
RUN mvn clean package
# ENTRYPOINT exec java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005 "/usr/src/jdk_appserver/target/$jar_name"

FROM openjdk:8-jre-alpine
WORKDIR /home/application/java
COPY --from=build "/usr/src/jdk_appserver/target/$jar_name" .
ENTRYPOINT exec java -jar -Dspring.profiles.active=docker "/home/application/java/$jar_name"
