package com.coursera.algorithms; /*************************************************************************
 *  Compilation:  javac com.coursera.algorithms.stdlib.PictureDump.java
 *  Execution:    java com.coursera.algorithms.stdlib.PictureDump width height < file
 *  Dependencies: com.coursera.algorithms.stdlib.BinaryStdIn.java com.coursera.algorithms.stdlib.Picture.java
 *  Data file:    http://introcs.cs.princeton.edu/stdlib/abra.txt
 *  
 *  Reads in a binary file and writes out the bits as w-by-h picture,
 *  with the 1 bits in black and the 0 bits in white.
 *
 *  % more abra.txt 
 *  ABRACADABRA!
 *
 *  % java com.coursera.algorithms.stdlib.PictureDump 16 6 < abra.txt
 *
 *************************************************************************/
import com.coursera.algorithms.stdlib.BinaryStdIn;
import com.coursera.algorithms.stdlib.Picture;
import com.coursera.algorithms.stdlib.StdOut;

import java.awt.Color;

public class PictureDump {

    public static void main(String[] args) {
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        Picture pic = new Picture(width, height);
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pic.set(j, i, Color.RED);
                if (!BinaryStdIn.isEmpty()) {
                    count++;
                    boolean bit = BinaryStdIn.readBoolean();
                    if (bit) pic.set(j, i, Color.BLACK);
                    else     pic.set(j, i, Color.WHITE);
                }
            }
        }
        pic.show();
        StdOut.println(count + " bits");
    }
}
