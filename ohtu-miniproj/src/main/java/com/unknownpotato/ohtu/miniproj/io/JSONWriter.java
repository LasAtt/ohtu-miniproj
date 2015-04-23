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

    public static void saveReferences(References ref, String filename) throws IOException, JSONException {
            FileWriterHandler fw = new FileWriterHandler(filename);
            JSONObject jo = new JSONObject();
            JSONArray referencesJA = new JSONArray();
            for (Reference r : ref.getReferences()) {
                JSONObject referenceJO = new JSONObject();
                referenceJO.put("name", r.getName());
                referenceJO.put("type", r.getType().name());
                referenceJO.put("fields", r.getFields());
                JSONArray tja = new JSONArray(r.getTags());
                referenceJO.put("tags", tja);
                referencesJA.put(referenceJO);
            }
            jo.put("references", referencesJA);
            fw.writeTo(jo.toString(4));

    }
}
