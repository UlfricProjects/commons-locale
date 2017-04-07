package com.ulfric.commons.locale;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
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
	void testBuilderNotNull()
	{
		Verify.that(this.builder).isNotNull();
	}

	@Test
	void testBuilderIsUnique()
	{
		Verify.that(this.builder).isNotSameAs(Locale.builder());
	}

	@Test
	void testPopulated()
	{
		Verify.that(this.buildLocale(1).getMessage("0")).isNotNull();
	}

	@Test
	void testAddMessages()
	{
		Message message = Message.builder().setCode("hello").setText("Hello, world!").build();
		Locale locale = Locale.builder().setCode("en_US").addMessages(Arrays.asList(message, null)).build();
		List<Message> messages = StreamSupport.stream(locale.spliterator(), false).collect(Collectors.toList());
		Verify.that(messages).contains(message);
	}

	private Locale buildLocale(int messageCount)
	{
		this.builder.setCode(UUID.randomUUID().toString());
		IntStream.range(0, messageCount).mapToObj(String::valueOf).forEach(this::add);
		return this.builder.build();
	}

	private void add(String message)
	{
		this.builder.addMessage(Message.builder()
				.setCode(message)
				.setText(message)
				.build());
	}

}