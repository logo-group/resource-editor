package com.logo.util;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class TranslatorService {

	public String translate2(String word) throws Exception {
		String translatedText = Translate.execute(word, Language.FRENCH, Language.ENGLISH);
		return translatedText;
	}
}
