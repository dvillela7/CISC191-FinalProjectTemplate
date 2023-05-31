package edu.sdccd.cisc191.template.franchise

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FranchiseListTest {
    private FranchiseList<String> franchiseList;

    @BeforeEach
    void setUp() {
        franchiseList = new FranchiseList<>();
        franchiseList.add("Franchise A");
        franchiseList.add("Franchise B");
        franchiseList.add("Franchise C");
    }

    @Test
    void testAdd() {
        assertEquals(3, franchiseList.toArrayList().size());
        assertEquals("Franchise A", franchiseList.toArrayList().get(0));
        assertEquals("Franchise B", franchiseList.toArrayList().get(1));
        assertEquals("Franchise C", franchiseList.toArrayList().get(2));
    }

    @Test
    void testRemove() {
        franchiseList.remove("Franchise B");
        assertEquals(2, franchiseList.toArrayList().size());
        assertEquals("Franchise A", franchiseList.toArrayList().get(0));
        assertEquals("Franchise C", franchiseList.toArrayList().get(1));

        franchiseList.remove("Franchise A");
        franchiseList.remove("Franchise C");
        assertNull(franchiseList.head);
        assertNull(franchiseList.tail);
        assertNull(franchiseList.current);
    }

    @Test
    void testToArrayList() {
        List<String> franchiseData = franchiseList.toArrayList();
        assertEquals(3, franchiseData.size());
        assertEquals("Franchise A", franchiseData.get(0));
        assertEquals("Franchise B", franchiseData.get(1));
        assertEquals("Franchise C", franchiseData.get(2));
    }

    @Test
    void testClear() {
        franchiseList.clear();
        assertNull(franchiseList.head);
        assertNull(franchiseList.tail);
        assertNull(franchiseList.current);
    }

    @Test
    void testStream() {
        List<String> franchiseData = franchiseList.stream().toList();
        assertEquals(3, franchiseData.size());
        assertEquals("Franchise A", franchiseData.get(0));
        assertEquals("Franchise B", franchiseData.get(1));
        assertEquals("Franchise C", franchiseData.get(2));
    }
}
