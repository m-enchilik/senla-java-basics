package ru.enchilik.currency;

import java.util.Scanner;

public class OperationsAndExchange {
    private final String textSetCurrentRate = "Установите текущий курс валют: ";
    private String temp;
    private Currency[] currencies;

    private WorkingWithConsole workingWithConsole;
    private Scanner scanner = new Scanner(System.in);

    public OperationsAndExchange() {
        currencies = Currency.values();
        workingWithConsole = new WorkingWithConsole(this);
    }

    public void setCurrenciesRate() {
        System.out.println(textSetCurrentRate);
        for (Currency cur : currencies){
            setCurrencyRate(cur.ordinal());
        }
    }

    public double setCurrencyRate(int currencyCode) {
        System.out.print(currencies[currencyCode] + " - ");
        temp = scanner.nextLine();
        if (!workingWithConsole.isDouble(temp)){
            System.out.println("Курс не установлен.");
            currencies[currencyCode].setVal(0);
            return 0;
        }

        double newValue = Double.parseDouble(temp);

        if (newValue < 0) {
            System.exit(-1);
        }

        currencies[currencyCode].setVal(newValue);
        return newValue;
    }

    public Double exchangeCurrency(int innerCodeCurrency, int outerCodeCurrency, double amount) {
        if (checkRate(innerCodeCurrency) == 0) return null;
        if (checkRate(outerCodeCurrency) == 0) return null;
        double money;

        money = (amount * currencies[innerCodeCurrency].getVal()) / currencies[outerCodeCurrency].getVal();

        return money;
    }

    private double checkRate(int currencyCode) {
        double currentRate = currencies[currencyCode].getVal();

        if (currentRate == 0) {
            System.out.println("Не установлен курс для " + currencies[currencyCode] + " валюты!");
            System.out.println("Установите курс или наберите 0, чтобы начать заново.");
            currentRate = setCurrencyRate(currencyCode);
        }

        return currentRate;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currency[] currencies) {
        this.currencies = currencies;
    }
}