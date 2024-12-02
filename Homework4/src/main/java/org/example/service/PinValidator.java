package org.example.service;

import org.example.exception.AccountIsLockedException;

public interface PinValidator {

    boolean getAccessStatus();

    void setPassword(String password);

    boolean enterPassword(String digit) throws AccountIsLockedException;

}
