package org.example;

public class Child extends Parent{

    static {
        System.out.println("Child:static 1");
    }

    static {
        System.out.println("Child:static 2");
    }

    {
        System.out.println("Child:instance 1");
    }

    {
        System.out.println("Child:instance 2");
    }

    public Child() {
        System.out.println("Child:constructor");
    }

    public Child(String Name) {
        System.out.println("Child:name-constructor");
    }

}
