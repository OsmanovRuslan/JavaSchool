package org.example.service;

public interface TerminalServer {

    void getAccountDetails();

    String getPassword();

    void setAccessStatus(boolean accessStatus);

    void takeMoney(Long amount);

    void putMoney(Long amount);

}
