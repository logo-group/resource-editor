package com.logo.util.search;

import org.apache.commons.lang.StringUtils;

import com.logo.ui.components.SpellChecComboBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;

public class SearchByAll {

	private TextField orderNrBegin;
	private TextField orderNrEnd;
	private TextField tagNrBegin;
	private TextField tagNrEnd;
	private TextField levelNrBegin;
	private TextField levelNrEnd;
	private SpellChecComboBox<String> resourceItemCaseCombo;
	private SpellChecComboBox<String> prefixCombo;
	private SpellChecComboBox<String> infoCombo;
	private TextField prefixComboText;
	private TextField infoComboText;
	private SpellChecComboBox<String> descComboTR;
	private TextField comboTextTR;
	private SpellChecComboBox<String> descComboEN;
	private TextField comboTextEN;
	private SearchByResourceNr searchByResourceNr;

	public static class Builder {

		private TextField orderNrBegin;
		private TextField orderNrEnd;
		private TextField tagNrBegin;
		private TextField tagNrEnd;
		private TextField levelNrBegin;
		private TextField levelNrEnd;
		private SpellChecComboBox<String> resourceItemCaseCombo;
		private SpellChecComboBox<String> prefixCombo;
		private SpellChecComboBox<String> infoCombo;
		private TextField prefixComboText;
		private TextField infoComboText;
		private SpellChecComboBox<String> descComboTR;
		private TextField comboTextTR;
		private SpellChecComboBox<String> descComboEN;
		private TextField comboTextEN;
		private SearchByResourceNr searchByResourceNr;

		public Builder setOrderNrBegin(TextField orderNrBegin) {
			this.orderNrBegin = orderNrBegin;
			return this;
		}

		public Builder setOrderNrEnd(TextField orderNrEnd) {
			this.orderNrEnd = orderNrEnd;
			return this;
		}

		public Builder setTagNrBegin(TextField tagNrBegin) {
			this.tagNrBegin = tagNrBegin;
			return this;
		}

		public Builder setTagNrEnd(TextField tagNrEnd) {
			this.tagNrEnd = tagNrEnd;
			return this;
		}

		public Builder setLevelNrBegin(TextField levelNrBegin) {
			this.levelNrBegin = levelNrBegin;
			return this;
		}

		public Builder setLevelNrEnd(TextField levelNrEnd) {
			this.levelNrEnd = levelNrEnd;
			return this;
		}

		public Builder setResourceItemCaseCombo(SpellChecComboBox<String> resourceItemCaseCombo) {
			this.resourceItemCaseCombo = resourceItemCaseCombo;
			return this;
		}

		public Builder setPrefixCombo(SpellChecComboBox<String> prefixCombo) {
			this.prefixCombo = prefixCombo;
			return this;
		}

		public Builder setInfoCombo(SpellChecComboBox<String> infoCombo) {
			this.infoCombo = infoCombo;
			return this;
		}

		public Builder setPrefixComboText(TextField prefixComboText) {
			this.prefixComboText = prefixComboText;
			return this;
		}

		public Builder setInfoComboText(TextField infoComboText) {
			this.infoComboText = infoComboText;
			return this;
		}

		public Builder setDescComboTR(SpellChecComboBox<String> descComboTR) {
			this.descComboTR = descComboTR;
			return this;
		}

		public Builder setComboTextTR(TextField comboTextTR) {
			this.comboTextTR = comboTextTR;
			return this;
		}

		public Builder setDescComboEN(SpellChecComboBox<String> descComboEN) {
			this.descComboEN = descComboEN;
			return this;
		}

		public Builder setComboTextEN(TextField comboTextEN) {
			this.comboTextEN = comboTextEN;
			return this;
		}

		public Builder setSearchByResourceNr(SearchByResourceNr searchByResourceNr) {
			this.searchByResourceNr = searchByResourceNr;
			return this;
		}

