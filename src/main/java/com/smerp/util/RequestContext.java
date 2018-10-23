package com.smerp.util;

public class RequestContext {

	private static ThreadLocal<Context> requestContext = new ThreadLocal<Context>();

	public static void initialize() {
		requestContext.set(new Context());
	}

	public static void set(Context context) {
		requestContext.set(context);
	}

	public static Context get() {
		return requestContext.get();
	}

	public static void remove() {
		requestContext.set(null);
	}

	@Override
	public String toString() {
		return "RequestContext []";
	}

}
