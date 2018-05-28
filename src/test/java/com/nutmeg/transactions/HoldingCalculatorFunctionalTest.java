package com.nutmeg.transactions;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class HoldingCalculatorFunctionalTest {

	private HoldingCalculator holdingCalculator;

	@Before
	public void setUp() {
		holdingCalculator = new HoldingCalculatorImpl();
	}

	@Test
	public void applyTestFormDocumentationComponantTest() throws IOException {
		String fileName = "data/all.csv";

		ClassLoader classLoader = this.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		Map<String, List<Holding>> holdings = holdingCalculator.calculateHoldings(file, LocalDate.of(2017, 2, 01));
		
		
		assertEquals(2, holdings.size());
		assertEquals(4, holdings.get("NEAA0000").size());
		Holding vusaHolding = holdings.get("NEAA0000").stream().filter(x -> "VUSA".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(10d, vusaHolding.getHolding(), 0.0001);
		Holding vukeHolding = holdings.get("NEAA0000").stream().filter(x -> "VUKE".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(20d, vukeHolding.getHolding(), 0.0001);
		Holding gilsHolding = holdings.get("NEAA0000").stream().filter(x -> "GILS".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(10.5120, gilsHolding.getHolding(), 0.0001);
		Holding cashHolding = holdings.get("NEAA0000").stream().filter(x -> "CASH".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(17.6848, cashHolding.getHolding(), 0.0001);
		
		assertEquals(1, holdings.get("NEAB0001").size());
		cashHolding = holdings.get("NEAB0001").stream().filter(x -> "CASH".equals(x.getAsset())).collect(Collectors.toList()).get(0);
		assertEquals(10000d, cashHolding.getHolding(), 0.0001);
	}

}