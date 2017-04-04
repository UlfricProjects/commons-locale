package com.ulfric.commons.locale;

import org.junit.jupiter.api.BeforeEach;
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
	void testBuilderIsNotNull()
	{
		Verify.that(this.builder).isNotNull();
	}

	@Test
	void testBuilderIsUnique()
	{
		Verify.that(this.builder).isNotSameAs(Message.builder());
	}

	@Test
	void testBuilderNullCodeThrowsNullPointerException()
	{
		Verify.that(() -> this.builder.setCode(null).build()).doesThrow(NullPointerException.class);
	}

	@Test
	void testBuilderNullTextThrowsNullPointerException()
	{
		Verify.that(() -> this.builder.setText(null).build()).doesThrow(NullPointerException.class);
	}

	@Test
	void testBuilderNoMessagesThrowsNullPointerException()
	{
		Verify.that(this.builder::build).doesThrow(NullPointerException.class);
	}

	@Test
	void testBuilderSingularMessageANoPluralGetRawTextIsA()
	{
		Verify.that(this.builder.setText("A").build().getText()).isEqualTo("A");
	}

}