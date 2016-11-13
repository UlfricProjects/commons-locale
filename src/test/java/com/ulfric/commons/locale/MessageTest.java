package com.ulfric.commons.locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

final class MessageTest {

	@Test
	@DisplayName("Message.builder() is not null")
	void testMessageBuilderIsNotNull()
	{
		Truth.assertThat(Message.builder()).isNotNull();
	}

	@Test
	@DisplayName("Message.builder() is unique")
	void testMessageBuilderIsUnique()
	{
		Truth.assertThat(Message.builder()).isNotSameAs(Message.builder());
	}

	@Test
	@DisplayName("Message.builder().setCode(null) throws NullPointerException")
	void testMessageBuilderNullCodeThrowsNullPointerException()
	{
		Assertions.expectThrows(NullPointerException.class, () -> Message.builder().setCode(null));
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").build() throws NullPointerException")
	void testMessageBuilderNoMessagesThrowsNullPointerException()
	{
		Assertions.expectThrows(NullPointerException.class, () -> Message.builder().setCode("").build());
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").setSingular(\"A\").build().getRawTest() equals \"A\"")
	void testMessageBuilderSingularMessageANoPluralGetRawTextIsA()
	{
		Truth.assertThat(Message.builder().setCode("").setSingular("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").setPlural(\"A\").build().getRawTest() equals \"A\"")
	void testMessageBuilderPluralMessageANoSingularGetRawTextIsA()
	{
		Truth.assertThat(Message.builder().setCode("").setPlural("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message with no plural returns itself when Message.plural() is called")
	void testMessageBuilderSingularMessageANoPluralIdentityEqualsPlural()
	{
		Message message = Message.builder().setCode("").setSingular("A").build();

		Truth.assertThat(message.plural()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with no singular returns itself when Message.singular() is called")
	void testMessageBuilderPluralMessageANoSingularIdentityEqualsSingular()
	{
		Message message = Message.builder().setCode("").setPlural("A").build();

		Truth.assertThat(message.singular()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with with singular and plural are unique")
	void testMessageBuilderSingularAPluralBAreNotTheSame()
	{
		Message message = Message.builder().setCode("").setSingular("A").setPlural("B").build();

		Truth.assertThat(message.singular()).isNotSameAs(message.plural());
	}

	@Test
	@DisplayName("Message with with singular and plural have the correct values")
	void testMessageSingularAPluralBAreCorrect()
	{
		Message message = Message.builder().setCode("").setSingular("A").setPlural("B").build();

		Truth.assertThat(message.singular().getRawText()).isEqualTo("A");
		Truth.assertThat(message.plural().getRawText()).isEqualTo("B");
	}

}