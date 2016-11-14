package com.ulfric.commons.locale;

import java.util.Optional;

public interface LocaleSpace {

	boolean hasLocale(String code);

	Optional<Locale> getLocale(String code);

	Locale install(Locale locale);

}