package org.example;

import org.example.Person.Person;
import org.example.Person.PersonComparator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

        System.out.println("Значение : Количество повторений");
        System.out.println("5 : " + map.getCount(5));
        System.out.println("6 : " + map.getCount(6));
        System.out.println("10 : " + map.getCount(10));
        System.out.println("----------------");

        System.out.println("Size: " + map.size());

        CountMap<Integer> map2 = new CountMapImpl<>();

        map2.add(12);
        map2.add(10);

        System.out.println("Соединение коллекций...");
        map2.toMap(map.toMap());
//        map.addAll(map2);

        System.out.println("Соединенные коллекции: " + map.toMap());

        System.out.println("Возврат метода remove: " + map.remove(10));

        System.out.println("Значение после метода remove: " + map.getCount(10));

        System.out.println("----------------");
        System.out.println("Задание 2");

        List<String> list = CollectionUtils.newArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        List<String> list2 = CollectionUtils.newArrayList();
        list2.add("5");
        list2.add("6");
        list2.add("7");

        System.out.println("List: " + list);
        System.out.println("Индекс объекта 3: " + CollectionUtils.indexOf(list, "3"));

        CollectionUtils.addAll(list2, list);
        System.out.println("List after adding list2: " + list);

        System.out.println("Limited List: " + CollectionUtils.limit(list, 3));

        System.out.println("Contains all: " + CollectionUtils.containsAll(list, list2));
        System.out.println("Contains any: " + CollectionUtils.containsAny(list, list2));

        System.out.println("List от 2 до 4: " + CollectionUtils.range(list, "2", "4"));

        CollectionUtils.removeAll(list, list2);
        System.out.println("Remove all items of list2 from list: " + list);
        System.out.println("Contains any: " + CollectionUtils.containsAny(list, list2));

        Person tom = new Person("Tom", 20);
        Person peter = new Person("Peter", 14);

        List<Person> personList = CollectionUtils.newArrayList();
        personList.add(tom);
        personList.add(peter);

        List<Person> rangedList = CollectionUtils.range(personList,
                new Person("Tom", 18),
                new Person("Peter", 21),
                new PersonComparator());

        System.out.println("----------------");
        System.out.println("Testing CollectionUtils.range with Comparator");
        System.out.println("Size of rangedList: " + rangedList.size());
        System.out.println("Elements from rangedList:");
        rangedList.forEach(System.out::println);
    }
}