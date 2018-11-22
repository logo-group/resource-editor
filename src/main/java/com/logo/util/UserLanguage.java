package com.logo.util;

public enum UserLanguage {

	TRTR(LogoResConstants.RE_TURKISHTR_NAME), 
	ENUS(LogoResConstants.RE_ENGLISHUS_NAME), 
	DEDE(LogoResConstants.RE_GERMANDE_NAME), 
	FAIR(LogoResConstants.RE_PERSIANIR_NAME), 
	AZAZ(LogoResConstants.RE_AZERBAIJANIAZ_NAME), 
	RURU(LogoResConstants.RE_RUSSIANRU_NAME), 
	BGBG(LogoResConstants.RE_BULGARIANBG_NAME), 
	RORO(LogoResConstants.RE_ROMANIANRO_NAME), 
	KAGE(LogoResConstants.RE_GEORGIANGE_NAME), 
	ARJO(LogoResConstants.RE_ARABICJO_NAME), 
	FRFR(LogoResConstants.RE_FRENCHFR_NAME), 
	SQKV(LogoResConstants.RE_ALBANIANKV_NAME), 
	TKTM(LogoResConstants.RE_TURKMENTM_NAME), 
	AREG(LogoResConstants.RE_ARABICEG_NAME), 
	ARSA(LogoResConstants.RE_ARABICSA_NAME);

	private String name;

	private UserLanguage(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
