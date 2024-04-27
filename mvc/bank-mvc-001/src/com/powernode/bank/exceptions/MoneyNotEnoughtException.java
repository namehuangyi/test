package com.powernode.bank.exceptions;

public class MoneyNotEnoughtException extends Exception{
    public MoneyNotEnoughtException(String message) {
        super(message);
    }

    public MoneyNotEnoughtException() {
    }
}
