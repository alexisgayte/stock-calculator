package com.nutmeg.transactions;

import java.io.Serializable;
import java.util.List;


public class Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String account;
	private List<Holding> holding;
	
	public Result(String account, List<Holding> holding) {
		super();
		this.account = account;
		this.holding = holding;
	}
	
	public String getAccount() {
		return account;
	}

	public List<Holding> getHolding() {
		return holding;
	}

}