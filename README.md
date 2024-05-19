## JAVA DEVELOPER INTERNSHIP TEST ASSIGNMENT
# Autocomplete library of input text
### Setting the task
You need to write a Java console application (JDK 11) that allows you to quickly search
for airports by a certain property.

The data for the program is taken from the airports.csv file. It contains a table of airports
with properties in CSV format. The name of the airport is column 2. 
It doesn't matter what other columns are responsible for, they are used for search.

The search consists in searching for line numbers (1 column airports.csv) by the column that
is specified at the start of the program (numbering from 1).

The search is prefixed. For example, if search column 2 is specified, a Bow search will return
the numbers 3600, 4275, 7848.

### When starting the program, the following parameters are specified:

--data airports.csv — the path to the csv file with airports,

--indexed-column-id 3 — column for which the search is performed,

--input-file input-path-to-file.txt — the path to the file with the input search strings.

The format is plain text. Each line is the text that the program should
search for. Example of file contents: 
Bower 
Asa 
Ret

--output-file output-path-to-file.json is the path to the search results file. If there is no file,
it should be created, if there is, overwritten. The file format is json, containing
the following fields:

1. initTime is a number, the time in milliseconds of initialization from the start of the program to
the readiness to perform the first search. It may include, among other things, proofreading
the --input-file file.

2. result — an array, each element of which is the result of a search on
a file line --input-file. Array objects have the following fields:
a) search — search string;  
b) result — an array of line numbers suitable for the search, sorted by column  
search. The sorting is lexicographic for string columns, numeric
for numeric columns. Line number is the first column;  
c) time is the number in milliseconds spent searching the line.
Example of the file contents:

{“initTime”:100, “result”:[  
{“search”: ”Bower”, “result”: [1, 2], “time”: 10},  
{“search”: ”Asa”, “result”: [8, 4], “time”: 10},  
{“search”: ”Ret”, “result”: [5, 100], “time”: 10}  
]}


Checking for a number of conditions is carried out automatically.  
3 attempts are given to complete the task.  
The best result is taken into account.  
The check takes place on linux systems.  

## The verification process
1. Build: mvn clean package
  
2. Copying the airports-search-* artifact.jar from the target folder to the verification directory.
   
3. Automated launch of the artifact for various verification criteria: java -Xmx7m -jar
airports-search.jar --data /home/test/airports.csv --indexed-column-id 3 --input-file /temp/input1.txt
--output-file /temp/result1.json
4. Checking the results from the result files that were created by the application.
## Non-functional requirements
1. You cannot reread all lines of the file with each search.
Including reading only a specific column for each row.

2. You cannot create new files or edit the current one.
Including using a DBMS.

3. You cannot store the entire file in memory.
Not only as an array of bytes, but also in a structure that somehow contains all
the data from the file.

4. The program requires no more than 7 MB of memory to work correctly.
All java –jar launches must be executed with the -Xmx7m jvm flag.

5. The search speed should be as high as possible, taking into account the requirements above.
As a guideline, you can take a number: it takes 25 ms to search for "Bo", which outputs 68 lines,
and 5 ms to search for "Bower", which outputs 1 line without filters.

6. The search complexity is less than O(n), where n is the number of lines in the file.
   
7. The principles of OOP and SOLID must be respected.
    
8. Erroneous and marginal situations must be handled correctly.
   
9. You cannot use ready-made libraries for parsing CSV format.

If a question arises that is not covered by this statement of the problem,
the candidate must choose any solution that does not contradict the statement.
The readme should reflect the issue and the decision made.
