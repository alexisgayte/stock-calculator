package com.nutmeg.transactions;

import java.util.List;


public class Result {
	
	private String account;
	private List<Holding> holding;
	
	public String getAccount() {
		return account;
	}

	public List<Holding> getHolding() {
		return holding;
	}

	public Result(String account, List<Holding> holding) {
		super();
		this.account = account;
		this.holding = holding;
	}

}