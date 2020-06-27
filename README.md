# score-10pin-bowling

This is a command line app that parses/processes/builds/calculates all the data (pinFalls and scores) for all the 10 frames in a bowling game played by one or more players.

The ins for the app are a text file in which every line has two tab-separated fields:
1. Player Name
2. Number Of Pins Knocked Down

In the context of this app, the mentioned line (with the two fields) is called a Player Chance. Hence, in this same context, the file that contains all this data is called Player Chances File.

## Installation

Clone this repository

```bash
git clone https://github.com/marcelomnc/score-10pin-bowling
```

Cd into the repository folder

```bash
cd score-10pin-bowling
```

Build the project using maven

```bash
mvn install
```

Maven will create a folder with the name 'target'. Cd into it

```bash
cd target
```

Inside it you will find this application's executable jar file.

```bash
ls -l
total 28
drwxr-xr-x 1 user 197609     0 Jun 25 10:21 classes/
drwxr-xr-x 1 user 197609     0 Jun 25 09:43 generated-sources/
drwxr-xr-x 1 user 197609     0 Jun 25 09:43 generated-test-sources/
-rw-r--r-- 1 user 197609     0 Jun 25 10:26 java
drwxr-xr-x 1 user 197609     0 Jun 25 10:10 maven-archiver/
drwxr-xr-x 1 user 197609     0 Jun 25 10:10 maven-status/
-rw-r--r-- 1 user 197609 24067 Jun 26 18:56 <strong>score-10pin-bowling-1.0-SNAPSHOT.jar</strong>
drwxr-xr-x 1 user 197609     0 Jun 26 07:50 surefire-reports/
drwxr-xr-x 1 user 197609     0 Jun 25 10:21 test-classes/
```

## Running

To run this application you will need a text file with the Player Chances Data that must be parsed and processed. You can execute it passing the mentioned file path as the first and unique argument like:

```bash
java -jar score-10pin-bowling-1.0-SNAPSHOT.jar [PATH_TO_YOUR_FILE]
```
