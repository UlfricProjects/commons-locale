package com.ulfric.commons.locale;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

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
		Verify.that(this.builder).isNotNull();
	}

	@Test
	@DisplayName("Message.builder() is unique")
	void testBuilderIsUnique()
	{
		Verify.that(this.builder).isNotSameAs(Message.builder());
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
		Verify.that(this.builder.setSingular("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message.builder().setCode(\"\").setPlural(\"A\").build().getRawTest() equals \"A\"")
	void testBuilderPluralMessageANoSingularGetRawTextIsA()
	{
		Verify.that(this.builder.setPlural("A").build().getRawText()).isEqualTo("A");
	}

	@Test
	@DisplayName("Message with no plural returns itself when Message.plural() is called")
	void testBuilderSingularMessageANoPluralIdentityEqualsPlural()
	{
		Message message = this.builder.setSingular("A").build();

		Verify.that(message.plural()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with no singular returns itself when Message.plural() is called")
	void testBuilderSingularMessageANoPluralIdentityEqualsSingularPlural()
	{
		Message message = this.builder.setPlural("A").build();

		Verify.that(message.plural()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with no singular returns itself when Message.singular() is called")
	void testBuilderPluralMessageANoSingularIdentityEqualsSingular()
	{
		Message message = this.builder.setPlural("A").build();

		Verify.that(message.singular()).isSameAs(message);
	}

	@Test
	@DisplayName("Message with with singular and plural are unique")
	void testBuilderSingularAPluralBAreNotTheSame()
	{
		Message message = this.builder.setSingular("A").setPlural("B").build();

		Verify.that(message.singular()).isNotSameAs(message.plural());
	}

	@Test
	@DisplayName("Message with with singular and plural have the correct values")
	void testSingularAPluralBAreCorrect()
	{
		Message message = this.builder.setSingular("A").setPlural("B").build();

		Verify.that(message.singular().getRawText()).isEqualTo("A");
		Verify.that(message.plural().getRawText()).isEqualTo("B");
	}

	@Test
	@DisplayName("Message.format(Object...) works")
	void testFormatVarargs()
	{
		Message message = this.builder.setSingular("Test: {a}").build();

		Verify.that(message.singular().format("a", "A")).isEqualTo("Test: A");
	}

	@Test
	@DisplayName("Message.format(Object...) works")
	void testFormatVarargsWithOddArguments()
	{
		Message message = this.builder.setSingular("Test: {a}").build();

		Verify.that(() -> message.format("a", "A", "odd one out")).doesThrow(
				IllegalArgumentException.class,
				"Format values must be equal amounts of key-value pairs");
	}

	@Test
	@DisplayName("Message.format(Map<String, Object>) works")
	void testFormatMap()
	{
		Message message = this.builder.setSingular("Test: {a}").build();

		Verify.that(message.singular().format(Collections.singletonMap("a", "A"))).isEqualTo("Test: A");
	}

	@Test
	@DisplayName("Message.singular().toString()")
	void testSingularToString()
	{
		Message message = this.builder.setSingular("A").build();

		Verify.that(message.singular().toString()).isEqualTo("SingularMessage[A]");
	}

	@Test
	@DisplayName("Message.plural().toString()")
	void testPluralToString()
	{
		Message message = this.builder.setPlural("A").build();

		Verify.that(message.plural().toString()).isEqualTo("PluralMessage[A]");
	}

}