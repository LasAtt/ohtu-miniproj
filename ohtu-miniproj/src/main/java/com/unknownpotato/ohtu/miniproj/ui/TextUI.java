/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.ui;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;

/**
 *
 * @author axwikstr
 */
public class TextUI {

    private ConsoleIO io = new ConsoleIO();
    private References references;

    public TextUI(References references) {
        this.references = references;
    }

    public void run() {
        int choice = 0;
        outerloop:
        while (true) {
            try {
                choice = io.readInt("Type 1 to add a new book type reference, 2 to list all book type references, 3 to export to BibTex or 4 to quit\n");
            } catch (Exception ex) {
                continue;
            }
            switch (choice) {
                case 1:
                    addReference();
                    continue;
                case 2:
                    listReferences();
                    continue;
                case 3:
                    exportToBibTex();
                    continue;
                case 4:
                    break outerloop;
                default:
                    break;

            }
        }
    }

    public void addReference() {
        String author = io.readLine("Author:\n");
        String title = io.readLine("Title:\n");
        String publisher = io.readLine("Publisher:\n");
        String year = io.readLine("Year:\n");
        Reference ref = ReferenceFactory.createBookReference(author, title, publisher, year);
        io.print("You have added a new book type reference with the following information:");
        io.print("Author: " + ref.getField("author") + " Title: " + ref.getField("title")
                + " Publisher: " + ref.getField("publisher") + " Year: " + ref.getField("year"));
    }

    public void listReferences() {
        for (Reference reference : references.getReferences()) {
            io.print("Author: " + reference.getField("author") + " Title: " + reference.getField("title")
                    + " Publisher: " + reference.getField("publisher") + " Year: " + reference.getField("year"));
        }
    }

    public void exportToBibTex() {
        io.print("To be implemented!");
    }
}
