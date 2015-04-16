/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author atte
 */
public class FileReader {
    
    private File file;
    
    public FileReader(File file) throws FileNotFoundException {
        this.file = file;
        if (!file.exists())
            throw new FileNotFoundException();
    }
    
    public FileReader(String file) throws FileNotFoundException {
        this(new File(file));
    }
    
    public String readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        
        while(scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }
        
        return sb.toString();
    }
}
