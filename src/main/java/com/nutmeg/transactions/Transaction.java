package com.nutmeg.transactions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
  private String accountNumber;
  private LocalDate date;
  private TransactionType type;
  private BigDecimal unit;
  private String asset;
  private BigDecimal price;

  public Transaction(String accountNumber, LocalDate date){
    this.accountNumber = accountNumber;
    this.date = date;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public BigDecimal getUnit() {
    return unit;
  }

  public void setUnit(BigDecimal unit) {
    this.unit = unit;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "accountNumber='" + accountNumber + '\'' +
        ", date=" + date +
        ", type=" + type +
        ", unit=" + unit +
        ", asset='" + asset + '\'' +
        ", price=" + price +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Transaction that = (Transaction) o;

    if (!accountNumber.equals(that.accountNumber)) return false;
    if (!date.equals(that.date)) return false;
    if (type != that.type) return false;
    if (!unit.equals(that.unit)) return false;
    if (!asset.equals(that.asset)) return false;
    return price.equals(that.price);
  }

  @Override
  public int hashCode() {
    int result = accountNumber.hashCode();
    result = 31 * result + date.hashCode();
    result = 31 * result + type.hashCode();
    result = 31 * result + unit.hashCode();
    result = 31 * result + asset.hashCode();
    result = 31 * result + price.hashCode();
    return result;
  }

}
