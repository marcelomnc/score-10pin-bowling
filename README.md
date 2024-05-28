# java-challenge

This is a **JAVA** command line app that parses/processes/builds/calculates/prints all the data (**pin falls** and **scores**) for all the 10 frames in a bowling game played by one or more players.

The input for the app is a text file in which every line has two tab-separated fields:

1. Player Name
2. Number Of Pins Knocked Down

In the context of this app, the mentioned line (the one with the two fields) is called a **Player Chance**. Hence, in this same context, the file that contains all this data is called **Player Chances File**.

## Requirements

This app requires the following tools, so it can be built/executed:

1. Apache Maven 3.9.6+
2. Java 8

## Installation

Cd into this project's parent folder:

```bash
cd JavaChallenge
```

Build the project using **maven**:

```bash
mvn clean install
```

Maven will create a folder with the name **target**. Cd into it:

```bash
cd target
```

Inside it, you will find the app's executable jar file (`java-challenge-2.0-SNAPSHOT.jar`):

```bash
ls -l
total 200
drwxr-xr-x   4 marcelomnc  staff    128 May 22 22:58 classes
drwxr-xr-x   3 marcelomnc  staff     96 May 22 22:58 generated-sources
drwxr-xr-x   3 marcelomnc  staff     96 May 22 22:58 generated-test-sources
-rw-r--r--   1 marcelomnc  staff  68435 May 22 22:58 jacoco.exec
-rw-r--r--   1 marcelomnc  staff  30598 May 22 22:58 java-challenge-2.0-SNAPSHOT.jar
drwxr-xr-x   3 marcelomnc  staff     96 May 22 22:58 maven-archiver
drwxr-xr-x   3 marcelomnc  staff     96 May 22 22:58 maven-status
drwxr-xr-x   3 marcelomnc  staff     96 May 22 22:58 site
drwxr-xr-x  28 marcelomnc  staff    896 May 22 22:58 surefire-reports
drwxr-xr-x   5 marcelomnc  staff    160 May 22 22:58 test-classes
```

## Running

To run this app you will need a text file (The **Player Chances File**) with all the data that must be parsed and processed. You can execute it setting the mentioned file path as the first and unique argument on the command line as follows:

```bash
java -jar java-challenge-2.0-SNAPSHOT.jar [PATH_TO_YOUR_FILE]
```

> **NOTE:** If the path to your file contains spaces (`" "`), don't forget to enclose it using double quotation marks as in: `"/folder/name with spaces/more spaces/file.txt"`.

### Sample File

This is a sample data for the **Player Chances File**:

        Jeff	10
        John	3
        John	7
        Jeff	7
        Jeff	3
        John	6
        John	3
        Jeff	9
        Jeff	0
        John	10
        Jeff	10
        John	8
        John	1
        Jeff	0
        Jeff	8
        John	10
        Jeff	8
        Jeff	2
        John	10
        Jeff	F
        Jeff	6
        John	9
        John	0
        Jeff	10
        John	7
        John	3
        Jeff	10
        John	4
        John	4
        Jeff	10
        Jeff	8
        Jeff	1
        John	10
        John	9
        John	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Zeroes	0
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Fouls	F
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10
        Perfect	10

The printed output by the app for the file above would be as follows:

        Frame           1               2               3               4               5               6               7               8               9               10
        Jeff
        PinFalls                X       7       /       9       0               X       0       8       8       /       F       6               X               X       X       8       1
        Score           20              39              48              66              74              84              90              120             148             167
        John
        PinFalls        3       /       6       3               X       8       1               X               X       9       0       7       /       4       4       X       9       0
        Score           16              25              44              53              82              101             110             124             132             151
        Zeroes
        PinFalls        0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0
        Score           0               0               0               0               0               0               0               0               0               0
        Fouls
        PinFalls        F       F       F       F       F       F       F       F       F       F       F       F       F       F       F       F       F       F       F       F
        Score           0               0               0               0               0               0               0               0               0               0
        Perfect
        PinFalls                X               X               X               X               X               X               X               X               X       X       X       X
        Score           30              60              90              120             150             180             210             240             270             300

