package com.spring.upwork.model;

public class Currency {
    private String currencyCode;
    private Double amount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                '}';
    }
}
