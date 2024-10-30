package ru.enchilik.currency;

public class App {

    public static void main(String[] args) {
        OperationsAndExchange operationsAndExchange = new OperationsAndExchange();
        WorkingWithConsole workingWithConsole = new WorkingWithConsole(operationsAndExchange);

        operationsAndExchange.setCurrenciesRate();
        workingWithConsole.inputToExchange();
    }
}
