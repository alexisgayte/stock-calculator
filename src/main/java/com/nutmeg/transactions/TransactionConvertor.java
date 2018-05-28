package com.nutmeg.transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class TransactionConvertor implements Function<String, Transaction> {
	private static final int ACCOUNT_COL = 0;
	private static final int DATE_COL = 1;
	private static final int TXN_TYPE_COL = 2;
	private static final int UNITS_COL = 3;
	private static final int PRICE_COL = 4;
	private static final int ASSET_COL = 5;
	
	private DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;

	public TransactionConvertor() {
	}
	
	public TransactionConvertor(DateTimeFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}
	
	@Override
	public Transaction apply(String transactionLine) {
		String[] split = transactionLine.split(",");	
		
		Transaction transaction = new Transaction();
		transaction.setAccount(split[ACCOUNT_COL]);
		transaction.setDate(LocalDate.parse(split[DATE_COL],dateFormatter));
		transaction.setTxnType(TransactionType.valueOf(split[TXN_TYPE_COL]));
		transaction.setPrice(Double.valueOf(split[PRICE_COL]));
		transaction.setUnits(Double.valueOf(split[UNITS_COL]));
		transaction.setAsset(split[ASSET_COL]);
		
		return transaction;
	}
}
