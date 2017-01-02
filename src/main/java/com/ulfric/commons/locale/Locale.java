package com.ulfric.commons.locale;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;

public final class Locale implements Iterable<Message> {

	public static Builder builder()
	{
		return new Builder();
	}

	public static final class Builder
	{
		Builder() { }

		public Locale build()
		{
			Objects.requireNonNull(this.code);

			return new Locale(this.code, Collections.unmodifiableMap(new HashMap<>(this.messages)));
		}

		private String code;
		private final Map<String, Message> messages = new HashMap<>();

		public Builder setCode(String code)
		{
			Objects.requireNonNull(code);

			this.code = code;

			return this;
		}

		private void quickAddMessage(Message message)
		{
			this.messages.put(message.getCode().toLowerCase(), message);
		}

		public Builder addMessage(Message message)
		{
			Objects.requireNonNull(message);

			this.quickAddMessage(message);

			return this;
		}

		public Builder addMessages(Iterable<Message> messages)
		{
			Objects.requireNonNull(messages);

			for (Message message : messages)
			{
				if (message == null) continue;

				this.quickAddMessage(message);
			}

			return this;
		}
	}

	Locale(String code, Map<String, Message> messages)
	{
		this.code = code;
		this.messages = messages;
	}

	private final String code;
	private final Map<String, Message> messages;

	public String getCode()
	{
		return this.code;
	}

	@Override
	public Iterator<Message> iterator()
	{
		return this.messages.values().iterator();
	}

	public Optional<Message> getMessage(String code)
	{
		return Optional.ofNullable(this.messages.get(String.valueOf(code).toLowerCase()));
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}