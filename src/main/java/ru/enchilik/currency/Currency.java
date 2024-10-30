package ru.enchilik.currency;

public enum Currency {
    USD (),
    EUR(),
    RUB(),
    CNY(),
    KZT();

    private double val;

    Currency() {
    }

    Currency(double val) {
        this.val = val;
    }

    public double setVal(double val) {
        this.val = val;
        return val;
    }

    public double getVal() {
        return val;
    }
}
