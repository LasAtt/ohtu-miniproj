/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.ui;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.BibtexFormatter;
import com.unknownpotato.ohtu.miniproj.io.FileWriterHandler;
import com.unknownpotato.ohtu.miniproj.io.IO;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
                choice = io.readInt("Type 1 to add a new reference, 2 to list all book type references, 3 to export to BibTex or 4 to quit\n");
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
        int typeChoice = 0;
        while (typeChoice < 1 || typeChoice > 2) {
            try {
                typeChoice = io.readInt("Type (1=book, 2=article):\n");
            } catch (Exception ex) {
            }
        }
        
        ReferenceType type = null;
        
        if (typeChoice == 1) {
            type = ReferenceType.BOOK;
            askForFields(type);
        } else if (typeChoice == 2) {
            type = ReferenceType.ARTICLE;
            askForFields(type);
        }
    }

    public void listReferences() {
        Set<String> referenceFields = null;
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
        } else {
            io.println("All references:");
            for (Reference reference : references.getReferences()) {
                referenceFields = reference.getFieldKeys();
                for (String field : referenceFields) {
                    io.print(field + ": " + reference.getField(field) + " ");
                }
                System.out.println("");
            }
        }
    }

    public void exportToBibTex() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
        } else {
            try {
                FileWriterHandler writer = new FileWriterHandler("BibTex_export.bib");
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
    
    public void askForFields(ReferenceType type) {
        Map<String, String> fields = new HashMap<String, String>();
        for (String field : type.getRequiredFields()) {
            String fieldContent = io.readLine(field + ":\n");
            fields.put(field, fieldContent);
            
        }
        
        for (String field : type.getOptionalFields()) {
            String fieldContent = io.readLine(field + ":\n");
            fields.put(field, fieldContent);
        }
//        String author = io.readLine("Author:\n");
//        String title = io.readLine("Title:\n");
//        String publisher = io.readLine("Publisher:\n");
//        String year = io.readLine("Year:\n");
//        fields.put("author", author);
//        fields.put("title", title);
//        fields.put("publisher", publisher);
//        fields.put("year", year);
        Reference ref = Reference.createReference(type, "", fields);
        references.addReference(ref);
        io.println("You have added a new reference!");
    }
}
