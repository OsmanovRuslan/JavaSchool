package org.example.iterator.impl;

import org.example.iterator.MassiveIterator;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

public class MassiveIteratorImplTest {

    private Object[] objects;
    private MassiveIterator massiveIterator;

    @BeforeEach
    public void setUp() {
        objects = new Object[]{1, 2, 3, 4, 5};
        massiveIterator = new MassiveIteratorImpl(objects);
    }

    @Test
    @DisplayName("Тест hasNext()")
    public void whenHasNext_thenOk() {
        Assertions.assertTrue(massiveIterator.hasNext());
    }

    @Test
    @DisplayName("Тест next()")
    public void whenNext_thenOk() {
        Object result = null;
        if (massiveIterator.hasNext()) {
            result = massiveIterator.next();
        }

        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("Тест remove()")
    public void whenRemove_thenOk() {
        Object[] expectedObjects = {2, 3, 4, 5};

        if (massiveIterator.hasNext()) {
            massiveIterator.next();
        }
        massiveIterator.remove();

        Object[] actualObjects = new Object[4];
        int count = 0;
        while (massiveIterator.hasNext()) {
            actualObjects[count] = massiveIterator.next();
            count++;
        }

        Assertions.assertArrayEquals(expectedObjects, actualObjects);
    }

    @Test
    @DisplayName("Тест на NoSuchElementException")
    public void whenNextAfterAll_thenThrowNoSuchElementException() {
        while (massiveIterator.hasNext()) {
            massiveIterator.next();
        }

        Assertions.assertThrows(NoSuchElementException.class, () -> massiveIterator.next());
    }

}
