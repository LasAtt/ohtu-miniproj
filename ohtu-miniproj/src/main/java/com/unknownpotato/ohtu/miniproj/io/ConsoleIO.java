package com.unknownpotato.ohtu.miniproj.io;

import static java.lang.System.in;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Input and output class for console interface
 */
@Component
public class ConsoleIO implements IO {

    /**
     * History of prints made with this IO.
     */
    private final List<String> prints;
    
    public ConsoleIO() {
        prints = new ArrayList<>();
    }

    @Override
    public String readLine(String prompt) {
        Scanner scanner = new Scanner(in);
        print(prompt + " ");
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        Scanner scanner = new Scanner(in);
        print(prompt + " ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String readCharacter(String prompt) {
        Scanner scanner = new Scanner(in);
        print(prompt + " ");
        return scanner.next().substring(0, 1);
    }

    @Override
    public void print(String toPrint) {
        out.print(toPrint);
        prints.add(toPrint);
    }

    @Override
    public void println(String toPrint) {
        print(toPrint + '\n');
    }

    @Override
    public void println() {
        println("");
    }

    @Override
    public List<String> getPrints() {
        return prints;
    }

}
