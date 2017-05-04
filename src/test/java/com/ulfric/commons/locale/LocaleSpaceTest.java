package com.ulfric.commons.locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class LocaleSpaceTest {

	private LocaleSpace space;

	@BeforeEach
	void init()
	{
		this.space = new LocaleSpace();
	}

	@Test
	void testInstallEmptyLocale()
	{
		this.installEmptyLocale();
		Verify.that(this.space.hasLocale("")).isTrue();
	}

	@Test
	void testInstallEmptyLocaleWhenLocaleIsAlreadyPresent()
	{
		this.installEmptyLocale();
		this.installEmptyLocale();
		Verify.that(this.space.hasLocale("")).isTrue();
	}

	@Test
	void testGetLocaleFromNull()
	{
		Verify.that(() -> this.space.getLocale(null)).doesThrow(NullPointerException.class);
	}

	@Test
	void testGetLocale()
	{
		this.installEmptyLocale();
		Verify.that(this.space.getLocale("")).isPresent();
	}

	private void installEmptyLocale()
	{
		this.space.install(Locale.builder().setCode("").build());
	}

}