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
	public Collection<String> getRelation(String URL) {
		Collection<String> relations = new ArrayList<String>();
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			String title = doc.title();

			Elements links = doc.select("a[href]");
			for (Element link : links) {

				if (link.attr("href").startsWith("/wiki/")) {
					relations.add(link.text());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return relations;
	}
}