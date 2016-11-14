package com.ulfric.commons.locale;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.ulfric.commons.object.ObjectUtils;

public final class SimpleLocaleSpace implements LocaleSpace {

	public static SimpleLocaleSpace newInstance()
	{
		return new SimpleLocaleSpace();
	}

	private SimpleLocaleSpace() { }

	private final Map<String, Locale> locales = new HashMap<>();

	@Override
	public boolean hasLocale(String code)
	{
		Objects.requireNonNull(code);

		return this.locales.containsKey(code.toLowerCase());
	}

	@Override
	public Optional<Locale> getLocale(String code)
	{
		Objects.requireNonNull(code);

		return Optional.ofNullable(this.locales.get(code.toLowerCase()));
	}

	@Override
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

		if (!(object instanceof SimpleLocaleSpace))
		{
			return false;
		}

		SimpleLocaleSpace other = (SimpleLocaleSpace) object;

		return this.locales.equals(other.locales);
	}

	@Override
	public String toString()
	{
		return ObjectUtils.generateString(this);
	}

}