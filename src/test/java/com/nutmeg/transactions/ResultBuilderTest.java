package com.nutmeg.transactions;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class ResultBuilderTest {

	public ResultBuilder resultBuilder;

	@Before
	public void init() {

		resultBuilder = new ResultBuilder();

	}

	@Test
	public void addDeposit() {

		Transaction transaction = new Transaction();
		transaction.setAccount("FFA");
		transaction.setDate(LocalDate.now());
		transaction.setPrice(10);
		transaction.setAsset("CASH");
		transaction.setTxnType(TransactionType.DEP);
		transaction.setUnits(1);

		resultBuilder.addTransaction(transaction);
		Result build = resultBuilder.build();

		assertEquals("FFA", build.getAccount());
		assertEquals(1, build.getHolding().size());
		assertEquals("CASH", build.getHolding().get(0).getAsset());
		assertEquals(10, build.getHolding().get(0).getHolding(), 0.0001);
	}
	

	@Test
	public void buyShouldDecreaseTheCash() {

		Transaction transaction = new Transaction();
		transaction.setAccount("FFA");
		transaction.setDate(LocalDate.now());
		transaction.setAsset("AA");
		transaction.setPrice(10);
		transaction.setTxnType(TransactionType.BOT);
		transaction.setUnits(2);

		resultBuilder.addTransaction(transaction);
		Result build = resultBuilder.build();

		assertEquals("FFA", build.getAccount());
		assertEquals(2, build.getHolding().size());
		Holding cashHolding = build.getHolding().stream().filter(x -> "CASH".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(-20, cashHolding.getHolding(), 0.0001);
		
		Holding aaHolding = build.getHolding().stream().filter(x -> "AA".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(2, aaHolding.getHolding(), 0.0001);
	}


}
