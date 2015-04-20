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
import com.unknownpotato.ohtu.miniproj.io.JSONReader;
import com.unknownpotato.ohtu.miniproj.io.JSONWriter;
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
 * Terminal User Interface for the software.
 */
@Component
public class TextUI {

    /**
     * Handles reading input and writing output for the class.
     */
    private IO io;
    /**
     * References handles the references stored in memory.
     */
    private References references;
    private final String DEFAULT_FILENAME = "references.json";

    /**
     * 
     * @param references References -class that references created are stored in.
     * @param io Input and output class.
     */
    @Autowired
    public TextUI(References references, IO io) {
        this.references = references;
        this.io = io;
    }

    /**
     * Start the reference handler UI.
     */
    public void run() {
        Map<String, Runnable> choices = setUpChoices();
        while (true) {
            io.println("[A]dd new reference\n"
                    + "[L]ist all references\n"
                    + "[E]dit a reference\n"
                    + "[D]elete a reference\n"
                    + "e[X]port to BibTeX\n"
                    + "[S]ave references JSON file\n"
                    + "[O]pen references JSON file\n"
                    + "[Q]uit");
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

    /**
     * Reads reference information from the user using IO and adds a new reference to IO.
     */
    private void addReference() {
        while (true) {
            io.println(listReferenceCreationChoices());
            String choice = io.readCharacter(":");
            if (!StringUtils.isNumeric(choice)) {
                switch (choice) {
                    case ("q"):
                        return;
                    default:
                        io.println("Invalid choice");
                }
            }
            int i = Integer.parseInt(choice);
            if (i < 0 || i >= ReferenceType.values().length) {
                io.println("Invalid choice");
                continue;
            }

            createNewReference(ReferenceType.values()[i]);
            io.println("You have added a new reference!");

            return;
        }
    }

    /**
     * Lists all choices on creating a new Reference.
     * @return choices as string.
     */
    private String listReferenceCreationChoices() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Give reference type (");
        int i[] = {0};
        Arrays.asList(ReferenceType.values()).stream().forEach(t -> {
            sb.append(i[0]++).append("=").append(t.name().toLowerCase()).append(", ");
        });
        sb.append("q=quit)");
        return sb.toString();
    }

    /**
     * Prints all references stored in references-object.
     */
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

    /**
     * Reads Reference information to String and returns it.
     * @param r reference to be converted
     * @return reference given as String representation.
     */
    private String referenceToString(Reference r) {
        StringBuilder sb = new StringBuilder();
        sb.append(" - ").append(r.getName()).append(": { ");
        r.getFieldKeys().stream()
                .forEach(f -> sb.append(f)
                        .append(": ")
                        .append(r.getField(f))
                        .append(" "));
        return sb.append(" }").toString();
    }

    /**
     * Deletes a Reference from references based input read from IO.
     */
    private void deleteReference() {
        String name = io.readLine("Name the reference to be deleted:\n");
        if (!references.getReferences().contains(references.getReference(name))) {
            io.println("Reference " + name + " was not found!");
        } else {
            references.deleteReference(name);
            io.println("Reference " + name + " was deleted!");
        }
    }
<<<<<<< HEAD
    
    /**
     * Edits a field in References.
     */
    private void editReference() {
=======

    public void editReference() {
>>>>>>> 42a8d5c3a3f6ba749d4b75ca57c1dd92413a8980
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

    /**
     * Exports references to running directory file BibTex_export.bib
     */
    private void exportToBibTex() {
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

    /**
     * Creates a new Reference of Type type
     * @param type type of reference created
     */
    private void createNewReference(ReferenceType type) {
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

    /**
     * Asks for values to fields given in List fieldKeys.
     * @param fieldKeys keys that values are asked for
     * @param fields where the value-key pairs are stored
     * @param canLeaveEmpty whether it's okay to leave a field empty
     */
    private void askForFields(List<String> fieldKeys, Map<String, String> fields, boolean canLeaveEmpty) {
        fieldKeys.stream().forEach(f -> {
            String value = io.readLine(" " + f + ":");
            while ((!canLeaveEmpty && value.isEmpty())
                    || (f.equals("year") && (!StringUtils.isNumeric(value) || value.length() < 2))) {
                value = io.readLine(" " + f + ":");
            }
            if (!value.isEmpty()) {
                fields.put(f, value);
            }
        });
    }

    /**
     * Prompt that asks the user a simple yes/no question and returns the value as boolean.
     * @param prompt question to ask the user
     * @return true if answer equals "y", false otherwise
     */
    private boolean getPermission(String prompt) {
        String choice = io.readCharacter(prompt);
        return choice.toLowerCase().trim().equals("y");
    }

    /**
     * Filters references by tag
     * @param refs List of references to be filtered
     * @param tag Tag to filter by
     * @return Filtered list.
     */
    private List<Reference> filterByTag(List<Reference> refs, String tag) {
        return refs.stream()
                .filter(s -> s.getTags().contains(tag))
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
    /**
     * Sets up the user interface commands.
     * @return hashmap with the choices.
     */
=======
    public void loadReferences() {
        String filename = io.readLine("filename [" + DEFAULT_FILENAME + "]:");
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
        }
        references = JSONReader.loadReferences(filename);
        if (references.getReferences().isEmpty()) {
            io.println("No references loaded!");
        } else {
            io.println("References loaded successfully!");
        }
    }

    public void saveReferences() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
            return;
        }
        String filename = io.readLine("filename [" + DEFAULT_FILENAME + "]:");
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
        }
        JSONWriter.saveReferences(references, filename);
        io.println("References saved successfully!");
    }

>>>>>>> 42a8d5c3a3f6ba749d4b75ca57c1dd92413a8980
    private Map<String, Runnable> setUpChoices() {
        Map<String, Runnable> choices = new HashMap<>();
        choices.put("a", () -> addReference());
        choices.put("l", () -> listReferences());
        choices.put("e", () -> editReference());
        choices.put("d", () -> deleteReference());
        choices.put("x", () -> exportToBibTex());
        choices.put("s", () -> saveReferences());
        choices.put("o", () -> loadReferences());
        return choices;
    }

}
