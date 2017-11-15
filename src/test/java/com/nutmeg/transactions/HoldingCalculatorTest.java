package com.nutmeg.transactions;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.nutmeg.transactions.FileUtils.fileFromResources;
import static org.assertj.core.api.Assertions.assertThat;

public class HoldingCalculatorTest {

  private HoldingCalculator holdingCalculator;

  @Before
  public void setUp(){
    holdingCalculator = new HoldingCalculatorImpl();
  }


  @Test
  public void with() throws IOException {
    String fileName = "data/all.csv";

    Map<String,List<Holding>> holdings = holdingCalculator.calculateHoldings(fileFromResources(fileName), LocalDate.now());

    assertThat(holdings)
        .isNotEmpty()
        .containsOnlyKeys("NEAA0000","NEAB0001") ;

  }
}