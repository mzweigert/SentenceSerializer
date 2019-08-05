@echo off
IF EXIST ./target/sentence-serializer*.jar (
   mvn -DskipTests exec:java -Dexec.args="%*"
) ELSE (
    mvn clean install -DskipTests exec:java -Dexec.args="%*"
)
