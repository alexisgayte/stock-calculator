package com.nutmeg.transactions;

import java.math.BigDecimal;

public enum TransactionType {
  BOT {
    public Result calculate(Transaction transaction) {
      return new Result(BigDecimal.valueOf(-1).multiply(transaction.getPrice()), transaction.getUnit());
    }
  },

  SLD {
    public Result calculate(Transaction transaction) {
      return new Result(transaction.getPrice(), BigDecimal.valueOf(-1).multiply(transaction.getUnit()));
    }
  },

  DIV {
    public Result calculate(Transaction transaction) {
      return new Result(transaction.getPrice(), BigDecimal.ZERO);
    }
  },

  DEP {
    public Result calculate(Transaction transaction) {
      return new Result(transaction.getPrice(), BigDecimal.ZERO);
    }
  },

  WDR {
    public Result calculate(Transaction transaction) {
      return new Result(BigDecimal.valueOf(-1).multiply(transaction.getPrice()), BigDecimal.ONE);
    }
  };

}
