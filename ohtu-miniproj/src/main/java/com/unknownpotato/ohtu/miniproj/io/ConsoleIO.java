
package com.unknownpotato.ohtu.miniproj.io;

import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIO implements IO {
    
    private Scanner scanner;

    public ConsoleIO () {
        scanner = new Scanner(System.in);
    }

    public String readLine(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt + " ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }
}