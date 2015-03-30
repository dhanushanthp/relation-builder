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
import org.pearson.domain.Domain;
import org.pick.google.GoogleRetrive;

public class WikiRelationBuilder {
	public Domain getRelations(String input, String decipline) throws IOException {

		String URL = new GoogleRetrive().getRelatedConURL(input, decipline, "wiki");
		if (URL.contains("http://en.wikipedia.org/")) {

			Domain relationObj = new Domain();
			Collection<String> textRelation = new ArrayList<String>();
			Set<String> titleRelation = new HashSet<String>();
			Set<String> processedResult = new HashSet<String>();

			Document doc;
			try {
				doc = Jsoup.connect(URL).get();
				relationObj.setTitle(doc.title().replace(" - Wikipedia, the free encyclopedia", ""));

				Elements links = doc.select("a[href]");

				for (Element link : links) {
					if (!link.attr("href").contains(":") && link.attr("href").startsWith("/wiki/")
							&& !link.attr("href").contains("/wiki/International_Standard_Book_Number")
							&& !link.attr("href").contains("/wiki/Digital_object_identifier")) {
						textRelation.add(link.text());
						titleRelation.add(link.attr("title"));
					}
				}

				Set<String> textArrToSet = new HashSet<String>(textRelation);

				// First Algorithm to remove uncommon phrase between title and
				// text.
				textArrToSet.retainAll(titleRelation);

				// Second Algorithm to remove count less than one.
				for (String text : textArrToSet) {
					int x = Collections.frequency(textRelation, text);
					if (x > 1) {
						processedResult.add(text);
					}
				}

				relationObj.setRelations(processedResult);

			} catch (IOException e) {
				System.out.println("Error At Get relation _--------------------------");
				e.printStackTrace();
			}

			relationObj.setTextCount(textRelation.size());
			relationObj.setTitleCount(titleRelation.size());
			relationObj.setResultCount(processedResult.size());
			return relationObj;
		} else {
			System.out.println("The result is not from wikipedia...");
			return null;
		}
	}
}