		public SearchByAll build() {
			SearchByAll searchByAll = new SearchByAll();
			searchByAll.orderNrBegin = this.orderNrBegin;
			searchByAll.orderNrEnd = this.orderNrEnd;
			searchByAll.tagNrBegin = this.tagNrBegin;
			searchByAll.tagNrEnd = this.tagNrEnd;
			searchByAll.levelNrBegin = this.levelNrBegin;
			searchByAll.levelNrEnd = this.levelNrEnd;
			searchByAll.resourceItemCaseCombo = this.resourceItemCaseCombo;
			searchByAll.prefixCombo = this.prefixCombo;
			searchByAll.infoCombo = this.infoCombo;
			searchByAll.prefixComboText = this.prefixComboText;
			searchByAll.infoComboText = this.infoComboText;
			searchByAll.descComboTR = this.descComboTR;
			searchByAll.comboTextTR = this.comboTextTR;
			searchByAll.descComboEN = this.descComboEN;
			searchByAll.comboTextEN = this.comboTextEN;
			searchByAll.searchByResourceNr = this.searchByResourceNr;
			return searchByAll;
		}
	}

	public SearchByResourceNr getSearchByResourceNr() {
		return searchByResourceNr;
	}
	
	public void generateSearchParam() {
		searchByResourceNr.generateSearchParam();
		if (StringUtils.isNotEmpty(orderNrBegin.getValue()))
			searchByResourceNr.getScParam().setOrderNrBegin(Integer.parseInt(orderNrBegin.getValue()));
		if (StringUtils.isNotEmpty(orderNrEnd.getValue()))
			searchByResourceNr.getScParam().setOrderNrEnd(Integer.parseInt(orderNrEnd.getValue()));
		if (StringUtils.isNotEmpty(tagNrBegin.getValue()))
			searchByResourceNr.getScParam().setTagNrBegin(Integer.parseInt(tagNrBegin.getValue()));
		if (StringUtils.isNotEmpty(tagNrEnd.getValue()))
			searchByResourceNr.getScParam().setTagNrEnd(Integer.parseInt(tagNrEnd.getValue()));
		if (StringUtils.isNotEmpty(levelNrBegin.getValue()))
			searchByResourceNr.getScParam().setLevelNrBegin(Integer.parseInt(levelNrBegin.getValue()));
		if (StringUtils.isNotEmpty(levelNrEnd.getValue()))
			searchByResourceNr.getScParam().setLevelNrEnd(Integer.parseInt(levelNrEnd.getValue()));

		if(resourceItemCaseCombo != null)
			searchByResourceNr.getScParam().setResourceItemCaseFlag(resourceItemCaseCombo.getSelectedItem());
		if(prefixCombo != null)
			searchByResourceNr.getScParam().setPrefixFlag(prefixCombo.getSelectedItem());
		if(infoCombo != null)
			searchByResourceNr.getScParam().setInfoFlag(infoCombo.getSelectedItem());
		if(descComboTR != null)
			searchByResourceNr.getScParam().setDescComboTRFlag(descComboTR.getSelectedItem());
		if(descComboEN != null)
			searchByResourceNr.getScParam().setDescComboENFlag(descComboEN.getSelectedItem());

		if (prefixComboText != null && StringUtils.isNotEmpty(prefixComboText.getValue()))
			searchByResourceNr.getScParam().setPrefixComboText(prefixComboText.getValue());
		if (infoComboText != null &&StringUtils.isNotEmpty(infoComboText.getValue()))
			searchByResourceNr.getScParam().setInfoComboText(infoComboText.getValue());
		if (comboTextTR != null && StringUtils.isNotEmpty(comboTextTR.getValue()))
			searchByResourceNr.getScParam().setComboTextTR(comboTextTR.getValue());
		if (comboTextEN != null && StringUtils.isNotEmpty(comboTextEN.getValue()))
			searchByResourceNr.getScParam().setComboTextEN(comboTextEN.getValue());

	}

	public SearchByAll() {
		/**
		 * */
	}

}
