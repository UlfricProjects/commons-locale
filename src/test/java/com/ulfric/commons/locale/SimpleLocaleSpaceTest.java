package com.ulfric.commons.locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

@RunWith(JUnitPlatform.class)
class SimpleLocaleSpaceTest {

	private LocaleSpace space;

	@BeforeEach
	void init()
	{
		this.space = SimpleLocaleSpace.newInstance();
	}

	@Test
	@DisplayName("newInstance is not null")
	void testNewInstanceNotNull()
	{
		Verify.that(this.space).isNotNull();
	}

	@Test
	@DisplayName("newInstance is unique")
	void testNewInstanceIsUnique()
	{
		Verify.that(this.space).isNotSameAs(SimpleLocaleSpace.newInstance());
	}

	@Test
	@DisplayName("Install works")
	void testInstallEmptyLocale()
	{
		this.space.install(Locale.builder().setCode("").build());

		Verify.that(this.space.hasLocale("")).isTrue();
	}

}