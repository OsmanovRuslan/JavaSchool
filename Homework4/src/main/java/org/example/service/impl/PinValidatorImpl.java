package org.example.service.impl;

import org.example.exception.AccountIsLockedException;
import org.example.exception.PinCodeException;
import org.example.service.OutputSystem;
import org.example.service.PinValidator;

public class PinValidatorImpl implements PinValidator {

    private final OutputSystem outputSystem;

    private boolean accessAllowed = false;
    String enteredPassword = "";
    private String clientPassword;
    private int attempts = 0;
    boolean block = false;
    long start;

    public PinValidatorImpl(OutputSystem outputSystem) {
        this.outputSystem = outputSystem;
    }

    @Override
    public boolean enterPassword(String digit) throws AccountIsLockedException {
        long now = System.currentTimeMillis();
        if ((now - start) >= 10_000) {
            block = false;
        }
        try {
            if (block){
                long timeToFinish = (start + 10_000) - now;
                throw new AccountIsLockedException("Аккаунт временно заблокирован. До разблокировки осталось: " + (timeToFinish < 0 ? "0" : timeToFinish / 1000) + "сек.");
            }
            if (digit.length() == 1 && Character.isDigit(digit.charAt(0))) {
                enteredPassword += digit;
                if (enteredPassword.length() == 4 && enteredPassword.equals(clientPassword)) {
                    accessAllowed = true;
                    outputSystem.printMessage("Пин код верный. Можно выполнять операции");
                    return true;
                } else if (enteredPassword.length() == 4 && !enteredPassword.equals(clientPassword)) {
                    attempts++;
                    enteredPassword = "";
                    if (attempts == 3) {
                        outputSystem.printMessage("Пин код неверный. Аккаунт заблокирован на 10 секунд.");
                        start = System.currentTimeMillis();
                        block = true;
                        attempts = 0;
                    } else {
                        outputSystem.printMessage("Пин код неверный. У вас осталось попыток: " + (3 - attempts));
                    }
                }
            } else if (digit.length() != 1) {
                throw new PinCodeException("Неверное количество символов. Вы должны вводить по одной цифре.");
            } else if (!Character.isDigit(digit.charAt(0))) {
                throw new PinCodeException("Некорректный символ. Вы должны вводить по одной цифре.");
            }
        } catch (PinCodeException e) {
            outputSystem.printError(e.getMessage());
        }
        return false;
    }

    public boolean getAccessStatus() {
        return accessAllowed;
    }

    @Override
    public void setPassword(String password) {
        clientPassword = password;
    }

}
