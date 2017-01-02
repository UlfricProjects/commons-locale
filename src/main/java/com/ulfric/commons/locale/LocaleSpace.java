package com.ulfric.commons.locale;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;

public final class LocaleSpace {

	public static LocaleSpace newInstance()
	{
		return new LocaleSpace();
	}

	private LocaleSpace() { }

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

		Locale mixed = Locale.builder().setCode(code).addMessages(current.getMessages()).addMessages(locale.getMessages()).build();

		this.locales.put(code, mixed);

		return mixed;
	}

	@Override
	public int hashCode()
	{
		return this.locales.hashCode();
	}

	@Override
	public boolean equals(Object object)
	{
		if (object == this)
		{
			return true;
		}

		if (!(object instanceof LocaleSpace))
		{
			return false;
		}

		LocaleSpace other = (LocaleSpace) object;

		return this.locales.equals(other.locales);
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}