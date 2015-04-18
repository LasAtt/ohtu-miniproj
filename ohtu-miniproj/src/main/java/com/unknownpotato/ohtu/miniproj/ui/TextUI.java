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
        Map<String, Runnable> choices = setUpChoices();
        while (true) {
            io.println("[A]dd new reference, [L]ist all references, [E]dit a reference, [D]elete a reference, e[X]port to BibTeX, [Q]uit");
            String choice = io.readCharacter(":");
            choice = choice.toLowerCase();

            if (choice.equals("q")) {
                return;
            }

            if (!choices.containsKey(choice)) {
                continue;
            }

            choices.get(choice).run();
        }
    }

    public void addReference() {
        while (true) {
            io.println(listTypeChoices());
            String choice = io.readCharacter(":");
            if (!StringUtils.isNumeric(choice)) {
                switch (choice) {
                    case ("q"):
                        return;
                    default:
                        io.println("Invalid choice");
                    case ("h"):
                        continue;    
                }
            }
            int i = Integer.parseInt(choice);
            if (i < 0 || i >= ReferenceType.values().length) {
                io.println("Invalid choice");
            }
                
            createNewReference(ReferenceType.values()[i]);
            io.println("You have added a new reference!");

            return;
        }
    }

    private String listTypeChoices() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Give reference type (");
        int i[] = {0};
        Arrays.asList(ReferenceType.values()).stream().forEach(t -> {
            sb.append(i[0]++).append("=").append(t.name().toLowerCase()).append(", ");
        });
        sb.append("h=help, q=quit)");
        return sb.toString();
    }

    private void listReferences() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
            return;
        }
        references.getReferences().stream().forEach(r -> {
            io.println("All references:");
            io.println(referenceToString(r));
        });
    }

    public String referenceToString(Reference r) {
        StringBuilder sb = new StringBuilder();
        sb.append(" - ").append(r.getName()).append(": { ");
        r.getFieldKeys().stream()
                .forEach(f -> sb.append(f)
                        .append(": ")
                        .append(r.getField(f))
                        .append(" "));
        return sb.append(" }").toString();
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
    
    public void editReference() {
        boolean fieldWasFound = false;
        String name = io.readLine("Name the reference to be edited:\n");
        if (!references.getReferences().contains(references.getReference(name))) {
            io.println("Reference " + name + " was not found!");
        } else {
            String fieldToEdit = io.readLine("Name the field to be edited:\n");
            for (String field : references.getReference(name).getFieldKeys()) {
                if (field.equals(fieldToEdit)) {
                    fieldWasFound = true;
                    String newFieldContent = io.readLine("Name the new content for this field:\n");
                    references.getReference(name).editField(field, newFieldContent);
                    io.println("The field " + fieldToEdit + " was edited!");
                }
            }
            if (fieldWasFound == false) {
                io.println("The field " + fieldToEdit + " was not found!");
            }
        }
    }

    public void exportToBibTex() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
            return;
        }

        try {
            FileWriterHandler writer = new FileWriterHandler("BibTex_export.bib");
            writer.writeTo(references.getReferences().stream()
                    .map(r -> BibtexFormatter.convertReference(r))
                    .collect(Collectors.toList()));
        } catch (IOException ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
            io.println("Export failed!");
            return;
        }
        io.println("Export complete!");
    }

    public void createNewReference(ReferenceType type) {
        Map<String, String> fields = new HashMap<>();

        io.println("Fill required fields");
        askForFields(Arrays.asList(type.getRequiredFields()), fields, false);

        if (getPermission("Fill optional fields? ([Y]es/[N]o):")) {
            io.println("Fill optional fields, press enter to leave field empty.");
            askForFields(Arrays.asList(type.getOptionalFields()), fields, true);
        }

        Reference ref = Reference.createReference(type, "", fields);
        references.addReference(ref);
    }

    private void askForFields(List<String> fieldKeys, Map<String, String> fields, boolean canLeaveEmpty) {
        fieldKeys.stream().forEach(f -> {
            String value = io.readLine(" " + f + ":");
            while ((canLeaveEmpty && value.isEmpty())
                    || (f.equals("year") && (!StringUtils.isNumeric(value) || value.length() < 2))) {
                value = io.readLine(" " + f + ":");
            }
            if (!value.isEmpty()) {
                fields.put(f, value);
            }
        });
    }

    private boolean getPermission(String prompt) {
        String choice = io.readCharacter(prompt);
        return choice.toLowerCase().trim().equals("y");
    }

    public List<Reference> filterByTag(List<Reference> refs, String tag) {
        return refs.stream()
                .filter(s -> s.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    private Map<String, Runnable> setUpChoices() {
        Map<String, Runnable> choices = new HashMap<>();
        choices.put("a", () -> addReference());
        choices.put("l", () -> listReferences());
        choices.put("e", () -> editReference());
        choices.put("d", () -> deleteReference());
        choices.put("x", () -> exportToBibTex());
        return choices;
    }

}
