import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCollection {

    @Test
    public void testToString() {
        MyCollection<Integer> col = new MyCollection<>();
        col.add(1);
        col.add(2);
        String str = col.toString();
        MyCollection<Integer> col1 = new MyCollection<>();
        String str1 = col1.toString();
        assertAll(() -> assertEquals("[1, 2]", str),
                () -> assertEquals("[]", str1));

    }

    @Test
    public void testAdd() {
        MyCollection<Integer> col = new MyCollection<>();
        col.add(1);
        col.add(2);
        String str = col.toString();
        assertAll(() -> assertEquals("[1, 2]", str),
                () -> assertTrue(col.add(1)));
    }

    @Test
    public void testGet() {
        MyCollection<Integer> col = new MyCollection<>();
        col.add(1);
        col.add(2);
        int res = col.get(1);
        assertAll(() -> assertEquals(2, res),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> col.get(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> col.get(3)));
    }

    @Test
    public void testAddByIndex() {
        MyCollection<Integer> col = new MyCollection<>();
        col.add(1);
        col.add(2);
        col.add(0, 3);
        col.add(1, 4);
        col.add(4, 3);
        String str = col.toString();
        assertAll(() -> assertEquals("[3, 4, 1, 2, 3]", str),
                () -> assertEquals("[3, 4, 1, 2, 3]", str),
                () -> assertTrue(col.add(1, 2)),
                () -> assertFalse(col.add(10, 10))
                );
    }

    @Test
    public void testRemove() {
        MyCollection<Integer> col = new MyCollection<>();
        col.add(0);
        col.add(1);
        col.add(2);
        col.add(3);
        col.add(4);
        col.add(5);
        int delEl5 = col.remove(5);
        int delEl2 = col.remove(2);
        int delEl0 = col.remove(0);
        String str = col.toString();
        assertAll(() -> assertEquals(5, delEl5),
                () -> assertEquals(2, delEl2),
                () -> assertEquals(0, delEl0),
                () -> assertEquals("[1, 3, 4]", str),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> col.remove(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> col.remove(3)));
    }
}
