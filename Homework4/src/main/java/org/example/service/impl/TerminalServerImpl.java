package org.example.service.impl;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountArgumentException;
import org.example.exception.UnauthorizedAccessException;
import org.example.service.OutputSystem;
import org.example.service.TerminalServer;

public class TerminalServerImpl implements TerminalServer {

    private final OutputSystem outputSystem;

    private Long money;
    private final String password = "1234";
    private boolean accessStatus = false;

    public TerminalServerImpl(Long money, OutputSystem outputSystem) {
        if (money != null && money >= 0L) {
            this.money = money;
        } else {
            this.money = 0L;
        }
        this.outputSystem = outputSystem;
    }

    @Override
    public void getAccountDetails() {
        try{
            if (accessStatus) {
                outputSystem.printMessage("На счете: " + money);
            } else {
                throw new UnauthorizedAccessException("У вас нет права доступа. Введите пин код.");
            }
        } catch (UnauthorizedAccessException e) {
            outputSystem.printError(e.getMessage());
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setAccessStatus(boolean accessStatus) {
        this.accessStatus = accessStatus;
    }

    @Override
    public void takeMoney(Long amount) {
        try {
            if (accessStatus) {
                if (money >= amount && amount > 0 && (amount % 100 == 0)) {
                    money -= amount;
                    outputSystem.printMessage("Снятие успешно завершено");
                } else if (amount <= 0) {
                    throw new InvalidAmountArgumentException("Количество денег должно быть больше 0");
                } else if (amount % 100 != 0) {
                    throw new InvalidAmountArgumentException("Количество денег должно быть кратно 100");
                } else {
                    throw new InsufficientFundsException("Недостаточно средств для совершения операции");
                }
            } else {
                throw new UnauthorizedAccessException("У вас нет права доступа. Введите пин код.");
            }
        } catch (InvalidAmountArgumentException | InsufficientFundsException | UnauthorizedAccessException e) {
            outputSystem.printError(e.getMessage());
        }
    }

    @Override
    public void putMoney(Long amount) {
        try {
            if (accessStatus) {
                if (amount > 0 && (amount % 100 == 0)) {
                    money += amount;
                    outputSystem.printMessage("Деньги успешно зачислены на счет");
                }
                else if (amount <= 0) {
                    throw new InvalidAmountArgumentException("Количество денег должно быть больше 0");
                } else if (amount % 100 != 0) {
                    throw new InvalidAmountArgumentException("Количество денег должно быть кратно 100");
                }
            } else {
                throw new UnauthorizedAccessException("У вас нет права доступа. Введите пин код.");
            }
        } catch (InvalidAmountArgumentException | UnauthorizedAccessException e) {
            outputSystem.printError(e.getMessage());
        }
    }
}
