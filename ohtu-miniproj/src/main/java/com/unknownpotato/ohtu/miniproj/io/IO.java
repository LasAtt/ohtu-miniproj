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
     * Reads one character into String from input
     * @param prompt to be printed
     * @return read character in a string
     */
    String readCharacter(String prompt);
    /**
     * Print a line to output
     * @param toPrint line to be printed
     */
    void print(String toPrint);
    /**
     * Print a line followed by line break to output.
     * @param toPrint line to be printed
     */
    void println(String toPrint);
    /**
     * Print an linebreak.
     */
    void println();
    /**
     * Get everything printed in print()-method as an arraylist.
     * @return all printed lines
     */
    List<String> getPrints();
}
