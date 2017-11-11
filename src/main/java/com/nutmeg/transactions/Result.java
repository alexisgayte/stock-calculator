package com.nutmeg.transactions;

import java.math.BigDecimal;

public class Result {
  private BigDecimal cash;
  private BigDecimal stock;

  public Result(BigDecimal cash, BigDecimal stock) {
    this.cash = cash;
    this.stock = stock;
  }
}
