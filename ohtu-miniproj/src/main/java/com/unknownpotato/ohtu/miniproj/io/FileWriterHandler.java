package com.unknownpotato.ohtu.miniproj.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class FileWriterHandler {

    public File file;

    public FileWriterHandler(File file) throws IOException {
        this.file = file;
        this.file.delete();
        this.file.createNewFile();
    }

    public FileWriterHandler(String file) throws IOException {
        this(new File(file));
    }

    public void writeTo(String toWrite) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(toWrite);
        writer.close();
    }

    public void writeTo(List<String> toWrite) throws IOException {
        for (String line : toWrite) {
            writeTo(line);
            writeTo("\n");
        }
    }
}
