package org.text.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiRelationBuilder {
	public Collection<String> getRelationap1(String URL) {
		Collection<String> relations = new ArrayList<String>();
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			String title = doc.title();

			Elements links = doc.select("a[href]");
			for (Element link : links) {
				if (!link.attr("href").contains(":") && link.attr("href").startsWith("/wiki/")
						&& !link.attr("href").contains("/wiki/International_Standard_Book_Number")
						&& !link.attr("href").contains("/wiki/Digital_object_identifier")
						) {
//					System.out.println(link.attr("title"));
					relations.add(link.attr("title"));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return relations;
	}
	
	public Collection<String> getRelationap2(String URL) {
		Set<String> relationText = new HashSet<String>();
		Set<String> relationTitle = new HashSet<String>();
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			String title = doc.title();

			Elements links = doc.select("a[href]");
			
			for (Element link : links) {
				if (!link.attr("href").contains(":") && link.attr("href").startsWith("/wiki/")
						&& !link.attr("href").contains("/wiki/International_Standard_Book_Number")
						&& !link.attr("href").contains("/wiki/Digital_object_identifier")
						) {
					relationText.add(link.text());
					relationTitle.add(link.attr("title"));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(relationText.size());
		System.out.println(relationTitle.size());
		relationText.retainAll(relationTitle);
		System.out.println(relationText.size());
		return relationText;
	}
	
	
}