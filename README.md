# About the solution

The program 
1. Reads records from provided output file, and parse the record to a CounterRecord case class
2. Aggregate the records to get expected statisitcs 
3. Display the results in console

Error handling
1. The programm checks if file path for the output file is given, and throws error if no provided
2. If the file is corrupted, or program fails to extract records (like missing count, or incorrect date format), runtime exception will be thrown.
3. I have tried using Either / Try to handle exception in a different branch [error-handling](https://github.com/ethanli83/ethanscalatest/tree/error-handling). In the branch, runtime error will be handled at top level. Skipping incorrect record can be implemented in the branch.

# Get started
The project is built with SBT. Please refers to [Installing SBT](https://www.scala-sbt.org/1.x/docs/Setup.html) for setup instruction.

To run the program, use following command:
```shell
sbt "run ./data/outputs"
```

Expected outputs:
```
Total count: 398

Daily count:
Date: 2016-12-01 Count: 179
Date: 2016-12-05 Count: 81
Date: 2016-12-08 Count: 134
Date: 2016-12-09 Count: 4

Top 3 records with most count:
Datetime: 2016-12-01T07:30 Count: 46
Datetime: 2016-12-01T08:00 Count: 42
Datetime: 2016-12-08T18:00 Count: 33

Done!
```

To run all tests, use following command:
```shell
sbt test
```
