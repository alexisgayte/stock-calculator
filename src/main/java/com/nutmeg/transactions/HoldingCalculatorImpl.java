package com.nutmeg.transactions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HoldingCalculatorImpl implements HoldingCalculator {

	private TransactionConvertor transactionConvertor = new TransactionConvertor();

	@Override
	public Map<String, List<Holding>> calculateHoldings(File transactionFile, LocalDate date){

		
		Map<String, List<Holding>> result = new LinkedHashMap<>();
		ResultBuilder resultBuilder = new ResultBuilder();
		
		try {
			Files.lines(Paths.get((transactionFile.getAbsolutePath())))
					.map(transactionConvertor)
					.filter(t -> !date.isBefore(t.getDate()))
					.forEachOrdered(a -> {
						if (!resultBuilder.isBuilderFor(a.getAccount())) {
							build(result, resultBuilder);
						}
						resultBuilder.addTransaction(a);
					});
			build(result, resultBuilder);
			
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		return result;
	}

	private void build(Map<String, List<Holding>> result, ResultBuilder resultBuilder) {
		Result build = resultBuilder.build();
		resultBuilder.reset();
		result.put(build.getAccount(), build.getHolding());
	}

}
