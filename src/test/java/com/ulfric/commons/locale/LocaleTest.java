package com.ulfric.commons.locale;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.ulfric.verify.Verify;

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
		Verify.that(this.builder).isNotNull();
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testBuilderIsUnique()
	{
		Verify.that(this.builder).isNotSameAs(Locale.builder());
	}

	@Test
	@DisplayName("Locale.builder() is unique")
	void testPopulated()
	{
		Verify.that(this.buildLocale(1).getMessage("0")).isPresent();
	}

	@Test
	@DisplayName("Locale.builder().addMessages(Iterable) works")
	void testAddMessages()
	{
		Message message = Message.builder().setCode("hello").setSingular("Hello, world!").build();
		Locale locale = Locale.builder().setCode("en_US").addMessages(Arrays.asList(message, null)).build();
		List<Message> messages = StreamSupport.stream(locale.spliterator(), false).collect(Collectors.toList());
		Verify.that(messages).contains(message);
	}

	@Test
	void testToString()
	{
		Locale locale = this.buildLocale(5);
		String expected = locale.getClass().getCanonicalName() + "@" +
				Integer.toHexString(System.identityHashCode(locale)) + "[code=" + locale.getCode() +
				",messages={0=SingularMessage[0], 1=SingularMessage[1], 2=SingularMessage[2], " +
				"3=SingularMessage[3], 4=SingularMessage[4]}]";
		Verify.that(locale.toString()).isEqualTo(expected);
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