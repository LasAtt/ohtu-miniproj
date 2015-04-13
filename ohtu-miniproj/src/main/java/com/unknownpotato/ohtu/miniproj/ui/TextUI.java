/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.ui;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.BibtexFormatter;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;
import com.unknownpotato.ohtu.miniproj.io.FileWriterHandler;
import com.unknownpotato.ohtu.miniproj.io.IO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author axwikstr
 */
@Component
public class TextUI {

    private IO io;
    private References references;

    @Autowired
    public TextUI(References references, IO io) {
        this.references = references;
        this.io = io;
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
        io.println("You have added a new book type reference!");
    }

    public void listReferences() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
        } else {
            io.println("Book type references:");
            for (Reference reference : references.getReferences()) {
                io.println("Author: " + reference.getField("author") + " Title: " + reference.getField("title")
                        + " Publisher: " + reference.getField("publisher") + " Year: " + reference.getField("year"));
            }
        }
    }

    public void exportToBibTex() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
        } else {
            try {
                FileWriterHandler writer = new FileWriterHandler("BibTex_export.txt");
                for (Reference reference : references.getReferences()) {
                    writer.writeTo(BibtexFormatter.convertReference(reference));
                }
            } catch (IOException ex) {
                Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
                io.println("Export failed.");
                return;
            }
            io.println("Export complete!");
        }
    }
}
