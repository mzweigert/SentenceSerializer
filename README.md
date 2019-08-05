# Sentence serializer
Simple sentence serializer

## Software needed to run:

* Java: `1.8` [How to install](https://java.com/en/download/help/download_options.xml)
* Maven: `3.3.x` [How to install](https://maven.apache.org/install.html)
* Make sure, that you have set JAVA_HOME and MAVEN_HOME environment variables mentioned in above links.

## How to build project
Make sure, that you have installed maven and java, then in project root folder type:
`mvn clean install -DskipTests`

## How to run tests
Make sure, that you have installed maven and java, then in project root folder type:
`mvn clean install`

## How to run sentence serializer

To make sentence serialization run sentence_serializer.bat (Windows) or sentence_serializer.sh (Unix/Linux) with file given as arg:
<pre>
usage: Windows: sentence_serializer path/to/file.extension | Linux/Unix: ./sentence_serializer.sh path/to/file.extension
</pre>

## Result files
When serializer finishes work, sentences are saved as xml and csv file to `%root_project_folder%/output/`
If you want save files in other location replace `output_dir` property in `src/main/resources/config.properties` file.