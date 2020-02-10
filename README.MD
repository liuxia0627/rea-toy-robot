# README #
This repository implements the solution to the Toy Robot Simulator.
## Getting Started ##
### Dependencies ###
* You will need to install the following:
    * [Java jdk 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    * [Gradle  6.1.1](https://gradle.org/install/)
* Setup your path to have the following values
    * JAVA_HOME = /java/location/java/jdk1.8.0/bin
    * GRADLE_HOME= /gradle/location/gradle/gradle-6.1.1-bin/gradle-6.1.1
To check if Java and Gradle are installed and configured correctly, you can verify their versions by running the following command:
```robotframework
    java -version
```
```robotframework
    gradle -v
```
This will display the installed versions of java and gradle.
### Build  ###
To build project, please run the following command from the root directory:
```robotframework
    gradle build
```
The build JAR along with sample commands can be found in the ./build/libs/ directory.
### Test ###
Unit tests already runs by executing above command to build the project
### Usage ###
You can run the toy robot simulator by executing the following command in the ./build/libs/ directory which contains the jar:
```sh
java -jar rea-toy-robot-1.0-SNAPSHOT.jar [commands.txt]
```
You can invoke the simulator in any of the two following options:
1. A plain text file which contains commands(one command per line).
2. Commands are retrieved from stdin if there is no command file defined
To get more details on the commands and the react to these commands, please refer to [PROBLEM.MD](https://github.com/liuxia0627/rea-toy-robot/blob/master/PROBLEM.MD)
### Design ###
To get further explanation of design and approach for this app, please refer to [DESIGN.MD](https://github.com/liuxia0627/rea-toy-robot/blob/master/DESIGN.MD)














