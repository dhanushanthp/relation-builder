package org.core.main;

import java.io.IOException;
import org.pearson.domain.Domain;
import org.text.extractor.WikiRelationBuilder;

public class Core {

	public static void approachTwo() {
		WikiRelationBuilder w = new WikiRelationBuilder();
		try {
			Domain ss = w.getRelations("Capacity", "Computer");
			System.out.println(ss.getTitle());
			System.out.println(ss.getTextCount());
			System.out.println(ss.getTitleCount());
			System.out.println(ss.getResultCount());
			System.out.println(ss.getRelations());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		approachTwo();
	}
}
