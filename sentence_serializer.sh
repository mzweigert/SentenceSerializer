#!/bin/bash
if [[ -n "$(find ./target -name 'sentence-serializer*.jar' | head -1)" ]]; then
    mvn -DskipTests exec:java -Dexec.args="$*"
else
    mvn clean install -DskipTests exec:java -Dexec.args="$*"
fi
