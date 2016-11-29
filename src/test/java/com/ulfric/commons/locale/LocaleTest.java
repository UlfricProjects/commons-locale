package com.ulfric.commons.locale;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;

@RunWith(JUnitPlatform.class)
final class LocaleTest {

	private Locale.Builder builder;

	@BeforeEach
	void init()
	{
		this.builder = Locale.builder();
	}

	@Test
	@DisplayName("Locale.builder() is not null")
	void testBuilderNotNull()
	{
		Truth.assertThat(this.builder).isNotNull();
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testBuilderIsUnique()
	{
		Truth.assertThat(this.builder).isNotSameAs(Locale.builder());
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testPopulated()
	{
		Truth8.assertThat(this.buildLocale(1).getMessage("0")).isPresent();
	}

	private Locale buildLocale(int messageCount)
	{
		this.builder.setCode(UUID.randomUUID().toString());

		for (int x = 0; x < messageCount; x++)
		{
			String m = String.valueOf(x);
			this.builder.addMessage(Message.builder().setCode(m).setSingular(m).setPlural(m).build());
		}

		return this.builder.build();
	}

}