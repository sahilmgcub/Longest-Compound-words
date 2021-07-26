# Program for Longest Compound Word

Write a program that reads a file containing a sorted list of words (one word per line, no spaces, all lower case), then identifies the longest word in the file that can be constructed by concatenating copies of shorter words also found in the file.

For example, if the file contained:


```bash
   cat
   cats
   catsdogcats
   catxdogcatsrat
   dog
   dogcatsdog
   hippopotamuses
   rat
   ratcatdogcat
```
The answer would be 'ratcatdogcat' - at 12 letters, it is the longest word made up of other words in the list. The program should then go on to report how many of the words in the list can be constructed of other words in the list.

## Steps For Code execution

Replace with the file location of the text in your machine and run the java file in a java compiler.
```
File Txtfile = new File("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\words for problem.txt");
```

## Approach

First, read words from the Txtfile
		fill up a queue with words that have suffixes, who are
		candidates to be compound words
		insert each word in a trie. 

A Trie is an advanced data structure that is sometimes also known as prefix tree or digital tree. It is a tree that stores the data in an ordered and efficient way. We generally use trie's to store strings.

Create a class pair that is used to store a pair of words and their remaining suffixes.
If no suffixes are found, which means no prefixes found
discard the pair and check the next pair.

If no suffix means it is a compound word check if the compound word is the longest
then updates the longest. then for second-longest remove the longest word and run the algorithms again and updates the second-longest.

To calculate the running time of the program 
```
long start = System.currentTimeMillis();
// some time passes
long end = System.currentTimeMillis();
long elapsedTime = end - start;
```
The time will be returned in milliseconds.
