package org.pearson.domain;

import java.util.Collection;

public class Domain {
	private String title;
	private Collection<String> relations;
	private int textCount;
	private int titleCount;
	private int resultCount;

	public int getTextCount() {
		return textCount;
	}

	public void setTextCount(int textCount) {
		this.textCount = textCount;
	}

	public int getTitleCount() {
		return titleCount;
	}

	public void setTitleCount(int titleCount) {
		this.titleCount = titleCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<String> getRelations() {
		return relations;
	}

	public void setRelations(Collection<String> relations) {
		this.relations = relations;
	}
}
