package edu.sdccd.cisc191.template.franchise;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// MODULE 11 & 15

public class FranchiseSorter {

    /**
     * Searches for a franchise by location in the given franchise list.
     *
     * @param franchiseList the franchise list to search in
     * @param location      the location to search for
     * @return the franchise with the specified location, or null if not found
     */
    public static Franchise searchByLocation(FranchiseList<Franchise> franchiseList, String location) {
        FranchiseList<Franchise>.Node currentNode = franchiseList.head;
        while (currentNode != null) {
            if (currentNode.data.getLocation().equals(location)) {
                return currentNode.data;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    /**
     * Sorts the franchise list by location.
     *
     * @param franchiseList the franchise list to sort
     *
     * BEFORE
     *  for (Franchise franchise : franchiseData)
     *     franchiseList.add(franchise);
     */
    public static void sortByLocation(FranchiseList<Franchise> franchiseList) {
        List<Franchise> sortedList = franchiseList.stream()
                .sorted(Comparator.comparing(Franchise::getLocation))
                .collect(Collectors.toList());
        franchiseList.clear();

        for (Franchise franchise : sortedList) {
            franchiseList.add(franchise);
        }
    }
}
