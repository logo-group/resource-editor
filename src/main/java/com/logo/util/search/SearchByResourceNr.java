package com.logo.util.search;

import org.apache.commons.lang.StringUtils;

import com.logo.ui.components.SpellChecComboBox;
import com.vaadin.ui.TextField;

public class SearchByResourceNr {

	private TextField resNrBegin;
	private TextField resNrEnd;
	private SpellChecComboBox<String> descCombo;
	private TextField descComboText;
	private SpellChecComboBox<String> resourceGroupCombo;
	private SpellChecComboBox<String> resourceTypeCombo;
	private SpellChecComboBox<String> resourceCaseCombo;
	private SpellChecComboBox<String> resourceStateCombo;

	private SearchParam scParam = new SearchParam();

	public static class Builder {

		private TextField resNrBegin;
		private TextField resNrEnd;
		private SpellChecComboBox<String> descCombo;
		private TextField descComboText;
		private SpellChecComboBox<String> resourceGroupCombo;
		private SpellChecComboBox<String> resourceTypeCombo;
		private SpellChecComboBox<String> resourceCaseCombo;
		private SpellChecComboBox<String> resourceStateCombo;

		public Builder setResNrBegin(TextField resNrBegin) {
			this.resNrBegin = resNrBegin;
			return this;
		}

		public Builder setResNrEnd(TextField resNrEnd) {
			this.resNrEnd = resNrEnd;
			return this;
		}

		public Builder setDescCombo(SpellChecComboBox<String> descCombo) {
			this.descCombo = descCombo;
			return this;
		}

		public Builder setDescComboText(TextField descComboText) {
			this.descComboText = descComboText;
			return this;
		}

		public Builder setResourceGroupCombo(SpellChecComboBox<String> resourceGroupCombo) {
			this.resourceGroupCombo = resourceGroupCombo;
			return this;
		}

		public Builder setResourceTypeCombo(SpellChecComboBox<String> resourceTypeCombo) {
			this.resourceTypeCombo = resourceTypeCombo;
			return this;
		}

		public Builder setResourceCaseCombo(SpellChecComboBox<String> resourceCaseCombo) {
			this.resourceCaseCombo = resourceCaseCombo;
			return this;
		}

		public Builder setResourceStateCombo(SpellChecComboBox<String> resourceStateCombo) {
			this.resourceStateCombo = resourceStateCombo;
			return this;
		}

		public SearchByResourceNr build() {
			SearchByResourceNr searchByResourceNr = new SearchByResourceNr();
			searchByResourceNr.resNrBegin = this.resNrBegin;
			searchByResourceNr.resNrEnd = this.resNrEnd;
			searchByResourceNr.descCombo = this.descCombo;
			searchByResourceNr.descComboText = this.descComboText;
			searchByResourceNr.resourceGroupCombo = this.resourceGroupCombo;
			searchByResourceNr.resourceTypeCombo = this.resourceTypeCombo;
			searchByResourceNr.resourceCaseCombo = this.resourceCaseCombo;
			searchByResourceNr.resourceStateCombo = this.resourceStateCombo;
			return searchByResourceNr;
		}
	}

	public void generateSearchParam() {
		if (StringUtils.isNotEmpty(resNrBegin.getValue()))
			scParam.setResNrBegin(Integer.parseInt(resNrBegin.getValue()));
		if (StringUtils.isNotEmpty(resNrEnd.getValue()))
			scParam.setResNrEnd(Integer.parseInt(resNrEnd.getValue()));
		if (descComboText != null && StringUtils.isNotEmpty(descComboText.getValue()))
			scParam.setWord(descComboText.getValue());

		if (descCombo != null)
			scParam.setDescFlag(descCombo.getSelectedItem());
		if (resourceCaseCombo != null)
			scParam.setResCaseFlag(resourceCaseCombo.getSelectedItem());
		if (resourceGroupCombo != null)
			scParam.setResGrpFlag(resourceGroupCombo.getSelectedItem());
		if (resourceStateCombo != null)
			scParam.setResStateFlag(resourceStateCombo.getSelectedItem());
		if (resourceTypeCombo != null)
			scParam.setResTypFlag(resourceTypeCombo.getSelectedItem());
	}

	public SearchParam getScParam() {
		return scParam;
	}

	public SearchByResourceNr() {
	}
}
