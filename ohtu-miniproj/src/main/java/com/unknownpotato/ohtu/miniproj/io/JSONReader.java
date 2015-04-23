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

    public static References loadReferences(String filename) throws FileNotFoundException, JSONException {
        References references = new References();
            FileReader fr = new FileReader(filename);
            JSONObject jo = new JSONObject(fr.readFile());
            JSONArray referencesJA = jo.getJSONArray("references");
            populateWithReferences(referencesJA, references);
        return references;
    }

    private static void populateWithReferences(JSONArray ja, References ref) throws JSONException {
        for (int i = 0; i < ja.length(); i++) {
            JSONObject refrerenceJO = ja.getJSONObject(i);
            HashMap<String, String> fields = referenceFields(refrerenceJO);
            Reference r = Reference.createReference(ReferenceType.valueOf(refrerenceJO.getString("type")), refrerenceJO.getString("name"), fields);
            addTags(refrerenceJO, r);
            ref.addReference(r);
        }
    }

    private static void addTags(JSONObject refrerenceJO, Reference r) throws JSONException {
        JSONArray tja = refrerenceJO.getJSONArray("tags");
        for (int j = 0; j < tja.length(); j++) {
            r.addTag(tja.getString(j));
        }
    }

    private static HashMap<String, String> referenceFields(JSONObject refrerenceJO) throws JSONException {
        HashMap<String, String> fields = new HashMap<>();
        JSONObject fieldsJO = refrerenceJO.getJSONObject("fields");
        Iterator fieldsJOIterator = fieldsJO.keys();
        while (fieldsJOIterator.hasNext()) {
            String key = (String) fieldsJOIterator.next();
            fields.put(key, fieldsJO.getString(key));
        }
        return fields;
    }
}
