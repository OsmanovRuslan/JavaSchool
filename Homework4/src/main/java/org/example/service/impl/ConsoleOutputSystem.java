package org.example.service.impl;

import org.example.service.OutputSystem;

public class ConsoleOutputSystem implements OutputSystem {

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.err.println(message);
    }

}
