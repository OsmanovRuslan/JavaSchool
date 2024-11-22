package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");

        String[] fruitArray = new String[]{"apple", "lemon", "banana",
                "apple", "strawberry", "orange",
                "strawberry", "melon", "orange",
                "banana", "apple", "watermelon",
                "lemon", "peach", "pear"};

        Map<String, Integer> fruitMap = new TreeMap<>();
        for (String fruit : fruitArray){
            fruitMap.put(fruit, fruitMap.getOrDefault(fruit, 0) + 1);
        }

        Set<String> uniqueFruits = new TreeSet<>(fruitMap.keySet());
        System.out.println("Список уникальных фруктов: " + uniqueFruits);

        System.out.println("Слово : Количество повторений");
        fruitMap.forEach((fruit, quantity) -> System.out.println(fruit + " : " + quantity));

        System.out.println("----------------------");
        System.out.println("Задание 2");

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ivanov", "89123456789");
        phoneBook.add("Sherbakov", "89663617293");
        phoneBook.add("Ivanov", "89826137946");
        phoneBook.add("Sherbakov", "89718376151");
        phoneBook.add("Ivanov", "89825725111");
        phoneBook.add("Petrov", "89998562845");

        System.out.println("Ivanov: " + phoneBook.get("Ivanov"));
        System.out.println("Sherbakov: " + phoneBook.get("Sherbakov"));
        System.out.println("Petrov: " + phoneBook.get("Petrov"));
        System.out.println("Sidorov: " + phoneBook.get("Sidorov"));
    }
}