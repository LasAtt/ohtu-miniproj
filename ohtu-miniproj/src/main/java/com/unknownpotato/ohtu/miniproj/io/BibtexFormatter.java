
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;

/**
 *
 * @author axwikstr, VoxWave
 */
public class BibtexFormatter {

	public static String convertReference(Reference ref) {
		ReferenceType type = ref.getType();
		
		StringBuilder builder = new StringBuilder();
		builder.append("@");
		builder.append(type);
		builder.append("{");
		builder.append(ref.getName());
		builder.append(",\n");
		
		String value = "";
		for (String field : ref.getFieldKeys()) {
			value = ref.getField(field);
			if (!value.isEmpty()) {
				builder.append(field);
				builder.append(" = \"");
				builder.append(value);
				builder.append("\",\n");
			}
		}
		builder.append("}\n");
		return builder.toString();
	}
}
