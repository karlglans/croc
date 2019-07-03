FROM node:8 as frontend
WORKDIR /usr/src/reactapp
COPY ./src/main/frontend3 ./
RUN npm install
RUN npm run build

FROM maven:3.6.0-jdk-11-slim as serve
WORKDIR /usr/src/jdk_appserver
COPY ./pom.xml ./
COPY ./src/main/java ./src/main/java
COPY ./src/main/resources ./src/main/resources
COPY --from=frontend /usr/src/reactapp/build ./src/main/resources/static
RUN mvn clean -Dmaven.test.skip=true package
ENTRYPOINT ["java","-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005", "/usr/src/jdk_appserver/target/croc-0.0.1-SNAPSHOT.jar"]

# maybe add another buildstep for a jreserver. Keeping jdk for remote debugging