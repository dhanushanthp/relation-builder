package org.core.process;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.pick.google.GoogleRetrive;
import org.text.extractor.WikiRelationBuilder;

public class Core {
	public static void main(String[] args) {
		String input = "american state";
		Set<String> print = new HashSet<String>();
		try {
			String URL = new GoogleRetrive().getRelatedConURL(input);
			if (URL.contains("http://en.wikipedia.org/")) {
				Collection<String> relations = new WikiRelationBuilder().getRelation(URL);
				for (String relation : relations) {
					if (Collections.frequency(relations, relation) > 1) {
						print.add(relation);
					}
				}

				for (String string : print) {
					System.out.println(string);
				}
				System.out.println(URL);
			} else {
				System.out.println("The result is not from wikipedia...");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