## Validations

The app will validate the following conditions before it starts to parse the **Player Chances File**:

1. Only 1 argument is passed from the command line (i.e. The **Player Chances File** path)
2. A file exists on the specified path
3. The file is NOT empty

The app will print a message when any of the above conditions are not met:

        This application expects just one argument (the input file path) in the command line, no more, no less.
        
        Player Chances File Errors Encountered:
        Error: File in path [/Users/marcelomnc/Downloads/_Challenge/Code Challenge/Repo/JavaChallenge/src/test/resources/positive/scores-.txt] not found. Application execution aborted.

        Player Chances File Errors Encountered:
        Error: File in path [/Users/marcelomnc/Downloads/_Challenge/Code Challenge/Repo/JavaChallenge/src/test/resources/negative/empty.txt] is empty. Application execution aborted.

If an ``IOException`` is thrown while trying to read the file, a message will be printed out, then the app will exit:

        An exception occurred while trying to read the player chances file on path: [C:\Temp\score10pinbowling.txt]. Application execution aborted.

The app validates these conditions for each line inside the **Player Chances File**:

1. Line has a field separator (tab)
2. Line has 2 fields only (player's name and knocked down pins quantity)
3. Line has a value for 'knocked down pins' field that is between 0 and 10 (both inclusive), or an F to indicate a Foul

If the app finds lines on which the conditions above are not met, it will print an **error report** with the following info:

1. Line number
2. Line
3. Error message

Given a **Player Chances File** with these lines:

        1.dsasd
        2.
        3.67723	ughgg
        4.
        5.              
        6.			   
        7.jdjsdhaak
        8.
        9.Marcus	10	12
        10.Marcus	10
        11.Marcus	10
        12.Marcus	10
        13.Marcus	10
        14.8999	89

The **error report** printed by the app would be as follows:

        Player Chances File Errors Encountered:

        Line Number: 1
        Original line: dsasd
        Error: Line has no field separator.
        
        Line Number: 2
        Original line:
        Error: Line has no field separator.
        
        Line Number: 3
        Original line: 67723    ughgg
        Error: The value for 'knocked down pins' field must be a number (0-10) or an F to indicate a foul. Found value: [ughgg].
        
        Line Number: 4
        Original line:
        Error: Line has no field separator.
        
        Line Number: 5
        Original line:
        Error: Line has no field separator.
        
        Line Number: 6
        Original line:
        Error: Line has no field separator.
        
        Line Number: 7
        Original line: jdjsdhaak
        Error: Line has no field separator.
        
        Line Number: 8
        Original line:
        Error: Line has no field separator.
        
        Line Number: 9
        Original line: Marcus   10      12
        Error: Line must have 2 fields only, no more no less.
        
        Line Number: 14
        Original line: 8999     89
        Error: The value for 'knocked down pins' field must be a value between 0 and 10. Found value: [89].


**The app only processes/builds/calculates games data when all lines inside Player Chances File are valid**.

## Game Invalidations

The app might invalidate a player's game if any of the following conditions are met:

1. Game exceeds max chances (i.e. All 10 frames of the game have been processed/completed and there are **STILL** chances data to process)
2. Game has not enough chances data (i.e. Less than 10 frames have been processed/completed and there are **NO** more chances data to process)
3. Any frame of the game exceeds the maximum pin fall sum value of 10.

The app will print a message when any of the above conditions are met:
        
        Invalid games: 

        Paul
        Invalidation message: Frame 10: : Max player chances (scores/throws) exceeded.        

        Leopold
        Invalidation message: There are not enough player chances (scores/throws) data in the input file to build the game's information completely.
        
        Logan
        Invalidation message: Frame 4: Exceeds pin falls max sum of 10. Calculated sum: [19].

**The app will calculate/print the scores for valid games only**.

If **NO** valid games are present, a message will be printed:

        No valid games to print.

## Links

Rules for scoring a bowling game can be found on youtube **[here](https://www.youtube.com/watch?v=aBe71sD8o8c)** and **[here](https://www.youtube.com/watch?v=wY-fT2Gxa5s)**.