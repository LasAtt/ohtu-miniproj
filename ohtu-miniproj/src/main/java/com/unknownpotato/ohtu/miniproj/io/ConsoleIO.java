package com.unknownpotato.ohtu.miniproj.io;

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
     * Scanner to read input from System.in
     */
    private Scanner scanner;
    /**
     * History of prints made with this io.
     */
    private List<String> prints;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
        prints = new ArrayList<String>();
    }

    @Override
    public String readLine(String prompt) {
        String print = prompt + " ";
        System.out.print(print);
        prints.add(print);
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        String print = prompt + " ";
        System.out.print(print);
        prints.add(print);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
        prints.add(toPrint);
    }

    @Override
    public List<String> getPrints() {
        return prints;
    }
}