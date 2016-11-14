package com.ulfric.commons.locale;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;

final class LocaleTest {

	@Test
	@DisplayName("Locale.builder() is not null")
	void testBuilderNotNull()
	{
		Truth.assertThat(Locale.builder()).isNotNull();
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testBuilderIsUnique()
	{
		Truth.assertThat(Locale.builder()).isNotSameAs(Locale.builder());
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testPopulated()
	{
		Truth8.assertThat(this.buildLocale(1).getMessage("0")).isPresent();
	}

	private Locale buildLocale(int messageCount)
	{
		Locale.Builder builder = Locale.builder();

		builder.setCode(UUID.randomUUID().toString());

		for (int x = 0; x < messageCount; x++)
		{
			String m = String.valueOf(x);
			builder.addMessage(Message.builder().setCode(m).setSingular(m).setPlural(m).build());
		}

		return builder.build();
	}

}