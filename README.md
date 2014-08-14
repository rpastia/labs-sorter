Sorter
===========

This is a simple application that implements quicksort and mergesort for experimental and demonstrative purposes.

The algorithms are implemented such that they work with any all Java objects that implement the Comparable interface. 
The application that runs these algorithms handles input of type Integer, Float, or String.

### Requirements ###

In order to build and run Sorter you will need: 
* Java 1.7
* Maven

### Building ###

You will need `Maven` to compile the project. Run the following command in the project root directory:

```
mvn package
```

### Running ###

Usage: SorterApp quick|merge input_file

You should run the Sorter by using the `target/sorter-${VERSION}-jar-with-dependencies.jar` 
(replace `${VERSION}` with the actual version) file created by `maven`.

The JAR file contains a main class manifest so you can run it like this:

```
java -jar target/sorter-${VERSION}.jar quick input.txt
```

Output will be sent to `STDOUT`. Other info will be sent to `STDERR`.

##### Formatting the input #####

The application reads it's input from an input file named by the second parameter. Each line in this file constitutes an entry in the input array.
The application automatically detects the type of the input data (`Integer`, `Float` or `String`) based on the first element of the input array (the first line in the file).
If the first element is "1" the whole set will be parsed as `Integer`. If the first element is "1.0" the whole set will be parsed as `Float`. Elements that can't be parsed will be skipped.


### Architecture ###

A few considerations about some architectural decisions:

The application also presents the user with some statistics about the run (runtime and number of comparisons). This is accomplished via the `SortCallback` interface. 
The Sorter class uses a `SortCallback` object to send various callbacks; thus this functionality is decoupled from the actual sorting.

Once the application detects the type of the input data, it will create an instance of an anonymous class that implements the `StringCaster` interface and it will use that
object to cast each input to the detected type.
