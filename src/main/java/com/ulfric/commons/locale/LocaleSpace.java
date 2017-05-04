package com.ulfric.commons.locale;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.ulfric.commons.bean.Bean;

public class LocaleSpace extends Bean {

	private final Map<String, Locale> locales = new HashMap<>();

	public boolean hasLocale(String code)
	{
		Objects.requireNonNull(code);

		return this.locales.containsKey(code.toLowerCase());
	}

	public Optional<Locale> getLocale(String code)
	{
		Objects.requireNonNull(code);

		return Optional.ofNullable(this.locales.get(code.toLowerCase()));
	}

	public Locale install(Locale locale)
	{
		Objects.requireNonNull(locale);

		String code = locale.getCode().toLowerCase();

		Locale current = this.locales.get(code);

		if (current == null)
		{
			this.locales.put(code, locale);

			return locale;
		}

		Locale mixed = Locale.builder().setCode(code).addMessages(current).addMessages(locale).build();

		this.locales.put(code, mixed);

		return mixed;
	}

}