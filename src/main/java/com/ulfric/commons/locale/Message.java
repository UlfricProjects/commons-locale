package com.ulfric.commons.locale;

import java.util.Objects;

import com.ulfric.commons.object.ObjectUtils;

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
		return ObjectUtils.generateString(this);
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