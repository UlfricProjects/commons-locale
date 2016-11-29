package com.ulfric.commons.locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.collect.ImmutableMap;
import com.google.common.truth.Truth;

@RunWith(JUnitPlatform.class)
final class MessageTest {

	private Message.Builder builder;

	@BeforeEach
	void init()
	{
		this.builder = Message.builder().setCode("");
	}

	@Test
	@DisplayName("Message.builder() is not null")
	void testBuilderIsNotNull()
	{
		Truth.assertThat(this.builder).isNotNull();
	}

	@Test
	@DisplayName("Message.builder() is unique")
	void testBuilderIsUnique()
	{
		Truth.assertThat(this.builder).isNotSameAs(Message.builder());
	}

	@Test
	@DisplayName("Message.builder().setCode(null) throws NullPointerException")
	void testBuilderNullCodeThrowsNullPointerException()
	{
		Assertions.expectThrows(NullPointerException.class, () -> this.builder.setCode(null));
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").build() throws NullPointerException")
	void testBuilderNoMessagesThrowsNullPointerException()
	{
		Assertions.expectThrows(NullPointerException.class, () -> this.builder.build());
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").setSingular(\"A\").build().getRawTest() equals \"A\"")
	void testBuilderSingularMessageANoPluralGetRawTextIsA()
	{
		Truth.assertThat(this.builder.setSingular("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").setPlural(\"A\").build().getRawTest() equals \"A\"")
	void testBuilderPluralMessageANoSingularGetRawTextIsA()
	{
		Truth.assertThat(this.builder.setPlural("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message with no plural returns itself when Message.plural() is called")
	void testBuilderSingularMessageANoPluralIdentityEqualsPlural()
	{
		Message message = this.builder.setSingular("A").build();

		Truth.assertThat(message.plural()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with no singular returns itself when Message.singular() is called")
	void testBuilderPluralMessageANoSingularIdentityEqualsSingular()
	{
		Message message = this.builder.setPlural("A").build();

		Truth.assertThat(message.singular()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with with singular and plural are unique")
	void testBuilderSingularAPluralBAreNotTheSame()
	{
		Message message = this.builder.setSingular("A").setPlural("B").build();

		Truth.assertThat(message.singular()).isNotSameAs(message.plural());
	}

	@Test
	@DisplayName("Message with with singular and plural have the correct values")
	void testSingularAPluralBAreCorrect()
	{
		Message message = this.builder.setSingular("A").setPlural("B").build();

		Truth.assertThat(message.singular().getRawText()).isEqualTo("A");
		Truth.assertThat(message.plural().getRawText()).isEqualTo("B");
	}

	@Test
	@DisplayName("Message.format(Object...) works")
	void testFormatVarargs()
	{
		Message message = this.builder.setSingular("Test: {a}").build();

		Truth.assertThat(message.singular().format("a", "A")).isEqualTo("Test: A");
	}

	@Test
	@DisplayName("Message.format(Map<String, Object>) works")
	void testFormatMap()
	{
		Message message = this.builder.setSingular("Test: {a}").build();

		Truth.assertThat(message.singular().format(ImmutableMap.of("a", "A"))).isEqualTo("Test: A");
	}

}