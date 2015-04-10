package com.unknownpotato.ohtu.miniproj.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Teemu
 */
public class StubIO implements IO {
    
    private String[] lines;
    private Scanner scanner;
    private List<String> prints;
    private int i = 0;

    public StubIO(String... values) {
        scanner = new Scanner(System.in);
        prints = new ArrayList<String>();
        this.lines = values;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(lines[i++]);
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
    
    @Override
    public String readLine(String prompt) {
        print(prompt);
        if (i < lines.length) {
            return lines[i++];
        }
        return "";
    }
}
