package com.ulfric.commons.locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

class SimpleLocaleSpaceTest {

	@Test
	@DisplayName("newInstance is not null")
	void testNewInstanceNotNull()
	{
		Truth.assertThat(SimpleLocaleSpace.newInstance()).isNotNull();
	}

	@Test
	@DisplayName("newInstance is unique")
	void testNewInstanceIsUnique()
	{
		Truth.assertThat(SimpleLocaleSpace.newInstance()).isNotSameAs(SimpleLocaleSpace.newInstance());
	}

	@Test
	@DisplayName("Install works")
	void testInstallEmptyLocale()
	{
		LocaleSpace space = SimpleLocaleSpace.newInstance();

		space.install(Locale.builder().setCode("").build());

		Truth.assertThat(space.hasLocale("")).isTrue();
	}

}