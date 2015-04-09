package com.unknownpotato.ohtu.miniproj.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Teemu
 */
public class StubIO implements IO {
    
    private Scanner scanner;
    private List<String> prints;

    public StubIO() {
        scanner = new Scanner(System.in);
        prints = new ArrayList<String>();
    }

    @Override
    public String readLine(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    @Override
    public int readInt(String prompt) {
        System.out.print(prompt + " ");
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
