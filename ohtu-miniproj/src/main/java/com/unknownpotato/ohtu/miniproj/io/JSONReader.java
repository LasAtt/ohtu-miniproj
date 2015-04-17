/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;
import com.unknownpotato.ohtu.miniproj.domain.References;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author axwikstr
 */
public class JSONReader {
    public static References loadReferences(String filename) {
        References ref = new References();
        try {
            FileReader fr = new FileReader(filename);
            JSONObject jo = new JSONObject(fr.readFile());
            JSONArray ja = jo.getJSONArray("references");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject rjo = ja.getJSONObject(i);
                HashMap<String,String> fields = new HashMap<>();
                JSONObject fjo = rjo.getJSONObject("fields");
                Iterator it = fjo.keys();
                while(it.hasNext()){
                    String key = (String) it.next();
                    fields.put(key, fjo.getString(key));
                }
                Reference r = Reference.createReference(ReferenceType.valueOf(rjo.getString("type")), rjo.getString("name"), fields);
                ref.addReference(r);
            }
        } catch (FileNotFoundException | JSONException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ref;
    }
}
