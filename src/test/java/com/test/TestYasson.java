package com.test;

import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

import static org.junit.Assert.assertEquals;

public class TestYasson {
	@Test
	public void test() {
		// Given
		Jsonb jsonb = JsonbBuilder.create(new JsonbConfig()
				.withSerializers(new CustomSerializer()));
		TestClass first = new TestClass("1", "2");
		TestClass second = new TestClass("4", "5");

		// When
		String firstJson = jsonb.toJson(first, GenericClass.class);
		String secondJson = jsonb.toJson(second, GenericClass.class);

		// Then
		assertEquals("{\"com.test.TestClass\":{\"a\":\"1\",\"b\":\"2\"}}", firstJson);
		assertEquals("{\"com.test.TestClass\":{\"a\":\"4\",\"b\":\"5\"}}", secondJson);
	}

	public class CustomSerializer implements JsonbSerializer<GenericClass> {
		@Override
		public void serialize(GenericClass instance, JsonGenerator generator, SerializationContext context) {
			generator.writeStartObject();

			if (instance != null)
				context.serialize(instance.getClass().getName(), instance, generator);
			else
				context.serialize(null, generator);

			generator.writeEnd();
		}
	}
}
