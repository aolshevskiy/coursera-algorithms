package com.coursera.algorithms.stdlib;

/*************************************************************************
 *  Compilation:  javac com.coursera.algorithms.stdlib.Copy.java
 *  Execution:    java com.coursera.algorithms.stdlib.Copy < file
 *  Dependencies: com.coursera.algorithms.stdlib.BinaryStdIn.java com.coursera.algorithms.stdlib.BinaryStdOut.java
 *  
 *  Reads in a binary file from standard input and writes it to standard output.
 *
 *  % java com.coursera.algorithms.stdlib.Copy < mandrill.jpg > copy.jpg
 *
 *  %  diff mandrill.jpg copy.jpg
 *
 *************************************************************************/

public class Copy {

    public static void main(String[] args) {
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(c);
        }
        BinaryStdOut.flush();
    }
}
