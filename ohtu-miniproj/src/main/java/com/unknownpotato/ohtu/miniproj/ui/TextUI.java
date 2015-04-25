
package com.unknownpotato.ohtu.miniproj.ui;

import com.unknownpotato.ohtu.miniproj.domain.FieldValidator;
import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.BibtexFormatter;
import com.unknownpotato.ohtu.miniproj.io.FileWriterHandler;
import com.unknownpotato.ohtu.miniproj.io.IO;
import com.unknownpotato.ohtu.miniproj.io.JSONReader;
import com.unknownpotato.ohtu.miniproj.io.JSONWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.codehaus.plexus.util.StringUtils;
import org.json.JSONException;
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
    private final IO io;
    /**
     * References handles the references stored in memory.
     */
    private References references;
    private final String DEFAULT_FILENAME = "references.json";

    /**
     *
     * @param references References -class that references created are stored
     * in.
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
        io.println("Welcome to BibTeX-reference formatter!");
        while (true) {
            io.println("Input help to see commands");
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
     * Sets up the user interface commands.
     *
     * @return hashmap with the choices.
     */
    private Map<String, Runnable> setUpChoices() {
        final Map<String, Runnable> choices = new HashMap<>();
        choices.put("h", () -> listCommands());
        choices.put("a", () -> addReference());
        choices.put("l", () -> listReferences());
        choices.put("e", () -> editReference());
        choices.put("d", () -> deleteReference());
        choices.put("x", () -> exportToBibTex());
        choices.put("s", () -> saveReferences());
        choices.put("o", () -> loadReferences());
        return choices;
    }

    /**
     * Prints list of commands
     */
    private void listCommands() {
        io.println("[A]dd new reference,\n"
                + "[L]ist all references,\n"
                + "[E]dit a reference,\n"
                + "[D]elete a reference,\n"
                + "e[X]port to BibTeX,\n"
                + "[S]ave references JSON file,\n"
                + "[O]pen references JSON file,\n"
                + "[Q]uit\n");
    }

    /**
     * Reads reference information from the user using IO and adds a new
     * reference to IO.
     */
    private void addReference() {
        while (true) {
            io.println(listReferenceCreationChoices());
            String choice = io.readCharacter(":");
            if (!StringUtils.isNumeric(choice)) {
                if (choice.equals("q")) {
                    return;
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
     *
     * @return choices as string.
     */
    private String listReferenceCreationChoices() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Give reference type (");
        int i[] = {0};
        Arrays.asList(ReferenceType.values()).stream().forEach(t -> {
            sb.append(i[0]++).append("=")
                    .append(t.name().toLowerCase())
                    .append(", ");
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
     *
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
        if (references.deleteReference(name)) {
            io.println("Reference " + name + " was deleted!");
        } else {
            io.println("Reference " + name + " was not found!");
        }
    }

    private Reference findReference() {
        String name = io.readLine("Name the reference to be edited:\n");
        if (!references.contains(name)) {
            io.println("Reference " + name + " was not found!");
            return null;
        }
        return references.getReference(name);
    }

    private void editReference() {
        Reference ref = findReference();
        if (ref == null) {
            return;
        }
        
        Map<String, Consumer<Reference>> editChoices = setUpEditingChoices();

        io.println("[e]dit fields, [a]dd tags, [r]emove tags, [q]uit");
        String character = io.readCharacter("(" + ref.getName() + ") ");

        if (character.equals("q")) {
            return;
        }

        editChoices.get(character).accept(ref);
    }

    private Map<String, Consumer<Reference>> setUpEditingChoices() {
        final Map<String, Consumer<Reference>> editingChoices = new HashMap<>();
        editingChoices.put("e", param -> editFields(param));
        editingChoices.put("a", param -> addOrEditField(param));
        editingChoices.put("t", param -> addTags(param));
        editingChoices.put("r", param -> removeTags(param));
        return editingChoices;
    }

    private void editFields(Reference ref) {
        Map<String, String> fields = ref.getFields();

        io.println("Fill required fields");
        askForFields(Arrays.asList(ref.getType().getRequiredFields()), fields, false);

        if (getPermission("Fill optional fields? ([Y]es/[N]o):")) {
            io.println("Fill optional fields, press enter to leave field empty.");
            askForFields(Arrays.asList(ref.getType().getOptionalFields()), fields, true);
        }

        
        ref.editFields(fields);
    }

    private void addOrEditField(Reference ref) {
        String fieldToEdit = io.readLine("Name the field to be edited/added:\n");
        
        if (!ref.getFieldKeys().contains(fieldToEdit)) {
            return;
        }
        
        String newFieldContent = io.readLine(fieldToEdit + " [" + ref.getField(fieldToEdit) + "]:");
        ref.editField(fieldToEdit, newFieldContent);
        io.println("The field " + fieldToEdit + " was edited!");
    }
    

    /**
     * Exports references to running directory file BibTex_export.bib.
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
            io.println("Export failed!");
            return;
        }
        io.println("Export complete!");
    }

    /**
     * Creates a new Reference of Type type
     *
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
     *
     * @param fieldKeys keys that values are asked for
     * @param fields where the value-key pairs are stored
     * @param canLeaveEmpty whether it's okay to leave a field empty
     */
    private void askForFields(List<String> fieldKeys, Map<String, String> fields, boolean canLeaveEmpty) {
        fieldKeys.stream().forEach(f -> {
            String oldValue = fields.get(f) != null ? fields.get(f) : "";
            String value = io.readLine(f + " [" + oldValue + "]:");
            while ((!isFieldInputValid(f, canLeaveEmpty, value))) {
                value =  io.readLine(f + " [" + oldValue + "]:");
            }
            if (!value.isEmpty()) {
                fields.put(f, value);
            }
        });
    }

    /**
     * Add tags read from user to reference.
     *
     * @param ref reference to tag
     */
    private void addTags(Reference ref) {
        io.println("Input tags separated by spaces, leave empty to add no tags");
        ref.addTag(readTags());
    }

    /**
     * Removes tags from Reference
     *
     * @param ref reference to un-tag
     */
    private void removeTags(Reference ref) {
        io.println("Input tags separated by spaces, leave empty to remove no tags");
        ref.removeTag(readTags());
    }

    /**
     * Read tags to be added/removed from input.
     *
     * @return tags read from input
     */
    private String[] readTags() {
        String input = io.readLine(": ");
        return input.split(" ");
    }

    /**
     * Prompt that asks the user a simple yes/no question and returns the value
     * as boolean.
     *
     * @param prompt question to ask the user
     * @return true if answer equals "y", false otherwise
     */
    private boolean getPermission(String prompt) {
        String choice = io.readCharacter(prompt);
        return choice.toLowerCase().trim().equals("y");
    }

    /**
     *
     */
    private void loadReferences() {
        String filename = io.readLine("filename [" + DEFAULT_FILENAME + "]:");
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
            try {
                references = JSONReader.loadReferences(filename);
            } catch (FileNotFoundException ex) {
                io.println("File not found!");
            } catch (JSONException ex) {
                Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (references.getReferences().isEmpty()) {
                io.println("No references loaded!");
            }
        } else {
            io.println("References loaded successfully!");
        }
    }

    private void saveReferences() {
        if (references.getReferences().isEmpty()) {
            io.println("No references found!");
            return;
        }

        String filename = io.readLine("filename [" + DEFAULT_FILENAME + "]:");
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
        }
        try {
            JSONWriter.saveReferences(references, filename);
        } catch (IOException | JSONException ex) {
            Logger.getLogger(TextUI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        io.println("References saved successfully!");
    }

    private boolean isFieldInputValid(String field, boolean required, String input) {
        if (required && input.isEmpty()) {
            return false;
        }
        return FieldValidator.validate(field, input);
    }

}
