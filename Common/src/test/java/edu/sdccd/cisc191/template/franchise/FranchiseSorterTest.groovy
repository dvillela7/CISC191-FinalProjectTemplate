package edu.sdccd.cisc191.template.franchise

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FranchiseSorterTest {
    private FranchiseList<Franchise> franchiseList;

    @BeforeEach
    void setUp() {
        franchiseList = new FranchiseList<>();
        franchiseList.add(new Franchise("Franchise A", "Location A"));
        franchiseList.add(new Franchise("Franchise B", "Location C"));
        franchiseList.add(new Franchise("Franchise C", "Location B"));
    }

    @Test
    void testSearchByLocation() {
        Franchise franchiseA = FranchiseSorter.searchByLocation(franchiseList, "Location A");
        Franchise franchiseB = FranchiseSorter.searchByLocation(franchiseList, "Location B");
        Franchise franchiseD = FranchiseSorter.searchByLocation(franchiseList, "Location D");

        assertEquals("Franchise A", franchiseA.getName());
        assertEquals("Franchise C", franchiseB.getName());
        assertNull(franchiseD);
    }

    @Test
    void testSortByLocation() {
        FranchiseSorter.sortByLocation(franchiseList);

        Franchise franchiseA = franchiseList.get(0);
        Franchise franchiseB = franchiseList.get(1);
        Franchise franchiseC = franchiseList.get(2);

        assertEquals("Franchise A", franchiseA.getName());
        assertEquals("Franchise C", franchiseB.getName());
        assertEquals("Franchise B", franchiseC.getName());

    }
}