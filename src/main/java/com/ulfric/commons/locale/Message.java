package com.ulfric.commons.locale;

import java.util.Objects;

public final class Message {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Message>
	{
		private String code;
		private String text;

		Builder() { }

		@Override
		public Message build()
		{
			Objects.requireNonNull(this.code);
			Objects.requireNonNull(this.text);

			return new Message(this.code, this.text);
		}

		public Builder setCode(String code)
		{
			this.code = code;
			return this;
		}

		public Builder setText(String text)
		{
			this.text = text;
			return this;
		}
	}

	Message(String code, String message)
	{
		this.code = code;
		this.message = message;
	}

	private final String code;
	private final String message;

	public String getCode()
	{
		return this.code;
	}

	public String getText()
	{
		return this.message;
	}

}