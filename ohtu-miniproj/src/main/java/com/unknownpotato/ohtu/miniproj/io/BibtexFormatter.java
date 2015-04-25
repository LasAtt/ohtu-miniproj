package com.unknownpotato.ohtu.miniproj.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;

/**
 *
 * @author axwikstr, VoxWave
 */
public class BibtexFormatter {

	private static final Map<Character, String> umlauts = new HashMap<>();
	static {
		umlauts.put('ä', "{\\\"a}");
		umlauts.put('ö', "{\\\"o}");
		umlauts.put('å', "{\\aa}");
	}

	/**
	 * Converts a Reference to a String that is in Bibtex format
	 * 
	 * This method does not check the validity of the Reference.
	 * 
	 * @param ref
	 * @return String formatted in bibtex format
	 */

	public static String convertReference(Reference ref) {
		ReferenceType type = ref.getType();

		StringBuilder builder = new StringBuilder();
		builder.append("@");
		builder.append(type);
		builder.append("{");
		builder.append(ref.getName());
		builder.append(",\n");

		ref.getFieldKeys().stream().filter(f -> !ref.getField(f).isEmpty())
				.forEach(f -> {
					builder.append(f);
					builder.append(" = \"");
					builder.append(replaceUmlauts(ref.getField(f)));
					builder.append("\",\n");
				});

		builder.append("}\n");
		return builder.toString();
	}

	/**
	 * Replaces all umlauts with bibtex escapes
	 * 
	 * 
	 * @param origin The String to be converted
	 * @return a String with all the umlauts replaced with bibtex style escapes.
	 */

	public static String replaceUmlauts(String origin) {
		StringBuilder builder = new StringBuilder();
		for(char c: origin.toCharArray()) {
			builder.append(umlauts.getOrDefault(c, Character.toString(c)));
		}
		return builder.toString();
	}

	public static List<String> convertReferences(List<Reference> reflist) {
		return reflist.stream().map(r -> convertReference(r))
				.collect(Collectors.toList());
	}
	
}
