package br.com.ifood.shop.catalog.error;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String messageDefault = "No data found.";

	public DataNotFoundException() {
		super(messageDefault);
	}
}