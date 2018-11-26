package com.logo.util.search;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.logo.util.enums.ResourceCase;
import com.logo.util.enums.ResourceGroup;
import com.logo.util.enums.ResourceState;
import com.logo.util.enums.ResourceType;

public class SearchParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int resNrBegin = -999999;
	private int resNrEnd = 999999;
	private int descFlag = 3;
	private int resGrpFlag = -1;
	private int resTypFlag = -1;
	private int resCaseFlag = -1;
	private int resStateFlag = -1;
	private String word = "";

	private int orderNrBegin = -999999;
	private int orderNrEnd = 999999;
	private int tagNrBegin = -2147483648;
	private int tagNrEnd = 2147483647;
	private int levelNrBegin = 0;
	private int levelNrEnd = 999;
	private int resourceItemCaseFlag = -1;
	private int prefixFlag = 3;
	private int infoFlag = 3;
	private int descComboTRFlag = -1;
	private int descComboENFlag = -1;
	private String prefixComboText = "";
	private String infoComboText = "";
	private String comboTextTR = "";
	private String comboTextEN = "";

	private static final List<String> descList = Arrays.asList("Is empty", "Is not empty", "Is not empty or dash",
			"Contains", "Does not contain", "Begin with", "Does not begin with", "End with", "Does not end with",
			"Is equal to", "Is not equal to");

	private static final List<String> resourceGroupList = Arrays.asList(ResourceGroup.UN.toString(),
			ResourceGroup.HR.toString(), ResourceGroup.UNRP.toString(), ResourceGroup.HRRP.toString(),
			ResourceGroup.SS.toString(), ResourceGroup.HELP.toString(), ResourceGroup.MISC.toString());

	private static final List<String> resourceTypeList = Arrays.asList(ResourceType.LOCALIZABLE.toString(),
			ResourceType.NONLOCALIZABLE.toString());

	private static final List<String> resourceCaseList = Arrays.asList(ResourceCase.NORESTRICTION.toString(),
			ResourceCase.LOWERCASE.toString(), ResourceCase.UPPERCASE.toString(), ResourceCase.TITLECASE.toString(),
			ResourceCase.SENTENCECASE.toString());

	private static final List<String> resourceStateList = Arrays.asList(ResourceState.ACTIVE.toString(),
			ResourceState.INACTIVE.toString());

	public SearchParam() {
		/**
		 * */
	}

	public final void setResNrBegin(int resNrBegin) {
		this.resNrBegin = resNrBegin;
	}

	public final int getResNrBegin() {
		return resNrBegin;
	}

	public final int getResNrEnd() {
		return resNrEnd;
	}

	public final void setResNrEnd(int resNrEnd) {
		this.resNrEnd = resNrEnd;
	}

	public final int getDescFlag() {
		return descFlag;
	}

	public final void setDescFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (descList.contains(stringValue))
				this.descFlag = descList.indexOf(stringValue) + 1;
		}
	}

	public final int getResGrpFlag() {
		return resGrpFlag;
	}

	public final void setResGrpFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (resourceGroupList.contains(stringValue))
				this.resGrpFlag = resourceGroupList.indexOf(stringValue) + 1;
		}
	}

	public final int getResTypFlag() {
		return resTypFlag;
	}

	public final void setResTypFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (resourceTypeList.contains(stringValue))
				this.resTypFlag = resourceTypeList.indexOf(stringValue) + 1;
		}
	}

	public final int getResCaseFlag() {
		return resCaseFlag;
	}

	public final void setResCaseFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (resourceCaseList.contains(stringValue))
				this.resCaseFlag = resourceCaseList.indexOf(stringValue) + 1;
		}
	}

	public final int getResStateFlag() {
		return resStateFlag;
	}

	public final void setResStateFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (resourceStateList.contains(stringValue))
				this.resStateFlag = resourceStateList.indexOf(stringValue) + 1;
		}
	}

	public final String getWord() {
		return word;
	}

	public final void setWord(String word) {
		this.word = word;
	}

	public final int getOrderNrBegin() {
		return orderNrBegin;
	}

	public final void setOrderNrBegin(int orderNrBegin) {
		this.orderNrBegin = orderNrBegin;
	}

	public final int getOrderNrEnd() {
		return orderNrEnd;
	}

	public final void setOrderNrEnd(int orderNrEnd) {
		this.orderNrEnd = orderNrEnd;
	}

	public final int getTagNrBegin() {
		return tagNrBegin;
	}

	public final void setTagNrBegin(int tagNrBegin) {
		this.tagNrBegin = tagNrBegin;
	}

	public final int getTagNrEnd() {
		return tagNrEnd;
	}

	public final void setTagNrEnd(int tagNrEnd) {
		this.tagNrEnd = tagNrEnd;
	}

	public final int getLevelNrBegin() {
		return levelNrBegin;
	}

	public final void setLevelNrBegin(int levelNrBegin) {
		this.levelNrBegin = levelNrBegin;
	}

	public final int getLevelNrEnd() {
		return levelNrEnd;
	}

	public final void setLevelNrEnd(int levelNrEnd) {
		this.levelNrEnd = levelNrEnd;
	}

	public final int getPrefixFlag() {
		return prefixFlag;
	}

	public final void setPrefixFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (descList.contains(stringValue))
				this.prefixFlag = descList.indexOf(stringValue);
		}
	}

	public final int getInfoFlag() {
		return infoFlag;
	}

	public final void setInfoFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (descList.contains(stringValue))
				this.infoFlag = descList.indexOf(stringValue);
		}
	}

	public final int getDescComboTRFlag() {
		return descComboTRFlag;
	}

	public final void setDescComboTRFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (descList.contains(stringValue))
				this.descComboTRFlag = descList.indexOf(stringValue) + 1;
		}
	}

	public final int getDescComboENFlag() {
		return descComboENFlag;
	}

	public final void setDescComboENFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (descList.contains(stringValue))
				this.descComboENFlag = descList.indexOf(stringValue) + 1;
		}
	}

	public final String getPrefixComboText() {
		return prefixComboText;
	}

	public final void setPrefixComboText(String prefixComboText) {
		this.prefixComboText = prefixComboText;
	}

	public final String getInfoComboText() {
		return infoComboText;
	}

	public final void setInfoComboText(String infoComboText) {
		this.infoComboText = infoComboText;
	}

	public final String getComboTextTR() {
		return comboTextTR;
	}

	public final void setComboTextTR(String comboTextTR) {
		this.comboTextTR = comboTextTR;
	}

	public final String getComboTextEN() {
		return comboTextEN;
	}

	public final void setComboTextEN(String comboTextEN) {
		this.comboTextEN = comboTextEN;
	}

	public void setResourceItemCaseFlag(Optional<String> value) {
		if (value.isPresent()) {
			String stringValue = value.get();
			if (resourceCaseList.contains(stringValue))
				this.resourceItemCaseFlag = resourceCaseList.indexOf(stringValue) + 1;
		}
	}

	public int getResourceItemCaseFlag() {
		return resourceItemCaseFlag;
	}
}
