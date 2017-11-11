package com.nutmeg.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

public class TransactionBuilder {
  private String accountNumber;
  private LocalDate date;
  private TransactionType transactionType;
  private BigDecimal unit;
  private BigDecimal price;
  private String asset;

  public TransactionBuilder (String accountNumber, String date){
    this.accountNumber = accountNumber;
    this.date = LocalDate.parse(date, BASIC_ISO_DATE);
  }

  public TransactionBuilder withTransactionType(String type) {
    this.transactionType = TransactionType.valueOf(type);
    return this;
  }

  public TransactionBuilder hasUnit(String unit) {
    this.unit = new BigDecimal (unit);
    return this;
  }

  public TransactionBuilder hasAsset(String asset) {
    this.asset = asset;
    return this;
  }

  public TransactionBuilder hasPrice(String price) {
    this.price = new BigDecimal(price);
    return this;
  }

  public Transaction build() {
    Transaction transaction = new Transaction(accountNumber, date);
    transaction.setType(transactionType);
    transaction.setPrice(price);
    transaction.setUnit(unit);
    transaction.setAsset(asset);
    // TODO

    return transaction;
  }
}
