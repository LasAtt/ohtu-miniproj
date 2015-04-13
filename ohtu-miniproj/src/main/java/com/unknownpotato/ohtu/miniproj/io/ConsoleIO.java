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
     * History of prints made with this io.
     */
    private List<String> prints;

    public ConsoleIO() {
        prints = new ArrayList<String>();
    }

    @Override
    public String readLine(String prompt) {
        Scanner scanner = new Scanner(System.in);
        printPrompt(prompt);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        Scanner scanner = new Scanner(System.in);
        printPrompt(prompt);
        return Integer.parseInt(scanner.nextLine());
    }
    
    private void printPrompt(String prompt) {
        String print = prompt + " ";
        System.out.print(print);
        prints.add(print);
    }
    
    @Override
    public void print(String toPrint) {
        System.out.print(toPrint);
        prints.add(toPrint);
    }
    
    public void println(String toPrint) {
        print(toPrint + '\n');
    }

    @Override
    public List<String> getPrints() {
        return prints;
    }
}