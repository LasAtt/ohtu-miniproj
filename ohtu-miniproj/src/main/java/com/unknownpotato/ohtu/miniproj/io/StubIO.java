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
        println(prompt);
        return Integer.parseInt(lines[i++]);
    }

    @Override
    public void println(String toPrint) {
        print(toPrint + '\n');
    }

    @Override
    public List<String> getPrints() {
        return prints;
    }
    
    @Override
    public String readLine(String prompt) {
        println(prompt);
        if (i < lines.length) {
            return lines[i++];
        }
        return "";
    }

    @Override
    public void print(String toPrint) {
        prints.add(toPrint);
    }

    @Override
    public void println() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String readCharacter(String prompt) {
        return lines[i++].substring(0, 1);
    }
}
