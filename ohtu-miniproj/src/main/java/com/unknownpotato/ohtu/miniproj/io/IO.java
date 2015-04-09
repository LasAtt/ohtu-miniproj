package com.unknownpotato.ohtu.miniproj.io;

import java.util.List;

/**
 * IO interface
 */
public interface IO {
    /**
     * Read a line from input.
     * @param prompt to be printed
     * @return read line
     */
    String readLine(String prompt);
    /**
     * Read an integer from input
     * @param prompt to be printed
     * @return read integer
     */
    int readInt(String prompt);
    /**
     * Print a line to output
     * @param line to be printed
     */
    void print(String toPrint);
    /**
     * Get everything printed in print()-method as an arraylis
     * @return all printed lines
     */
    List<String> getPrints();
}
