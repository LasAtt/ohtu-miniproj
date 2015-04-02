/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.ui;

import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import java.util.Scanner;

/**
 *
 * @author axwikstr
 */
public class TextUi {
    
    private static Scanner reader = new Scanner(System.in);

    public static void run() {
        int choice = 0;
        while (true) {
             System.out.println("Type 1 to add a new book type reference, 2 to list all book type references, 3 to export to BibTex or 4 to quit");
             choice = Integer.parseInt(reader.nextLine());
             if (choice == 1) {
                 System.out.println("Author: ");
                 String author = reader.nextLine();
                 System.out.println("Title: ");
                 String title = reader.nextLine();
                 System.out.println("Publisher: ");
                 String publisher = reader.nextLine();
                 System.out.println("Year: ");
                 String year = reader.nextLine();
                 ReferenceFactory.createBookReference(author, title, publisher, year);
                 continue;
             } else if (choice == 2) {
                 
                 System.out.println("To be implemented!");
                 continue;
             } else if (choice == 3) {
                 System.out.println("To be implemented!");
                 continue;
             } else if (choice == 4) {
                 break;
             }
        }
    }
}
