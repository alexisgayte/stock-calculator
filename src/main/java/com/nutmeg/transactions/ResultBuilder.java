package com.nutmeg.transactions;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ResultBuilder {
	private static String CASH_KEY = "CASH";

	private String account;
	private Map<String, Double> holding;

	public ResultBuilder() {
		reset();
	}
	
	public boolean isBuilderFor(String account) {
		return this.account == null || Objects.equals(this.account, account);
	}

	public void addTransaction(Transaction transaction) {
		
		if (!isBuilderFor(transaction.getAccount())) throw new RuntimeException();
		if (account == null) account = transaction.getAccount();
		
		switch (transaction.getTxnType()) {
		case BOT:
			// Buy
			holding.merge(CASH_KEY, transaction.getPrice() * transaction.getUnits(), (x, y) -> x - y);
			holding.merge(transaction.getAsset(), transaction.getUnits(), (x, y) -> x + y);
			break;
		case DIV:
		case DEP:
			// Deposit/Div
			holding.merge(CASH_KEY, transaction.getPrice() * transaction.getUnits(), (x, y) -> x + y);
			break;
		case WDR:
			// Withdrawal
			holding.merge(CASH_KEY, transaction.getPrice() * transaction.getUnits(), (x, y) -> x - y);
			break;
		case SLD:
			// Sold
			holding.merge(CASH_KEY, transaction.getPrice() * transaction.getUnits(), (x, y) -> x + y);
			holding.merge(transaction.getAsset(), transaction.getUnits(), (x, y) -> x - y);
			break;
		default:
			break;
		}
		
	}

	public Result build() {
		List<Holding> holdings = holding.entrySet().stream()
											.map(x -> new Holding(x.getKey(), x.getValue()))
											.collect(Collectors.toList());
		return new Result(account, holdings);
	}

	public void reset() {
		account = null;
		holding = new ConcurrentHashMap<>();
		holding.put("CASH", 0d);
	}
}