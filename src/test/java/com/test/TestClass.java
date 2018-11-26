package com.test;

public class TestClass extends GenericClass {
	private String b;

	public TestClass(String a, String b) {
		super(a);
		this.b = b;
	}

	public String getB() {
		return b;
	}
}
