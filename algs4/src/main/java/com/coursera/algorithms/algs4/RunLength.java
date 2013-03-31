package com.coursera.algorithms.algs4;

import com.coursera.algorithms.stdlib.BinaryStdIn;
import com.coursera.algorithms.stdlib.BinaryStdOut;

/*************************************************************************
 *  Compilation:  javac RunLength.java
 *  Execution:    java RunLength - < input.txt   (compress)
 *  Execution:    java RunLength + < input.txt   (expand)
 *  Dependencies: com.coursera.algorithms.stdlib.BinaryIn.java com.coursera.algorithms.stdlib.BinaryOut.java
 *
 *  Compress or expand binary input from standard input using
 *  run-length encoding.
 *
 *  % java com.coursera.algorithms.stdlib.BinaryDump 40 < 4runs.bin
 *  0000000000000001111111000000011111111111
 *  40 bits
 *
 *  This has runs of 15 0s, 7 1s, 7 0s, and 11 1s.
 *
 *  % java RunLength - < 4runs.bin | java com.coursera.algorithms.stdlib.HexDump
 *  0f 07 07 0b
 *  4 bytes
 *
 *************************************************************************/

public class RunLength {
    private static final int R   = 256;
    private static final int lgR = 8;

    public static void expand() { 
        boolean b = false; 
        while (!BinaryStdIn.isEmpty()) {
            int run = BinaryStdIn.readInt(lgR);
            for (int i = 0; i < run; i++)
                BinaryStdOut.write(b);
            b = !b;
        }
        BinaryStdOut.close();
    }

    public static void compress() { 
        char run = 0; 
        boolean old = false;
        while (!BinaryStdIn.isEmpty()) {
            boolean b = BinaryStdIn.readBoolean();
            if (b != old) {
                BinaryStdOut.write(run, lgR);
                run = 1;
                old = !old;
            }
            else { 
                if (run == R-1) { 
                    BinaryStdOut.write(run, lgR);
                    run = 0;
                    BinaryStdOut.write(run, lgR);
                }
                run++;
            } 
        } 
        BinaryStdOut.write(run, lgR);
        BinaryStdOut.close();
    }


    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new RuntimeException("Illegal command line argument");
    }

}
