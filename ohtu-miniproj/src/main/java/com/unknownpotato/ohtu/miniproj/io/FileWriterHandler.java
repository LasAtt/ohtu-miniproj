package com.unknownpotato.ohtu.miniproj.io;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * 
 */
public class FileWriterHandler {
   
    public File file;

    public FileWriterHandler(String file) {
        try {
            this.file = new File(file);
            this.file.createNewFile();         
        } catch(Exception e) {
            System.err.println("An Exception was caught :" + e.getMessage());
        }
    }

    public void writeTo(String toWrite) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(toWrite);
            writer.close();
        } catch(Exception e) {
            System.err.println("An IOException was caught :" + e.getMessage());
        }
    }

    public void writeTo(List<String> toWrite) {
        for (String line : toWrite) {
            writeTo(line);
        }
    }
}
