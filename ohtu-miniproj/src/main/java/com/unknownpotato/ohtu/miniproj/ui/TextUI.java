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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.codehaus.plexus.util.StringUtils;
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
                choice = io.readInt("Type 1 to add a new reference, 2 to list all references, 3 to delete a reference, 4 to export to BibTex or 5 to quit\n");
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
                    deleteReference();
                    continue;
                case 4:
                    exportToBibTex();
                    continue;
                case 5:
                    break outerloop;
                default:
                    break;
            }
        }
    }

    public void addReference() {
        whileloop:
        while (true) {
            listTypeChoices();
            String choice = io.readCharacter(": ");
            if (!StringUtils.isNumeric(choice)) {
                switch (choice) {
                    case ("h"):
                        continue;
                    case ("q"):
                        return;
                }
            }
            
            askForFields(ReferenceType.values()[Integer.parseInt(choice)]);
            io.println("You have added a new reference!");
            return;
        }
    }

    private void listTypeChoices() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Give reference type (");
        int i[] = {0};
        Arrays.asList(ReferenceType.values()).stream().forEach(t -> {
            sb.append(i[0]++).append("=").append(t.name().toLowerCase()).append(", ");
        });
        sb.append("h=help, q=quit)");
        io.println(sb.toString());
    }

    private void listReferences() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
            return;
        }
        references.getReferences().stream().forEach(r -> {
            io.println(referenceToString(r));
        });
    }

    public String referenceToString(Reference r) {
        StringBuilder sb = new StringBuilder();
        sb.append("- name: ").append(r.getName()).append(" ");
        r.getFieldKeys().stream()
                .forEach(f -> sb.append(f)
                        .append(": ")
                        .append(r.getField(f))
                        .append(" "));
        return sb.toString();
    }

    public void deleteReference() {
        String name = io.readLine("Name the reference to be deleted:\n");
        if (!references.getReferences().contains(references.getReference(name))) {
            io.println("Reference " + name + " was not found!");
        } else {
            references.deleteReference(name);
            io.println("Reference " + name + " was deleted!");
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
                io.println("Export failed!");
                return;
            }
            io.println("Export complete!");
        }
    }

    public void askForFields(ReferenceType type) {
        Map<String, String> fields = new HashMap<>();
        for (String field : type.getRequiredFields()) {
            String fieldContent = io.readLine(field + ":\n");
            while (field.equals("year") && fieldContent.length() < 2) {
                fieldContent = io.readLine(field + ":\n");
            }
            fields.put(field, fieldContent);

        }

        for (String field : type.getOptionalFields()) {
            String fieldContent = io.readLine(field + ":\n");
            fields.put(field, fieldContent);
        }

        Reference ref = Reference.createReference(type, "", fields);
        references.addReference(ref);
    }

    public List<Reference> filterByTag(List<Reference> refs, String tag) {
        return refs.stream()
                .filter(s -> s.getTags().contains(tag))
                .collect(Collectors.toList());
    }
}
