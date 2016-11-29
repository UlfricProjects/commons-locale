package com.ulfric.commons.locale;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Message {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder
	{
		Builder() { }

		private String code;
		private String singular;
		private String plural;

		public Message build()
		{
			Objects.requireNonNull(this.code);

			if (this.singular == null)
			{
				Objects.requireNonNull(this.plural);

				return new PluralMessage(this.code, this.plural, null);
			}

			return new SingularMessage(this.code, this.singular, this.plural);
		}

		public Builder setCode(String code)
		{
			Objects.requireNonNull(code);

			this.code = code;

			return this;
		}

		public Builder setSingular(String singular)
		{
			this.singular = singular;

			return this;
		}

		public Builder setPlural(String plural)
		{
			this.plural = plural;

			return this;
		}
	}

	protected Message(String code, String message)
	{
		this.code = code;
		this.message = message;
	}

	private final String code;
	private final String message;

	public final String getCode()
	{
		return this.code;
	}

	public final String getRawText()
	{
		return this.message;
	}

	public final String format(Object... objects)
	{
		Objects.requireNonNull(objects);

		int length = objects.length;
		if (length % 2 != 0)
		{
			throw new IllegalArgumentException("Format values must be equal amounts of key-value pairs");
		}

		String encoded = this.message;

		for (int x = 0; x < length; x += 2)
		{
			String key = '{' + String.valueOf(objects[x]) + '}';
			String value = String.valueOf(objects[x + 1]);

			encoded = encoded.replace(key, value);
		}

		return encoded;
	}

	public final String format(Map<String, Object> objects)
	{
		Objects.requireNonNull(objects);

		String formatted = this.message;

		for (Map.Entry<String, Object> entry : objects.entrySet())
		{
			String key = '{' + entry.getKey() + '}';
			String value = String.valueOf(entry.getValue());

			formatted = formatted.replace(key, value);
		}

		return formatted;
	}

	public Message singular()
	{
		return this;
	}

	public Message plural()
	{
		return this;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	private static final class SingularMessage extends Message
	{
		SingularMessage(String code, String message, String plural)
		{
			super(code, message);

			if (plural == null)
			{
				this.pluralMessage = this;
			}
			else
			{
				this.pluralMessage = new PluralMessage(code, plural, this);
			}
		}

		private final Message pluralMessage;

		@Override
		public Message plural()
		{
			return this.pluralMessage;
		}
	}

	private static final class PluralMessage extends Message
	{
		PluralMessage(String code, String message, Message singular)
		{
			super(code, message);

			this.singular = singular == null ? this : singular;
		}

		private final Message singular;

		@Override
		public Message singular()
		{
			return this.singular;
		}
	}

}