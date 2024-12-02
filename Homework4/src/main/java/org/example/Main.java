package org.example;

import org.example.service.impl.ConsoleOutputSystem;
import org.example.service.impl.PinValidatorImpl;
import org.example.service.impl.TerminalImpl;
import org.example.service.impl.TerminalServerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Система вывода сообщений
        ConsoleOutputSystem outputSystem = new ConsoleOutputSystem();

        // Сервер на который терминал присылает запросы на выполнение операций
        TerminalServerImpl server = new TerminalServerImpl(1000L, outputSystem);

        // Пин код валидатор, который проверяет правильность пин кода и разрешает доступ
        PinValidatorImpl pinValidator = new PinValidatorImpl(outputSystem);

        // Терминал, с которым пользователь взаимодействует
        TerminalImpl terminal = new TerminalImpl(server, pinValidator, outputSystem);

        // Пытаемся произвести операцию без права доступа
        terminal.takeMoney(1000L);

        // Вводим пароль
        terminal.enterPassword();

        // Имея права доступа, выполняем операции: положить, проверить, снять деньги
        terminal.putMoney(1000L);
        terminal.getAccountDetails();
        terminal.takeMoney(1000L);


        System.out.println("Задание 2.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите URL ресурса: ");
            String url = scanner.nextLine();
            try {
                readContent(url);
                break;
            } catch (IOException e) {
                System.err.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте ввести URL снова.");
            }
        }

    }

    private static void readContent(String url) throws IOException {
        URL siteUrl = new URL(url);
        URLConnection connection = siteUrl.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}