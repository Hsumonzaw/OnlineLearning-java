package com.companyname.one.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateTimeFormatDeserializer extends StdDeserializer<Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public DateTimeFormatDeserializer() {
		this(null);
	}

	protected DateTimeFormatDeserializer(Class<Date> t) {
		super(t);
	}

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		try {
			if("".equals(parser.getText())) {
				return new Date();
			}
			return formatter.parse(parser.getText());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Parsing Error");
		}
	}
}
