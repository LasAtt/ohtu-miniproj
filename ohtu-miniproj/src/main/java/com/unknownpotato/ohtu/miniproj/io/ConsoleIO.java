package com.unknownpotato.ohtu.miniproj.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 * Input and output class for console interface
 */
@Component
public class ConsoleIO implements IO {

    /**
     * Scanner to read input from InputStream
     */
    private Scanner scanner;
    /**
     * History of prints made with this io.
     */
    private List<String> prints;
    /**
     * InputStream to read.
     */
    private final InputStream in;
    /**
     * PrintStream to print to.
     */
    private final PrintStream out;

    /**
     * Default constructor with no parameters. Uses systems default input and output streams.
     */
    public ConsoleIO() {
        this(System.in, System.out);
    }
    
    /**
     * Constructor for testing, where scanner is given as a parameter.
     * @param in
     * @param out
     */
    public ConsoleIO(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        scanner = new Scanner(in);
        prints = new ArrayList<String>();
    }

    @Override
    public String readLine(String prompt) {
        String print = prompt + " ";
        out.print(print);
        prints.add(print);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        String print = prompt + " ";
        out.print(print);
        prints.add(print);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void print(String toPrint) {
        out.println(toPrint);
        prints.add(toPrint);
    }

    @Override
    public List<String> getPrints() {
        return prints;
    }
}