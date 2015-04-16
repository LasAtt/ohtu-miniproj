/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.References;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author axwikstr
 */
public class JSONWriter {

    public static void saveReferences(References ref, String filename) {
        try {
            FileWriterHandler w = new FileWriterHandler(filename);
            JSONObject jo = new JSONObject();
            JSONArray ja = new JSONArray();
            for (Reference r : ref.getReferences()) {
                JSONObject rjo = new JSONObject();
                rjo.put("name", r.getName());
                rjo.put("type", r.getType().toString());
                rjo.put("fields", r.getFields());
                ja.put(rjo);
            }
            jo.put("references", ja);
            w.writeTo(jo.toString(4));
        } catch (IOException | JSONException ex) {
            Logger.getLogger(JSONWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}