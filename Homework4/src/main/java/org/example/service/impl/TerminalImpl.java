package org.example.service.impl;

import org.example.exception.AccountIsLockedException;
import org.example.service.OutputSystem;
import org.example.service.PinValidator;
import org.example.service.Terminal;
import org.example.service.TerminalServer;

import java.util.Scanner;

public class TerminalImpl implements Terminal {

    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final OutputSystem outputSystem;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator, OutputSystem outputSystem) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.outputSystem = outputSystem;
    }

    @Override
    public void enterPassword() {
        Scanner scanner = new Scanner(System.in);
        pinValidator.setPassword(server.getPassword());
        while (true) {
            String enter = scanner.nextLine();
            try {
                boolean result = pinValidator.enterPassword(enter);
                if (result) {
                    server.setAccessStatus(pinValidator.getAccessStatus());
                    break;
                }
            } catch (AccountIsLockedException e) {
                outputSystem.printError(e.getMessage());
            }
        }
    }

    @Override
    public void getAccountDetails() {
        server.getAccountDetails();
    }

    @Override
    public void takeMoney(Long amount) {
        server.takeMoney(amount);
    }

    @Override
    public void putMoney(Long amount) {
        server.putMoney(amount);
    }
}
