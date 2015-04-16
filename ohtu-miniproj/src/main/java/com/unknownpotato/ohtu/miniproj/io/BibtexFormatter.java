
package com.unknownpotato.ohtu.miniproj.io;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceType;

/**
 *
 * @author axwikstr, VoxWave
 */
public class BibtexFormatter {

	public static String convertReference(Reference ref) throws Exception {
		BibtexFormatter formatter = new BibtexFormatter();
		ReferenceType type = ref.getType();

		switch (type) {
		case BOOK:
			return formatter.formatBook(ref);
		case ARTICLE:
			return formatter.formatArticle(ref);
		}

		throw new Exception("Reference could not be formatted because the reference type is not supported");
	}

	private String formatArticle(Reference ref) {
		StringBuilder builder = new StringBuilder();
		builder.append("@Article{");
		builder.append(ref.getName());
		builder.append(",\n");
		return null;
	}

	private String formatBook(Reference ref) {
		StringBuilder builder = new StringBuilder();
		builder.append("@Book{");
		builder.append(ref.getName());
		builder.append(",\n");
		writeFields(ref, builder);
		return builder.toString();
	}

	private void writeFields(Reference ref, StringBuilder builder) {
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
	}
}
